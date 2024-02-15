package com.ssafy.joblog.domain.spellCheck.dto;

import com.ssafy.joblog.domain.schedule.entity.Schedule;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpellCheckRequestDto {
    private String text;
}