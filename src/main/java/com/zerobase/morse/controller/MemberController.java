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

    @PostMapping("/register")
    public Member register(@RequestBody MemberInput input) {
        return memberService.register(input);
    }
}
