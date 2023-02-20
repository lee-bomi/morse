package com.zerobase.morse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 로그인 후 jwt 발급을 위해 만든 클래스입니다.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LoginResponse {

  private String token;

  private String message;

  private boolean result;

}
