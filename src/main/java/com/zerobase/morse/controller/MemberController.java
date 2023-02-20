package com.zerobase.morse.controller;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.LoginResponse;
import com.zerobase.morse.model.LoginInput;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.security.TokenProvider;
import com.zerobase.morse.service.MemberService;
import lombok.RequiredArgsConstructor;
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
    public Member register(@RequestBody MemberInput input) {
        return memberService.register(input);
    }

    @GetMapping("/email-auth")
    public String ReqEmailAuth(@RequestParam String uuid) {
        Member member = memberService.checkAuthKey(uuid);
        memberService.changeEmailYn(member);

        return "이메일 인증완료";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public @ResponseBody LoginResponse login(@RequestBody LoginInput parameter) {
        System.out.println(parameter.getUsername());
        System.out.println(parameter.getPassword());
        LoginResponse loginResponse = this.memberService.login(parameter);

        if(loginResponse.isResult()){
            loginResponse.setToken(this.tokenProvider.generateToken(parameter.getUsername()));
        }
        System.out.println(loginResponse.isResult());

        return loginResponse;
    }

    /**
     * jwt토큰 인증을 위한 실헝용 페이지입니다.
     * @return
     */
    @GetMapping("/temp")
    public String get(){
        return "temp";
    }
}
