package com.ssafy.joblog.domain.schedule.service;

import com.ssafy.joblog.domain.board.dto.response.PostResponseDto;
import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.ssafy.joblog.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.ssafy.joblog.domain.schedule.dto.response.ScheduleResponseDto;
import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.schedule.repository.ScheduleRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Data
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    // 1. 개인 일정 등록하기
    public void createSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto) {
        // 작성한 유저 불러옴

        User user = userRepository.findById(scheduleCreateRequestDto.getUserId()).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Schedule schedule = scheduleCreateRequestDto.createSchedule(user);
        scheduleRepository.save(schedule);
    }

    // 2. 개인 전체 조회하기
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedules(Integer userId) {
        List<Schedule> schedules = scheduleRepository.findByUserId(userId);
        List<ScheduleResponseDto> getSchedulesList = new ArrayList<>();
        schedules.forEach(schedule -> getSchedulesList.add(schedule.toScheduleResponseDto()));
        return getSchedulesList;
    }

    // 3. 개인 일정 상세 조회하기
    @Transactional(readOnly = true)
    public ScheduleResponseDto getSchedule(Integer scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다"));
        ScheduleResponseDto scheduleResponseDto = schedule.toScheduleResponseDto();
        return scheduleResponseDto;
    }

    // 4. 개인 일정 수정하기
    @Transactional
    public void updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(scheduleUpdateRequestDto.getScheduleId())
                .orElseThrow(()-> new IllegalArgumentException("해당 일정이 존재하지 않습니다"));

        schedule.updateSchedule(scheduleUpdateRequestDto);
    }

    // 5. 개인 일정 삭제하기
    @Transactional
    public void markDeletedSchedule(int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 일정을 찾을 수 없습니다");
        });
        scheduleRepository.markDeletedSchedule(scheduleId);
    }
}