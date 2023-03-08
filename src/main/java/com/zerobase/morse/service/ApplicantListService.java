package com.zerobase.morse.service;

import com.zerobase.morse.entity.ApplicantList;
import com.zerobase.morse.entity.ChatParticipant;
import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.ApplicantResponse;
import com.zerobase.morse.repository.ApplicantListRepository;
import com.zerobase.morse.repository.ChatParticipantRepository;
import com.zerobase.morse.repository.ChatRoomRepository;
import com.zerobase.morse.type.ApplyStatusType;
import com.zerobase.morse.type.RoomType;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class ApplicantListService {
  private final ChatRoomRepository chatRoomRepository;
  private final ApplicantListRepository applicantListRepository;
  private final ChatParticipantRepository chatParticipantRepository;

  public boolean addApplicant(Member email, Study studyNo,int roomId){

    //신청한 적이 있는지 확인
    ApplicantList applicantList = this.applicantListRepository.findApplicantListByMemberAndStudyNo(email,studyNo);
    if(!ObjectUtils.isEmpty(applicantList)){
      return false;
    }

    //없을 경우 추가
    ChatRoom chatRoom = this.chatRoomRepository.getChatRoomByRoomId(roomId);

    this.applicantListRepository.save(ApplicantList.builder()
                                                    .studyNo(studyNo)
                                                    .member(email)
                                                    .chatRoom(chatRoom)
                                                    .applyStatus(ApplyStatusType.APPLYING.getMessage())
                                                    .applyDt(LocalDateTime.now())
                                                    .build());

    return true;
  }

  public ApplicantResponse permit(Study study, ChatRoom chatRoom) {

    // 1. 신청자 상태를 허락으로 바꾸기

    // 1-1.해당 신청자가 있는지 확인
    Optional<ApplicantList> optionalApplicantList =  applicantListRepository.findByStudyNoAndChatRoom(study,chatRoom);

    if(optionalApplicantList.isEmpty()){
      throw new RuntimeException("해당 신청자가 존재하지 않습니다.");
    }

    // 1-2. 신청자 상태를 허락으로 바꾸기
    ApplicantList applicantList = optionalApplicantList.get();
    if(applicantList.getApplyStatus().equals(ApplyStatusType.DENIED.getMessage())){
      throw new RuntimeException("해당 신청자는 이미 거부처리되었습니다.");
    }
    applicantList.setApplyStatus(ApplyStatusType.PERMITTED.getMessage());
    applicantListRepository.save(applicantList);

    // 2. 신청자를 스터디 채팅방에 넣기

    // 2-1. 신청자 가져오기
    Member applicant = applicantList.getMember();

    // 2-2. 스터디 채팅방 가져오기
    Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findByStudyNoAndRoomType(study,
        RoomType.STUDY_ROOM.getMessage());
    if(optionalChatRoom.isPresent()){
      chatParticipantRepository.save(ChatParticipant.builder()
                                                      .chatRoom(optionalChatRoom.get())
                                                      .member(applicant)
                                                      .participantDt(LocalDateTime.now())
                                                      .chatStatus(true)
                                                      .build());
      return new ApplicantResponse("기존 방에 초대되었습니다.",true);
    }

	// 2-3. 없으면 만들기
    ChatRoom newStudyRoom = chatRoomRepository.save(ChatRoom.builder()
																.studyNo(study)
																.roomType(RoomType.STUDY_ROOM.getMessage())
																.build());

    chatParticipantRepository.save(ChatParticipant.builder()
													.chatRoom(newStudyRoom)
													.member(applicant)
													.participantDt(LocalDateTime.now())
													.chatStatus(true)
													.build());

    return new ApplicantResponse("새로운 방이 만들어졌습니다.",true);
  }

  public ApplicantResponse deny(Study study, ChatRoom chatRoom) {

    //해당 신청자가 있는지 확인
    Optional<ApplicantList> optionalApplicantList = applicantListRepository.findByStudyNoAndChatRoom(study,chatRoom);
    if(optionalApplicantList.isEmpty()){
      throw new RuntimeException("해당 신청자가 존재하지 않습니다.");
    }

    //상태바꾸기
    ApplicantList applicantList = optionalApplicantList.get();
    if(applicantList.getApplyStatus().equals(ApplyStatusType.PERMITTED.getMessage())){
      throw new RuntimeException("해당 신청자는 이미 허락처리 되었습니다.");
    }

    applicantList.setApplyStatus(ApplyStatusType.DENIED.getMessage());
    applicantListRepository.save(applicantList);

    return new ApplicantResponse("신청자를 거절했습니다.",true);
  }
}
