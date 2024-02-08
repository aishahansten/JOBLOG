package com.ssafy.joblog.domain.essay.repository;

import com.ssafy.joblog.domain.essay.entity.Essay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EssayRepository extends JpaRepository<Essay, Integer> {

    // 전체조회 is_delete=0인 경우만 조회
    List<Essay> findByUserIdAndIsDeleteIsFalse(Integer userId);
    // 상세조회 is_delete=0인 경우만 조회
    Optional<Essay> findByIdAndIsDeleteIsFalse(Integer id);

    // delete
    @Modifying
    @Query("UPDATE Essay essay SET essay.isDelete = true WHERE essay.id = :id")
    void markDeletedEssay(@Param("id") int id);
}
