package com.ssafy.joblog.domain.diary.repository;

import com.ssafy.joblog.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE Diary diary SET diary.isDelete = true WHERE diary.id = :id")
    void markDeletedDiary(@Param("id") int id);

}