package com.ssafy.joblog.domain.diary.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryResponseDto {
    private int diaryId;
    private int userId;
    private String content;
}