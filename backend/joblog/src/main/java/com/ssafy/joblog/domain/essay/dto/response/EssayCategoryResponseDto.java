package com.ssafy.joblog.domain.essay.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EssayCategoryResponseDto {
    private Integer categoryId;
    private String categoryName;
}
