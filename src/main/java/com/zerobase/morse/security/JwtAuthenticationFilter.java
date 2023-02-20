
package com.zerobase.morse.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 클라이언트가 인증이 필요한 페이지에 요청시 브라우저에서 쿠키로 보낸 jwt 토큰으로 사용자 인증을 확인하는 필터입니다. SecurityConfiguration에 설정되어
 * 있습니다.
 */

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  public static final String JWT_COOKIE = "jwt";
  private final TokenProvider tokenProvider;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = this.resolveTokenFromRequest(request);

    if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {

      Authentication auth = this.tokenProvider.getAuthentication(token);
      SecurityContextHolder.getContext().setAuthentication(auth);
    }

    filterChain.doFilter(request, response);

  }

  private String resolveTokenFromRequest(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    String token = "";
    for (Cookie c : cookies) {
      if (c.getName().equals(JWT_COOKIE)) {
        token = c.getValue();
      }
    }

    if (!ObjectUtils.isEmpty(token)) {
      return token;
    }

    return null;
  }
}
