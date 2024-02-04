package com.ssafy.joblog.domain.company.dto.responseDto;

import com.ssafy.joblog.domain.company.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponseDto {
    private Long companyCode;
    private String companyName;

    public static CompanyResponseDto fromEntity(Company company){
        return CompanyResponseDto.builder()
                .companyCode(company.getCompanyCode())
                .companyName(company.getCompanyName())
                .build();
    }
}
