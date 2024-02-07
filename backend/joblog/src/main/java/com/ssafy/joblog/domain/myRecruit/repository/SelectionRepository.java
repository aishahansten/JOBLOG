package com.ssafy.joblog.domain.myRecruit.repository;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.resume.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SelectionRepository  extends JpaRepository<Selection, Integer> {
    Optional<Selection> findByIdAndIsDeleteIsFalse(int id);

    List<Selection> findAllSelectionByMyRecruitIdAndIsDeleteIsFalseOrderByStep(int id);

    @Query("SELECT s FROM Selection s WHERE s.user.id = :id AND s.reviewOrNot = true AND s.isDelete = false")
    List<Selection> findAllSelectionByUserIdAndIsDeleteIsFalseAndReviewOrNotIsTrue(@Param("id") int id);

    @Query("SELECT s FROM Selection s WHERE s.companyCode = :code AND s.reviewOrNot = true AND s.isDelete = false")
    List<Selection> findAllSelectionByCompanyCodeAndIsDeleteIsFalseAndReviewOrNotIsTrue(@Param("code") Long code);

    List<Selection> findAllByProgressDateBetweenAndIsDeleteFalse(LocalDateTime today, LocalDateTime tomorrow);
}
