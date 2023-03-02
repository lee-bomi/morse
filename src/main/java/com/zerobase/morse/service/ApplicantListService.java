package com.zerobase.morse.service;

import com.zerobase.morse.entity.ApplicantList;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.repository.ApplicantListRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class ApplicantListService {

  private final ApplicantListRepository applicantListRepository;

  public boolean addApplicant(Member email, Study studyNo){
    ApplicantList applicantList = this.applicantListRepository.findApplicantListByMemberAndStudyNo(email,studyNo);
    if(!ObjectUtils.isEmpty(applicantList)){
      return false;
    }


    this.applicantListRepository.save(ApplicantList.builder()
                                                    .studyNo(studyNo)
                                                    .member(email)
                                                    .applyStatus("신청중")
                                                    .applyDt(LocalDateTime.now())
                                                    .build());

    return true;
  }
}
