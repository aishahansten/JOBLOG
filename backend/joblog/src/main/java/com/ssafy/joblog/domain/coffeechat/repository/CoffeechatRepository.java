package com.ssafy.joblog.domain.coffeechat.repository;

import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeechatRepository extends JpaRepository<CoffeeChatRoom, Integer> {

}
