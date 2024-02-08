package com.ssafy.joblog.domain.essay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EssayCategoryUpdateRequestDto {
    private int categoryId;
    private int userId;
    private String categoryName;
}
