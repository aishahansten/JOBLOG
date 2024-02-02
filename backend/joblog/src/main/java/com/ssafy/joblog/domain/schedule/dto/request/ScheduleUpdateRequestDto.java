package com.ssafy.joblog.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleUpdateRequestDto {
    private int scheduleId;
    private int userId;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}

// Update 하면 postman에서 startDate, endDate를 key값으로 넣어야되는데, start_date, end_date
//로 입력해서 null로 update 되는 문제 발생함.