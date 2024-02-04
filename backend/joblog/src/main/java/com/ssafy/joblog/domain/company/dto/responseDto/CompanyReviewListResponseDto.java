package com.ssafy.joblog.domain.company.dto.responseDto;

import com.ssafy.joblog.domain.company.entity.CompanyReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyReviewListResponseDto {
    private Long id;
    private String nickname;
    private int benefitsAndSalary;
    private int workLifeBalance;
    private int culture;
    private int promotionOpportunity;
    private int management;
    private String content;

    public static CompanyReviewListResponseDto fromEntity(CompanyReview companyReview) {
        return CompanyReviewListResponseDto.builder()
                .id(companyReview.getId())
                .nickname(companyReview.getUser().getNickname())
                .benefitsAndSalary(companyReview.getBenefitsAndSalary())
                .workLifeBalance(companyReview.getWorkLifeBalance())
                .culture(companyReview.getCulture())
                .promotionOpportunity(companyReview.getPromotionOpportunity())
                .management(companyReview.getManagement())
                .content(companyReview.getContent())
                .build();
    }
}
