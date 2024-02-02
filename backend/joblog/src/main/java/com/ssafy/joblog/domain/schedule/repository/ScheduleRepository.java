package com.ssafy.joblog.domain.schedule.repository;

import com.ssafy.joblog.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByUserId(Integer userId);
}