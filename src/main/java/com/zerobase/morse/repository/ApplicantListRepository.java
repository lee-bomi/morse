package com.zerobase.morse.repository;

import com.zerobase.morse.entity.ApplicantList;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantListRepository extends JpaRepository<ApplicantList,Integer> {
  ApplicantList findApplicantListByMemberAndStudyNo(Member member, Study stduyNo);
}
