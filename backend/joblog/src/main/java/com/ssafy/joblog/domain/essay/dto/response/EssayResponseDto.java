package com.ssafy.joblog.domain.essay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EssayResponseDto {
    private int essayId;
    private long recruitId;
    private int categoryId;
    private String categoryName;/////
    private int userId;
    private String question;
    private String answer;


}
