package com.zerobase.morse.service;

import com.zerobase.morse.component.MailComponent;
import com.zerobase.morse.entity.BlackList;
import com.zerobase.morse.entity.Member;
import com.zerobase.morse.model.LoginInput;
import com.zerobase.morse.model.LoginResponse;
import com.zerobase.morse.model.MemberInput;
import com.zerobase.morse.repository.BlackListRepository;
import com.zerobase.morse.repository.MemberRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;

  private final MemberRepository memberRepository;

  private final BlackListRepository blackListRepository;
  private final MailComponent mailComponent;

  /**
   * 이메일 중복확인(중복확인 버튼 => ajax사용해야할듯...)
   *
   * @param email
   * @return
   */
  public boolean repeat(String email) {
    return memberRepository.existsByEmail(email);
  }

  /**
   * 회원가입
   *
   * @param m
   * @return
   */
  public Member register(MemberInput m) throws ParseException {

    String encPw = "";
    if (m.getPassword().equals(m.getRepassword())) {            // 비밀번호가 일치할경우에만 암호화하여 저장
      encPw = BCrypt.hashpw(m.getPassword(), BCrypt.gensalt());
    } else {
      //예외처리
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = sdf.parse(m.getBirth());


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
   *
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


  public LoginResponse login(LoginInput parameter) {
    Member member = this.memberRepository.getById(parameter.getUsername());
    if (ObjectUtils.isEmpty(member) || !this.passwordEncoder.matches(parameter.getPassword(),
        "$2a$10$HpPHhzop7HjJVW9dYHncwuklRTc7AP.b1f.Rqz14lnim68uwqk0fm")) {
      return LoginResponse.builder()
          .message("아이디 또는 비밀번호가 일치하지 않습니다.")
          .result(false)
          .build();
    }

    return LoginResponse.builder()
        .message("로그인에 성공했습니다.")
        .result(true)
        .build();
  }

  public void logout(String token) {
    this.blackListRepository.save(
            BlackList.builder()
                      .accessToken(token)
                      .build()
    );
  }

  public Member getMember(String email){
    return this.memberRepository.getById(email);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Member> optionalMember = memberRepository.findById(username);
    if (optionalMember.isEmpty()) {
      throw new UsernameNotFoundException("can't find " + username);
    }

    Member member = optionalMember.get();
    if (!member.isEmailYn()) {
      //이메일 미인증 예외 처리 추후 수정
      throw new RuntimeException("이메일 인증 후 로그인해 주세요.");
    }

    if (member.isStatus()) {
      //계정 정지 예외 처리 추후 수정
      throw new RuntimeException("정지된 계정입니다. 고객센터에 문의해 주세요.");
    }

    //data.sql에 비밀번호 암호화 되지 않아서 로그인 성공을 위해 임시로 작성.
    //String password = passwordEncoder.encode(member.getPassword()); 로 써야함.
    //12345로 적어야 됨.
    return new User(member.getEmail(),
        "$2a$10$HpPHhzop7HjJVW9dYHncwuklRTc7AP.b1f.Rqz14lnim68uwqk0fm", member.getAuthorities());
  }

}
