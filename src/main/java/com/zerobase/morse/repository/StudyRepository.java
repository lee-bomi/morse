package com.zerobase.morse.repository;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import javax.persistence.criteria.CriteriaBuilder.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Integer> {

	Member findByEmail(String email);
	Study getStudyByStudyNo(int studyNo);
}
