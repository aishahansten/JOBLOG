package com.ssafy.joblog.domain.alarm.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.alarm.entity.Alarm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmResponseDto {

    private int alarmId;
    private int userId;
    private int myRecruitId;
    private String myRecruitTitle;
    private String companyName;
    private int scheduleId;
    private String scheduleTitle;
    private int chatRoomId;
    private int chatteeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    private int selectionId;

    public static AlarmResponseDto fromEntity(Alarm alarm) {
        return AlarmResponseDto.builder()
                .alarmId(alarm.getId())
                .userId(alarm.getUser().getId())
                .myRecruitId(alarm.getMyRecruit().getId())
                .myRecruitTitle(alarm.getMyRecruit().getTitle())
                .companyName(alarm.getMyRecruit().getCompanyName())
                .scheduleId(alarm.getSchedule().getId())
                .scheduleTitle(alarm.getSchedule().getTitle())
                .chatRoomId(alarm.getCoffeeChatRoom().getId())
                .chatteeId(alarm.getCoffeeChatRoom().getChattee().getId())
                .startDate(alarm.getCoffeeChatRoom().getStartDate())
                .selectionId(alarm.getSelection().getId())
                .build();
    }

}
