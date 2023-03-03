package com.zerobase.morse.repository;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {

	Member findByEmail(String email);
	Study getStudyByStudyNo(Long studyNo);
}
