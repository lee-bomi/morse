package com.zerobase.morse.service;

import com.zerobase.morse.entity.Study;
import com.zerobase.morse.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudyService {

  private final StudyRepository studyRepository;

  public Study getStudy(int studyNo){
    return this.studyRepository.getStudyByStudyNo(studyNo);
  }
}
