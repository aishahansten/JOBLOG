package com.ssafy.joblog.domain.myRecruit.repository;

import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.resume.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MyRecruitRepository  extends JpaRepository<MyRecruit, Integer> {
    Optional<MyRecruit> findByUserIdAndRecruitId(int userId, Long recruitId);

    Optional<MyRecruit> findByIdAndIsDeleteIsFalse(int id);

    List<MyRecruit> findAllMyRecruitByUserIdAndIsDeleteIsFalseOrderByCreatedDateDesc(int id);

    List<MyRecruit> findAllByEndDateBetweenAndIsDeleteFalse(LocalDateTime today, LocalDateTime tomorrow);

}
