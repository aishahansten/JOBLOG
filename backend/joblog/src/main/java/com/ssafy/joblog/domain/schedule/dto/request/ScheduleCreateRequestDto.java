package com.ssafy.joblog.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleCreateRequestDto {
    private int userId;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Schedule createSchedule(User user){
        return Schedule.builder()
                .title(this.title)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .user(user)
                .build();
    }
}