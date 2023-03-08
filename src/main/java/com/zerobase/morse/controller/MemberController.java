package com.zerobase.morse.controller;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.LoginResponse;
import com.zerobase.morse.model.LoginInput;
import com.zerobase.morse.model.LoginResponse;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.security.TokenProvider;
import com.zerobase.morse.service.MemberService;
import java.security.Principal;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;
  private final TokenProvider tokenProvider;


  @GetMapping("/join")
  public String joinPage() {
    return "join";
  }

  @PostMapping("/repeat") //회원가입 이메일 중복확인
  public boolean repeat(String email) {
    return memberService.repeat(email);
  }

  @PostMapping("/register")
  public ResponseEntity<Member> register(@RequestBody MemberInput input) throws ParseException {
      return ResponseEntity.ok().body(memberService.register(input));
  }

  @GetMapping("/email-auth")
  public ResponseEntity<String> ReqEmailAuth(@RequestParam String uuid) {
      Member member = memberService.checkAuthKey(uuid);
      memberService.changeEmailYn(member);

    return ResponseEntity.ok().body("이메일인증완료");
  }

  @GetMapping("/login")
  public String loginPage() {
    return "login";
  }

  @PostMapping("/login")
  public @ResponseBody LoginResponse login(@RequestBody LoginInput parameter) {

    LoginResponse loginResponse = this.memberService.login(parameter);

    if (loginResponse.isResult()) {
      String username = parameter.getUsername();
      loginResponse.setAccessToken(this.tokenProvider.generateAccessToken(username));
      loginResponse.setRefreshToken(this.tokenProvider.generateRefreshToken(username));

    }
    System.out.println(parameter.getUsername()+", "+parameter.getPassword());
    System.out.println(loginResponse.getAccessToken());

    return loginResponse;
  }


  @PostMapping("/logout")
  public String logout(HttpServletRequest request){

    String accessToken = this.tokenProvider.getTokenFromCookies(request,"access_token");
    this.memberService.logout(accessToken);

    return "redirect:/";
  }

  /**
   * jwt토큰 인증을 위한 실헝용 페이지입니다.
   *
   * @return
   */
  @GetMapping("/temp")
  public String get() {
    return "temp";
  }

}
