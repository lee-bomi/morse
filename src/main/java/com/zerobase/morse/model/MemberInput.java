package com.zerobase.morse.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInput {

    @NotNull
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message = "이메일 형식으로 입력해주세요")
    String email;

    @NotNull
    @Size(min = 1, max = 8)
    String name;

    @NotNull
    @Size(min = 1, max = 8)
    String nickname;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    String password;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    String repassword;

    @NotBlank(message = "생년월일 6자리를 입력해주세요")
    @Size(min = 6, max = 6)
    int birth;

    @NotBlank(message = "주민번호 뒷자리 중 첫글자를 입력해주세요")
    @Size(min = 1, max = 1)
    int gender;

    @NotBlank(message = "하이픈(-)을 생략하여 입력해주세요")
    @Size(min = 10, max = 11)
    String phone;

    @NotBlank(message = "주소검색버튼을 클릭해주세요")
    @Size(max = 50)
    String address;
}
