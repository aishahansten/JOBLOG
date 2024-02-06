package com.ssafy.joblog.domain.schedule.repository;

import com.ssafy.joblog.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE Schedule schedule SET schedule.isDelete = true WHERE schedule.id = :id")
    void markDeletedSchedule(@Param("id") int id);

    List<Schedule> findAllByEndDateBetween(LocalDateTime endDate1, LocalDateTime endDate2);
}