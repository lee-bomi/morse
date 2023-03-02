package com.zerobase.morse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class StudyController {

  /**
   * 스터디 리스트를 볼 수 있는 임시용 페이지입니다.
   * @return 스터디리스트 페이지
   */
  @GetMapping("/studylist")
  public String studyList(){
    return "studylist";
  }


}
