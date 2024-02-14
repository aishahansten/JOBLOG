package com.ssafy.joblog.domain.alarm.service;

import com.ssafy.joblog.domain.alarm.dto.response.AlarmResponseDto;
import com.ssafy.joblog.domain.alarm.entity.Alarm;
import com.ssafy.joblog.domain.alarm.kafka.service.ProducerService;
import com.ssafy.joblog.domain.alarm.repository.AlarmRepository;
import com.ssafy.joblog.domain.coffeechat.entity.CoffeeChatRoom;
import com.ssafy.joblog.domain.coffeechat.repository.ChatRepository;
import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.repository.SelectionRepository;
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
    private final ChatRepository chatRepository;
    private final ScheduleRepository scheduleRepository;
    private final MyRecruitRepository myRecruitRepository;
    private final SelectionRepository selectionRepository;

    public List<AlarmResponseDto> getAlarms(int userId) {
        List<Alarm> alarms = alarmRepository.findByUserIdAndIsDeleteFalseOrderByCreatedDateDesc(userId);
        List<AlarmResponseDto> alarmResponseList = new ArrayList<>();
        alarms.forEach(alarm -> alarmResponseList.add(AlarmResponseDto.fromEntity(alarm)));
        return alarmResponseList;
    }

    public AlarmResponseDto getAlarm(int alarmId) {
        Alarm alarm = alarmRepository.findByIdAndIsDeleteFalse(alarmId).orElseThrow(() -> new IllegalArgumentException("해당 알림이 존재하지 않습니다"));
        alarmRepository.markCheckedAlarm(alarmId);
        return AlarmResponseDto.fromEntity(alarm);
    }

    public void markDeletedAlarm(int alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 알림을 찾을 수 없습니다");
        });
        alarmRepository.markDeletedAlarm(alarmId);
    }

    public void sendChatCreateAlarm(int userId, int chatId) {
        User user = userRepository.findById(userId).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
        CoffeeChatRoom coffeeChatRoom = chatRepository.findById(chatId).orElseThrow(() -> { return new IllegalArgumentException("커피챗이 존재하지 않습니다."); });
        Alarm alarm = Alarm.builder()
                .user(user)
                .coffeeChatRoom(coffeeChatRoom)
                .build();
        alarmRepository.save(alarm);
        producerService.chatCreateAlarm(userId, "커피챗 신청");
    }

    public void sendChatRejectAlarm(int userId, int chatId) {
        User user = userRepository.findById(userId).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
        CoffeeChatRoom coffeeChatRoom = chatRepository.findById(chatId).orElseThrow(() -> { return new IllegalArgumentException("커피챗이 존재하지 않습니다."); });
        Alarm alarm = Alarm.builder()
                .user(user)
                .coffeeChatRoom(coffeeChatRoom)
                .build();
        alarmRepository.save(alarm);
        producerService.chatRejectAlarm(userId, "커피챗 거절");
    }

//    @Scheduled(cron = "0 0 0 * * *")
    @Scheduled(cron = "0/10 * * * * *")
    public void sendScheduleAlarm() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        List<Schedule> schedules = scheduleRepository.findAllByEndDateBetweenAndIsDeleteFalse(today, tomorrow);
        schedules.forEach(schedule -> {
            User user = userRepository.findById(schedule.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .schedule(schedule)
                    .build();
            alarmRepository.save(alarm);
            producerService.upcomingScheduleAlarm(user.getId(), "오늘 일정 : " + schedule.getTitle());
        });
    }

    @Scheduled(cron = "0 20 0 * * *")
    public void sendMyRecruitAlarm() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        List<MyRecruit> myRecruits = myRecruitRepository.findAllByExpirationDateBetweenAndIsDeleteFalse(today, tomorrow);
        myRecruits.forEach(myRecruit -> {
            User user = userRepository.findById(myRecruit.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .myRecruit(myRecruit)
                    .build();
            alarmRepository.save(alarm);
            producerService.upcomingMyRecruitAlarm(user.getId(), "오늘 마감 채용 : " + myRecruit.getTitle());
        });
    }

    @Scheduled(cron = "0 20 1 * * *")
    public void sendMyRecruitReviewAlarm() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        List<MyRecruit> myRecruits = myRecruitRepository.findAllByExpirationDateBetweenAndIsDeleteFalse(yesterday, today);
        myRecruits.forEach(myRecruit -> {
            User user = userRepository.findById(myRecruit.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .myRecruit(myRecruit)
                    .build();
            alarmRepository.save(alarm);
            producerService.upcomingMyRecruitAlarm(user.getId(), "어제 마감 채용 : " + myRecruit.getTitle());
        });
    }

    @Scheduled(cron = "0 40 0 * * *")
    public void sendSelectionAlarm() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = today.plusDays(1);
        List<Selection> selections = selectionRepository.findAllByProgressDateBetweenAndIsDeleteFalse(today, tomorrow);
        selections.forEach(selection -> {
            User user = userRepository.findById(selection.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .selection(selection)
                    .build();
            alarmRepository.save(alarm);
            producerService.upcomingSelectionAlarm(user.getId(), "오늘 마감 전형 : " + selection.getTitle());
        });
    }

    @Scheduled(cron = "0 40 1 * * *")
    public void sendSelectionReviewAlarm() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        List<Selection> selections = selectionRepository.findAllByProgressDateBetweenAndIsDeleteFalse(yesterday, today);
        selections.forEach(selection -> {
            User user = userRepository.findById(selection.getUser().getId()).orElseThrow(() -> { return new IllegalArgumentException("해당 사용자를 찾을 수 없습니다"); });
            Alarm alarm = Alarm.builder()
                    .user(user)
                    .selection(selection)
                    .build();
            alarmRepository.save(alarm);
            producerService.upcomingSelectionAlarm(user.getId(), "어제 마감 전형 : " + selection.getTitle());
        });
    }

}
