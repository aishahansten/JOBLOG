package com.ssafy.joblog.domain.alarm.controller;

import com.ssafy.joblog.domain.alarm.dto.response.AlarmResponseDto;
import com.ssafy.joblog.domain.alarm.entity.Alarm;
import com.ssafy.joblog.domain.alarm.service.AlarmService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<AlarmResponseDto>> findAllAlarm(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(alarmService.getAlarms(userId));
    }

    @GetMapping("/detail/{alarmId}")
    public ResponseEntity<AlarmResponseDto> findAlarm(@PathVariable(value = "alarmId") int alarmId) {
        return ResponseEntity.ok(alarmService.getAlarm(alarmId));
    }

    @DeleteMapping("/delete/{alarmId}")
    public StatusResponseDto markDeletedAlarm(@PathVariable int alarmId) {
        alarmService.markDeletedAlarm(alarmId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

}
