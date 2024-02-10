package com.ssafy.joblog.domain.essay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EssayUpdateRequestDto {

    private int essayId;
    private int userId;
    private Long recruitId;
    private Integer categoryId;
    private String question;
    private String answer;
}
