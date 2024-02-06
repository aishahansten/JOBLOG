package com.ssafy.joblog.domain.alarm.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private int userRecruitId;
    private String userRecruitTitle;
    private String CompanyName;
    private int scheduleId;
    private String scheduleTitle;
    private int chatRoomId;
    private int chatteeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    private int applyStatusId;

}
