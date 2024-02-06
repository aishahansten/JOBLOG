package com.ssafy.joblog.domain.coffeechat.repository;

import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<CoffeeChatRoom, Integer> {

    // isDelete=0 인 경우만 조회하도록 하는 쿼리문
    List<CoffeeChatRoom> findByChatterIdAndIsDeleteIsFalseOrChatteeIdAndIsDeleteIsFalse(Integer chatterId, Integer chatteeId);

    // 커피챗 수락하기
    @Modifying
    @Query("UPDATE CoffeeChatRoom chat SET chat.acceptOrNot = true WHERE chat.id = :id")
    void markAcceptChat(@Param("id") int id);

    @Modifying
    @Query("UPDATE CoffeeChatRoom chat SET chat.isDelete = false WHERE chat.id = :id")
    void markShowChat(@Param("id") int id);

    // 커피챗 거절하기
    @Modifying
    @Query("UPDATE CoffeeChatRoom chat SET chat.isDelete = true WHERE chat.id = :id")
    void markNoshowChat(@Param("id") int id);

    @Modifying
    @Query("UPDATE CoffeeChatRoom chat SET chat.acceptOrNot = false WHERE chat.id = :id")
    void markUnacceptChat(@Param("id") int id);

}
