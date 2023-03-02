package com.zerobase.morse.repository;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

	Member findByEmail(String email);
=======

public interface StudyRepository extends JpaRepository<Study,Integer> {
  Study getStudyByStudyNo(int studyNo);
>>>>>>> 6e08022 (Feat: repository 추가)
}
