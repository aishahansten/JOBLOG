package com.ssafy.joblog.domain.schedule.controller;

import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.schedule.dto.request.ScheduleCreateRequestDto;
import com.ssafy.joblog.domain.schedule.dto.request.ScheduleUpdateRequestDto;
import com.ssafy.joblog.domain.schedule.dto.response.ScheduleResponseDto;
import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.schedule.service.ScheduleService;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/schedule")
@RequiredArgsConstructor
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    // 1. 개인 일정 등록하기
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createSchedule(@RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        scheduleService.createSchedule(scheduleCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 개인 전체 조회하기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSchedules(@PathVariable Integer userId) {
        return new ResponseEntity<>(scheduleService.getSchedules(userId), HttpStatus.OK);
    }

    // 3. 개인 일정 상세 조회하기
    @GetMapping("/detail/{scheduleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getSchedule(@PathVariable Integer scheduleId) {
        return new ResponseEntity<>(scheduleService.getSchedule(scheduleId), HttpStatus.OK);
    }

    // 4. 개인 일정 수정하기
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateSchedule(@RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        scheduleService.updateSchedule(scheduleUpdateRequestDto);
        String message = "Ok";
        return new StatusResponseDto(200, message);
    }

    // 5. 개인 일정 삭제하기
    @DeleteMapping("/delete/{scheduleId}")
    public StatusResponseDto markDeletedSchedule(@PathVariable Integer scheduleId) {
        scheduleService.markDeletedSchedule(scheduleId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

}