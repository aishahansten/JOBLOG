package com.ssafy.joblog.domain.chatter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChatterResponseDto {
    private int id;
    private int userId;
    private String job;
    private String career;
    private String description;
    protected boolean isDelete;
}
