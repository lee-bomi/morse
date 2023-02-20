package com.zerobase.morse.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 정보를 입력받기 위해 만든 클래스입니다.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginInput {

  private String username;
  private String password;
}
