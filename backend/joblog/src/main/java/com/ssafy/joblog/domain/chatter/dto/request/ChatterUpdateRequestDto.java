package com.ssafy.joblog.domain.chatter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ChatterUpdateRequestDto {
    private int userId;
    private String job;
    private String career;
    private String description;
}
