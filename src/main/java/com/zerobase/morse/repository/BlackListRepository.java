package com.zerobase.morse.repository;

import com.zerobase.morse.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository extends JpaRepository<BlackList,String> {

  boolean existsByAccessToken(String accessToken);
}
