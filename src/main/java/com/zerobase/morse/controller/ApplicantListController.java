package com.zerobase.morse.controller;

import com.zerobase.morse.service.ApplicantListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class ApplicantListController {

  private final ApplicantListService applicantListService;

  @PostMapping("/permit")
  public @ResponseBody void permitApplicant(){
    //신청자 얻기
    //신청자를 스터디 채팅방에 추가하기
    //결과 반환
  }

}
