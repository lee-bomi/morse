package com.zerobase.morse.model;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInput {

    @NotNull
    @Email(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$",message="이메일형식으로입력해주세요")
    String email;

    @NotNull
    @Size(min=1,max=8)
    String name;

    @NotNull
    @Size(min=1,max=8)
    String nickname;

    @NotBlank(message="비밀번호는필수입력값입니다")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message="비밀번호는영문대,소문자와숫자,특수기호가적어도1개이상씩포함된8자~20자의비밀번호여야합니다.")
    String password;

    @NotBlank(message="비밀번호는필수입력값입니다")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message="비밀번호는영문대,소문자와숫자,특수기호가적어도1개이상씩포함된8자~20자의비밀번호여야합니다.")
    String repassword;

    @NotBlank(message="생년월일6자리를입력해주세요")
    @Size(min=6,max=6)
    String birth;

    @NotBlank(message="주민번호뒷자리중첫글자를입력해주세요")
    @Size(min=1,max=1)
    int gender;

    @NotBlank(message="하이픈(-)을생략하여입력해주세요")
    @Size(min=10,max=11)
    String phone;

    @NotBlank(message="주소검색버튼을클릭해주세요")
    @Size(max=50)
    String address;
}
