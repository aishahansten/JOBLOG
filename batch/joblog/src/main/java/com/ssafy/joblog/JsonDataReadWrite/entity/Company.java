package com.ssafy.joblog.JsonDataReadWrite.entity;

import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
