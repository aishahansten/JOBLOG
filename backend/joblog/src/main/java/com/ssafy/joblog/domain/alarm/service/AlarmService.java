package com.ssafy.joblog.domain.alarm.service;

import com.ssafy.joblog.domain.alarm.dto.request.AlarmCreateRequestDto;
import com.ssafy.joblog.domain.alarm.dto.response.AlarmResponseDto;
import com.ssafy.joblog.domain.alarm.entity.Alarm;
import com.ssafy.joblog.domain.alarm.kafka.service.ProducerService;
import com.ssafy.joblog.domain.alarm.repository.AlarmRepository;
import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.schedule.repository.ScheduleRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class AlarmService {

    private final ProducerService producerService;
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //커피챗 생성
    public void createAlarm(AlarmCreateRequestDto alarmCreateRequestDto) {
        User user = userRepository.findById(alarmCreateRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        Alarm alarm = alarmCreateRequestDto.createAlarm(user);
        alarmRepository.save(alarm);
    }

//    public List<AlarmResponseDto> getAlarms(int userId) {
//        List<Alarm> alarms = alarmRepository.findByUserId(userId);
//        List<AlarmResponseDto> alarmResponseList = new ArrayList<>();
//        alarms.forEach(alarm -> alarmResponseList.add(AlarmResponseDto.builder()
//                        .alarmId(alarm.getId())
//                        .userId(alarm.getUser().getId())
//                        .
//                .build()));
//    }

    public void markCheckedAlarm(int alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 알림을 찾을 수 없습니다");
        });
        alarmRepository.markCheckedAlarm(alarmId);
    }

    public void markDeletedAlarm(int alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 알림을 찾을 수 없습니다");
        });
        alarmRepository.markDeletedAlarm(alarmId);
    }

//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0/10 * * * * *")
    public void sendScheduleAlarm() {
        LocalDateTime today = LocalDateTime.now();
        today = today.minusDays(1);
        LocalDateTime tomorrow = today.plusDays(1);
        List<Schedule> schedules = scheduleRepository.findAllByEndDateBetween(today, tomorrow);
        schedules.forEach(schedule -> {
            System.out.println("ASDFASDFA");
            User user = userRepository.findById(schedule.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            producerService.upcomingScheduleAlarm(user.getId(), "오늘 " + schedule.getTitle());
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .schedule(schedule)
                    .build();
            alarmRepository.save(alarm);
        });
    }

}
