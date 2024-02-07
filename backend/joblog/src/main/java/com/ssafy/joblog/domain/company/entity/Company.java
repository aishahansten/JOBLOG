package com.ssafy.joblog.domain.company.entity;

import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {
    @Id
    @Column(name = "company_code")
    private Long companyCode;

    private String companyName;


    @Builder
    public Company(Long companyCode, String companyName) {
        this.companyCode = companyCode;
        this.companyName = companyName;
    }
}
