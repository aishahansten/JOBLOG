package com.ssafy.joblog.domain.company.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyReviewUpdateRequestDto {
    private Long id;
    private Long companyCode;
    private int userId;
    private int benefitsAndSalary;
    private int workLifeBalance;
    private int culture;
    private int promotionOpportunity;
    private int management;
    private String content;

}
