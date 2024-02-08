package com.ssafy.joblog.domain.company.dto.responseDto;

import com.ssafy.joblog.domain.company.entity.Company;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageCompanyResponseDto {
    private int totalPages;
    private Long totalElements;
    private List<CompanyResponseDto> companyResponseDtos;

    public static PageCompanyResponseDto fromEntity(int totalPages, Long totalElements, List<CompanyResponseDto> companyResponseDtos) {
        return PageCompanyResponseDto.builder()
                .totalPages(totalPages)
                .totalElements(totalElements)
                .companyResponseDtos(companyResponseDtos)
                .build();
    }
}
