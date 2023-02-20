package com.zerobase.morse.security;

import com.zerobase.morse.service.MemberService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class TokenProvider {

  private static final long TOKEN_EXPRIRE_TIME = 1000 * 60 * 60; //1시간

  private final MemberService memberService;

  @Value("{spring.jwt.secret}")
  private String secretKey;

  public String generateToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);

    Date now = new Date();
    Date expiredDate = new Date(now.getTime() + TOKEN_EXPRIRE_TIME);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
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
