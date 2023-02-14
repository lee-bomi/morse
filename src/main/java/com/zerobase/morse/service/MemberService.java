package com.zerobase.morse.service;

import com.zerobase.morse.component.MailComponent;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MailComponent mailComponent;

    /**
     * 이메일 중복확인(중복확인 버튼 => ajax사용해야할듯...)
     * @param email
     * @return
     */
    public boolean repeat(String email) {
        return memberRepository.existsByEmail(email);
    }

    /**
     * 회원가입
     * @param m
     * @return
     */
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

        //일련번호 생성
        String uuid = UUID.randomUUID().toString();

        Member member = memberRepository.save(
                Member.builder()
                        .email(m.getEmail())
                        .password(encPw)
                        .name(m.getName())
                        .nickname(m.getNickname())
                        .gender((m.getGender() == 1 || m.getGender() == 3) ? '남' : '여')
                        .birth(date)
                        .address(m.getAddress())
                        .phone(toPhone(m.getPhone()))
                        .regDt(LocalDateTime.now())
                        .emailYn(false)
                        .emailAuthKey(uuid)
                        .status(true)   //정상회원
                        .reportCount(0)
                        .snsLogin("email")
                        .build());

        sendMail(m.getEmail(), uuid);

        return member;
    }

    /**
     * email_auth_key 일치여부 확인
     */
    public Member checkAuthKey(String uuid) {
        return memberRepository.findByEmailAuthKey(uuid);
    }

    /**
     * email_yn 변경
     */
    public void changeEmailYn(Member member) {
        member.setEmailYn(true);
        memberRepository.save(member);
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

    /**
     * 메일 발송
     */
    public void sendMail(String email, String uuid) {

        String subject = "모든스터디 가입 인증메일";
        String text = "<p>가입축하드려요! 아래링크 클릭하셔서 가입을 완료하세요</p>"
                + "<div><a href='http://localhost:8080/email-auth?uuid=" + uuid + "'>가입인증하기</a></div>";

        mailComponent.sendMail(email, subject, text);
    }


}
