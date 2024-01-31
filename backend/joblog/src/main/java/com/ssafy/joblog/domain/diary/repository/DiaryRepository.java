package com.ssafy.joblog.domain.diary.repository;

import com.ssafy.joblog.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findByUserId(Integer userId);

}