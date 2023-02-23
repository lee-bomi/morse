package com.zerobase.morse.security;

import com.zerobase.morse.entity.JwtToken;
import com.zerobase.morse.repository.JwtTokenRepository;
import com.zerobase.morse.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000; //1시간
  private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 6; //6시간
  private final MemberService memberService;
  private final JwtTokenRepository jwtTokenRepository;


  @Value("{spring.jwt.secret}")
  private String secretKey;

  public String generateAccessToken(String username) {
    return this.generateToken(username, ACCESS_TOKEN_EXPIRE_TIME);
  }

  public String generateRefreshToken(String username) {

    String refreshToken = this.generateToken(username, REFRESH_TOKEN_EXPIRE_TIME);
    this.saveRefreshToken(username, refreshToken);
    return refreshToken;
  }

  public String generateToken(String username, long expireTime) {
    Claims claims = Jwts.claims().setSubject(username);

    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + expireTime);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
  }

  private void saveRefreshToken(String username, String refreshToken) {
    JwtToken jwtToken = this.jwtTokenRepository.findByEmail(username);
    jwtToken.setRefreshToken(refreshToken);
    jwtToken = jwtTokenRepository.save(jwtToken);
    System.out.println(jwtToken.getRefreshToken());
  }

  /**
   * @param request 클라이언트에서 서버로 보내는 요청
   * @param tokenName 얻으려고하는 토큰 이름 access_token, refresh_token 둘 중 하나로 입력
   * @return access_token 또는 refresh_token
   */
  public String getTokenFromCookies(HttpServletRequest request,String tokenName) {
    Cookie[] cookies = request.getCookies();

    String token = "";
    for (Cookie c : cookies) {
      if (c.getName().equals(tokenName)) {
        token = c.getValue();
      }
    }

    if (!ObjectUtils.isEmpty(token)) {
      return token;
    }

    return null;
  }


  private Claims parseClaims(String token) {
    try {
      return Jwts.parser().setSigningKey(this.secretKey)
          .parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  public String getUsername(String token) {
    return this.parseClaims(token).getSubject();
  }

  public boolean validateToken(String token) {
    if (!StringUtils.hasText(token)) {
      return false;
    }

    Claims claims = this.parseClaims(token);
    return !claims.getExpiration().before(new Date());
  }


  public Authentication getAuthentication(String token) {
    String username = this.getUsername(token);
    UserDetails userDetails = this.memberService.loadUserByUsername(username);

    return new UsernamePasswordAuthenticationToken(userDetails, "",
        userDetails.getAuthorities());
  }


}
