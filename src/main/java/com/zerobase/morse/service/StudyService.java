package com.zerobase.morse.service;

import com.zerobase.morse.entity.Keyword;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.StudyInput;
import com.zerobase.morse.repository.KeywordRepository;
import com.zerobase.morse.repository.MemberRepository;
import com.zerobase.morse.repository.StudyRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class StudyService {

	private final StudyRepository studyRepository;
	private final KeywordRepository keywordRepository;
	private final MemberRepository memberRepository;

	/**
	 * 전체 게시글 가져오기
	 */
	public List<Study> getAllStudy() {
		return studyRepository.findAll();
	}

	/**
	 * 특정 게시글 가져오기
	 */
	public Optional<Study> getStudy(int id) {
		return studyRepository.findById(id);
	}

	/**
	 * 게시글 저장
	 */
	public Study saveStudy(StudyInput s) throws ParseException {
		String loginEmail = this.getLoginUser();
		Member member = memberRepository.findByEmail(loginEmail);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(s.getDueDt());

		Study study = studyRepository.save(Study.builder()
			.email(member)
			.title(s.getTitle())
			.category(s.getCategory())
			.subCategory(s.getSubCategory())
			.keyword(s.getKeyword())
			.description(s.getDescription())
			.status(true)
			.cancelStatus(false)
			.writeDt(LocalDateTime.now())
			.dueDt(date)
			.applicantNums(s.getNumPeople())
			.build());
		return study;
	}

	/**
	 * 로그인 된 객체가져오기
	 */
	public String getLoginUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;

		System.out.println("==================== " + userDetails.getUsername() + "==================== " );
		return userDetails.getUsername();
	}

	/**
	 * 수정된 게시물 저장
	 */
	public void saveUpdatedStudy(Study study) {


		studyRepository.save(study);
	}

	/**
	 * 해당 스터디 공고 삭제
	 */
	public void deleteStudy(int id) {
		studyRepository.deleteById(id);
	}
}
