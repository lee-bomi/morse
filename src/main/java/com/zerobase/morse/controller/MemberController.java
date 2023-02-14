package com.zerobase.morse.controller;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
}
