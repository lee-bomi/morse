package com.zerobase.morse.service;

import com.zerobase.morse.entity.ApplicantList;
import com.zerobase.morse.entity.ChatRoom;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.repository.ApplicantListRepository;
import com.zerobase.morse.repository.ChatRoomRepository;
import com.zerobase.morse.type.ApplyStatusType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class ApplicantListService {
  private final ChatRoomRepository chatRoomRepository;
  private final ApplicantListRepository applicantListRepository;

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
}
