package com.ssafy.joblog.domain.alarm.controller;

import com.ssafy.joblog.domain.alarm.service.AlarmService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @DeleteMapping("/delete/{alarmId}")
    public StatusResponseDto markDeletedAlarm(@PathVariable int alarmId) {
        alarmService.markDeletedAlarm(alarmId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

}
