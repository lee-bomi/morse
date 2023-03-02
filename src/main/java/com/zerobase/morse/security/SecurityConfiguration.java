package com.zerobase.morse.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final JwtAuthenticationFilter authenticationFilter;

  @Bean
  MemberAuthenticationHandler memberAuthenticationHandler() {
    return new MemberAuthenticationHandler();
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().disable()
        .csrf().disable()
        .formLogin().disable()
        .logout().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/", "/register","/login","/**/signup", "/**/signin").permitAll()
        .and()
        .authorizeRequests()
        .antMatchers("/temp","/chat","/studylist","/inquiry")
        .authenticated()
        .and()
        .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class);

  }

  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
