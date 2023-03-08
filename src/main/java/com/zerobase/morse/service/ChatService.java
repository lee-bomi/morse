package com.zerobase.morse.service;

import com.zerobase.morse.entity.ApplicantList;
import com.zerobase.morse.entity.ChatContent;
import com.zerobase.morse.entity.ChatParticipant;
import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.ChatContents;
import com.zerobase.morse.model.InquiryRoomResponse;
import com.zerobase.morse.model.Message;
import com.zerobase.morse.repository.ApplicantListRepository;
import com.zerobase.morse.repository.ChatContentRepository;
import com.zerobase.morse.repository.ChatParticipantRepository;
import com.zerobase.morse.repository.ChatRoomRepository;
import com.zerobase.morse.repository.MemberRepository;
import com.zerobase.morse.repository.StudyRepository;
import com.zerobase.morse.type.RoomType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChatService {


  private final ChatContentRepository chatContentRepository;
  private final ChatRoomRepository chatRoomRepository;
  private final ChatParticipantRepository chatParticipantRepository;
  private final MemberRepository memberRepository;
  private final StudyRepository studyRepository;
  private final ApplicantListRepository applicantListRepository;

  public InquiryRoomResponse makeInquiryChat(Member applicant, Study studyNo) {

    //상담방이 이미 존재하는지 체크
    Optional<ApplicantList> optionalApplicantList =this.applicantListRepository.findByMemberAndStudyNo(applicant,studyNo);

    //방이 이미 있다면 예외 처리
    if (optionalApplicantList.isPresent()) {
      ApplicantList applicantList = optionalApplicantList.get();
      return new InquiryRoomResponse(applicantList.getChatRoom().getRoomId(), "already exists", false);
    }

    //chatRoom에 방 추가
    ChatRoom chatRoom = this.chatRoomRepository.save(ChatRoom.builder()
                                                .studyNo(studyNo)
                                                .roomType(RoomType.INQUIRY_ROOM.getMessage())
                                                .build());

    //작성자 가져오기
    Optional<Study> optionalStudy = this.studyRepository.findById(studyNo.getStudyNo());
    if(optionalStudy.isEmpty()){
      //해당 스터디가 없다면
    }
    Member writer = optionalStudy.get().getEmail();


    //chatParticipant에 참가자와 작성자 추가, 참가자와 작성자가 같을 경우는 안 됨.
    if(applicant.getEmail().equals(writer)){
      return new InquiryRoomResponse(-1,"you are owner",false);
    }

    //채팅 참가자 테이블에 작성자와 신청자 추가
    this.addParticipant(chatRoom, applicant);
    this.addParticipant(chatRoom, writer);

    return new InquiryRoomResponse(chatRoom.getRoomId(), "success making inquiry room", true);
  }

  public ChatContents getChatContent(int chatRoomId) {

    Optional<ChatRoom> optionalChatRoom = this.chatRoomRepository.findById(chatRoomId);
    if (optionalChatRoom.isEmpty()) {
      //해당 방이 없을 경우 예외 처리
    }

    ChatRoom chatRoom = optionalChatRoom.get();
    String writer = this.studyRepository.getStudyByStudyNo(chatRoom.getStudyNo().getStudyNo()).getEmail().getEmail();
    

    List<ChatContent> list = chatContentRepository.findByChatRoomOrderByWriteDt(chatRoom);

    return new ChatContents(chatRoomId, chatRoom.getStudyNo().getStudyNo(), writer, list);
  }

  public ChatContent saveChatContent(String email, Message msg) {

    //해당방에 참여중인지 확인
    ChatRoom chatRoom = this.chatRoomRepository.getChatRoomByRoomId(msg.getRoomNum());
    Member member = this.memberRepository.getById(email);
    boolean isParticipated = this.chatParticipantRepository.existsByMemberAndChatRoom(member,chatRoom);
    if (!isParticipated) {
      //해당방에 참여하지 않은 상태인 경우 예외
    }

    return this.chatContentRepository.save(ChatContent.builder()
        .chatRoom(this.chatRoomRepository.getChatRoomByRoomId(msg.getRoomNum()))
        .member(this.memberRepository.getById(email))
        .content(msg.getChatContent())
        .writeDt(LocalDateTime.now())
        .build());
  }

  public Optional<ChatRoom> getChatRoom(int roomId){

    return chatRoomRepository.findById(roomId);
  }

  private void addParticipant(ChatRoom roomId, Member email) {
    this.chatParticipantRepository.save(ChatParticipant.builder()
        .chatRoom(roomId)
        .member(email)
        .participantDt(LocalDateTime.now())
        .chatStatus(true)
        .build());
  }
}
