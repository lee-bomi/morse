package com.zerobase.morse.repository;

import com.zerobase.morse.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, String> {

  JwtToken findByEmail(String email);

  boolean existsByRefreshToken(String refreshToken);
}
