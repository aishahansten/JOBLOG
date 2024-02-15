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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private boolean isChecked;
    private int alarmType;
    private String code;
    private LocalDateTime alarmDate;
    private int eventId;
    private String title;
    private String companyName;

    public static AlarmResponseDto fromEntity(Alarm alarm) {
        if (alarm.getMyRecruit() != null) {
            return AlarmResponseDto.builder()
                    .alarmId(alarm.getId())
                    .userId(alarm.getUser().getId())
                    .isChecked(alarm.isChecked())
                    .alarmType(alarm.getType())
                    .alarmDate(alarm.getMyRecruit().getExpirationDate())
                    .eventId(alarm.getMyRecruit().getId())
                    .title(alarm.getMyRecruit().getTitle())
                    .companyName(alarm.getMyRecruit().getCompanyName())
                    .build();
        } else if (alarm.getSchedule() != null) {
            return AlarmResponseDto.builder()
                    .alarmId(alarm.getId())
                    .userId(alarm.getUser().getId())
                    .isChecked(alarm.isChecked())
                    .alarmType(alarm.getType())
                    .alarmDate(alarm.getSchedule().getEndDate())
                    .eventId(alarm.getSchedule().getId())
                    .title(alarm.getSchedule().getTitle())
                    .build();
        } else if (alarm.getCoffeeChatRoom() != null) {
            return AlarmResponseDto.builder()
                    .alarmId(alarm.getId())
                    .userId(alarm.getUser().getId())
                    .isChecked(alarm.isChecked())
                    .alarmType(alarm.getType())
                    .code(alarm.getCode())
                    .alarmDate(alarm.getCoffeeChatRoom().getStartDate())
                    .eventId(alarm.getCoffeeChatRoom().getId())
                    .title(alarm.getCoffeeChatRoom().getTitle())
                    .build();
        } else if (alarm.getSelection() != null){
            return AlarmResponseDto.builder()
                    .alarmId(alarm.getId())
                    .userId(alarm.getUser().getId())
                    .isChecked(alarm.isChecked())
                    .alarmType(alarm.getType())
                    .alarmDate(alarm.getSelection().getProgressDate())
                    .eventId(alarm.getSelection().getId())
                    .title(alarm.getSelection().getTitle())
                    .companyName(alarm.getSelection().getMyRecruit().getCompanyName())
                    .build();
        }
        return null;
    }

}
