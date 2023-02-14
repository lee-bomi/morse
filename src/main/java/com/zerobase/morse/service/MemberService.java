package com.zerobase.morse.service;

import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member register(MemberInput m) {

        String encPw = "";
        if (m.getPassword().equals(m.getRepassword())) {            // 비밀번호가 일치할경우에만 암호화하여 저장
            encPw = BCrypt.hashpw(m.getPassword(), BCrypt.gensalt());
        } else {
            //예외처리
        }

        String date = "";
        if (m.getGender() == 1 || m.getGender() == 2) {
            date = "19".concat(m.getBirth());
        } else {
            date = "20".concat(m.getBirth());
        }

        return memberRepository.save(
                Member.builder()
                    .email(m.getEmail())
                    .password(encPw)
                    .name(m.getName())
                    .nickname(m.getNickname())
                    .gender(gender)
                    .birth(date)
                    .address(m.getAddress())
                    .phone(toPhone(m.getPhone()))
                    .regDt(LocalDateTime.now())
                    .emailYn(false)
                    .status(true)   //정상회원
                    .reportCount(0)
                    .snsLogin("email")
                    .build());
    }

    /**
     * 숫자를 전화번호 형태로 변환
     * @param number
     * @return
     */
    public String toPhone(String number) {
        if (number == null) {
            return "";
        }
        if (number.length() == 8) {
            return number.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
        } else if (number.length() == 12) {
            return number.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
        }
        return number.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
    }


}
