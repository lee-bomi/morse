
package com.zerobase.morse.security;

import com.zerobase.morse.repository.BlackListRepository;
import com.zerobase.morse.repository.JwtTokenRepository;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 클라이언트가 인증이 필요한 페이지에 요청시
 * 브라우저에서 쿠키로 보낸 jwt 토큰으로 사용자 인증을 확인하는 필터입니다.
 * SecurityConfiguration에 설정되어 있습니다.
 */

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String ACCESS_TOKEN = "access_token";
  public static final String REFRESH_TOKEN = "refresh_token";
  private final TokenProvider tokenProvider;
  private final BlackListRepository blackListRepository;
  private final JwtTokenRepository jwtTokenRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    // accessToken, refreshToken 가져오기
    String accessToken = this.tokenProvider.getTokenFromCookies(request, ACCESS_TOKEN);
    String refreshToken = this.tokenProvider.getTokenFromCookies(request, REFRESH_TOKEN);


    if(StringUtils.hasText(accessToken)&& tokenProvider.validateToken(accessToken)){

      boolean isLogout = this.blackListRepository.existsByAccessToken(accessToken);

      if(!isLogout){
        Authentication auth = this.tokenProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
      }

    }

    filterChain.doFilter(request, response);

  }

}
