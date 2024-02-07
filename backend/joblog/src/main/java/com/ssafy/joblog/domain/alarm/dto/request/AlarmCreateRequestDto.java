//package com.ssafy.joblog.domain.alarm.dto.request;
//
//import com.ssafy.joblog.domain.alarm.entity.Alarm;
//import com.ssafy.joblog.domain.user.entity.User;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AlarmCreateRequestDto {
//
//    private int userId; //알림을 받는 유저
//    private int chatRoomId;
//
//    public Alarm createAlarm(User user) {
//        return Alarm.builder()
//                .user(user)
//                .build();
//    }
//
//}
