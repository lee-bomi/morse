package com.zerobase.morse.controller;

import com.zerobase.morse.component.PasswordEncoderComponent;
import com.zerobase.morse.entity.Study;
import com.zerobase.morse.model.StudyInput;
import com.zerobase.morse.model.StudyUpdate;
import com.zerobase.morse.service.StudyService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class StudyController {

	private final StudyService studyService;

	private final PasswordEncoder passwordEncoder;
	@PostMapping("/member/study/add")
	public ResponseEntity<?> registerStudy(StudyInput studyInput) throws ParseException {
		Study study = studyService.saveStudy(studyInput);
		return ResponseEntity.ok().body(study);
	}

	@GetMapping("/study")
	public String studyAddPage() {

		System.out.println("11111 -> "+passwordEncoder.encode("11111"));
		System.out.println("22222 -> "+passwordEncoder.encode("22222"));

		return "/study";
	}

	//전체스터디리스트(권한없어도 누구나 확인가능)
	@GetMapping("/studylist")
	public String studyList(Model model) {
		List<Study> studies = studyService.getAllStudy();

		model.addAttribute("studies",studies);

		return "/studylist";
	}

	//특정 게시글 수정페이지 랜딩요청
	@GetMapping("/studylist/{id}")
	public String updateStudyPage(@PathVariable Long id, Model model) {
		Study study = studyService.getStudy(id).orElseThrow(
			() -> new RuntimeException("해당 게시물이 없습니다"));
		model.addAttribute("study", study);
		return "/updateStudy";
	}

	//게시글 수정요청
	@PostMapping("/member/study/update")
	public ResponseEntity<?> updateStudy(StudyUpdate s)
		throws ParseException {
		Study study = studyService.getStudy(s.getStudyNo()).orElseThrow(
			() -> new RuntimeException("해당 게시물이 없습니다"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(s.getDueDt());

		study.setTitle(s.getTitle());
		study.setCategory(s.getCategory());
		study.setSubCategory(s.getSubCategory());
		study.setKeyword(s.getKeyword());
		study.setDescription(s.getDescription());
		study.setDueDt(date);

		studyService.saveUpdatedStudy(study);
		return ResponseEntity.ok().body(study);
	}

	//게시글 삭제
	@DeleteMapping("/studyList/{id}")
	public ResponseEntity<?> deleteStudy(@PathVariable Long id) {
		studyService.deleteStudy(id);
		return ResponseEntity.ok().build();
	}
}
