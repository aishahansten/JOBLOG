package com.ssafy.joblog.domain.company.entity;

import com.example.demo.domain.company.dto.requestDto.CompanyReviewUpdateRequestDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "company_code")
    private Company company;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    private int benefitsAndSalary;
    private int workLifeBalance;
    private int culture;
    private int promotionOpportunity;
    private int management;
    @Column(length=1000)
    private String content;

    @Builder
    public CompanyReview(Long id, Company company, User user, int benefitsAndSalary, int workLifeBalance, int culture, int promotionOpportunity, int management, String content) {
        this.id = id;
        this.company = company;
        this.user = user;
        this.benefitsAndSalary = benefitsAndSalary;
        this.workLifeBalance = workLifeBalance;
        this.culture = culture;
        this.promotionOpportunity = promotionOpportunity;
        this.management = management;
        this.content = content;
    }


    public void updateReview(CompanyReviewUpdateRequestDto companyReviewUpdateRequestDto) {
        this.benefitsAndSalary = companyReviewUpdateRequestDto.getBenefitsAndSalary();
        this.workLifeBalance = companyReviewUpdateRequestDto.getWorkLifeBalance();
        this.culture = companyReviewUpdateRequestDto.getCulture();
        this.promotionOpportunity = companyReviewUpdateRequestDto.getPromotionOpportunity();
        this.management = companyReviewUpdateRequestDto.getManagement();
        this.content = companyReviewUpdateRequestDto.getContent();
    }

    public void deleteReview() {
        this.isDelete = true;
    }
}
