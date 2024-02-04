package com.ssafy.joblog.domain.company.dto.requestDto;

import com.ssafy.joblog.domain.company.entity.Company;
import com.ssafy.joblog.domain.company.entity.CompanyReview;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyReviewCreateRequestDto {
    private Long companyCode;
    private int userId;
    private int benefitsAndSalary;
    private int workLifeBalance;
    private int culture;
    private int promotionOpportunity;
    private int management;
    private String content;

    public CompanyReview createReview(User user, Company company) {
        return CompanyReview.builder()
                .company(company)
                .user(user)
                .benefitsAndSalary(this.benefitsAndSalary)
                .workLifeBalance(this.workLifeBalance)
                .culture(this.culture)
                .promotionOpportunity(this.promotionOpportunity)
                .management(this.management)
                .content(this.content)
                .build();
    }
}
