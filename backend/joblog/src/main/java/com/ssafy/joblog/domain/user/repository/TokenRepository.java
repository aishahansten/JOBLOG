package com.ssafy.joblog.domain.user.repository;

import com.ssafy.joblog.domain.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Integer>  {

}
