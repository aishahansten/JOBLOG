package com.ssafy.joblog.domain.diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiaryUpdateRequestDto {
    private int diaryId;
    private int userId;
    private String content;
}