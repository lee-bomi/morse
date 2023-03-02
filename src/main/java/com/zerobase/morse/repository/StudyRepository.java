package com.zerobase.morse.repository;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study,Integer> {
  Study getStudyByStudyNo(int studyNo);
}
