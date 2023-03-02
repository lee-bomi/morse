/*
package com.zerobase.morse.config;

import com.zerobase.morse.security.CustomUserDetailService;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

  private final DataSource dataSource;
  private final CustomUserDetailService userDetailService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // 선택 경로에 필터를 적용해서 선택 자원에 필요한 권한이나 로그인 여부를 확인하는 필터
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("---------------configure---------------");

    //화면에서 로그인 진행 + 커스텀 로그인 페이지
    http.formLogin().loginPage("/member/login");
    //CSRF 토큰 비활성화
    http.csrf().disable();
    //자동로그인
    http.rememberMe()
        .key("12345678")
        .tokenRepository(persistentTokenRepository())
        .userDetailsService(userDetailService)
        .tokenValiditySeconds(60 * 60 * 24 * 30);

    return http.build();
  }

  private PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
    repo.setDataSource(dataSource);
    return repo;
  }

  // 정적으로 동작하는 파일에는 굳이 시큐리티를 적용할 필요가 없다.
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {

    log.info("---------------- Ignoring staticResource from security------------- ");


    return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }
}

 */
