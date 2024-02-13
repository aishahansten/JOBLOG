package com.ssafy.joblog.domain.recruit.dto.responseDto;

import com.ssafy.joblog.domain.recruit.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.query.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRecruitListResponseDto {
    private int totalPages;
    private Long totalElements;
    private List<RecruitListResponseDto> recruitListResponseDtos;

    public static PageRecruitListResponseDto fromEntity(int totalPages, Long totalElements, List<RecruitListResponseDto> recruitListResponseDtos) {
        return PageRecruitListResponseDto.builder()
                .totalPages(totalPages)
                .totalElements(totalElements)
                .recruitListResponseDtos(recruitListResponseDtos)
                .build();
    }
}
