package com.ssafy.joblog.domain.myRecruit.dto.response;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectionListResponseDto {
    private int id;
    private int myRecruitId;
    private long companyCode;
    private LocalDateTime progressDate;
    private String title;
    private int step;
    private String status;
    private Boolean reviewOrNot;
    public static SelectionListResponseDto fromEntity(Selection selection) {
        return SelectionListResponseDto.builder()
                .id(selection.getId())
                .myRecruitId(selection.getMyRecruit().getId())
                .companyCode(selection.getCompanyCode())
                .progressDate(selection.getProgressDate())
                .title(selection.getTitle())
                .step(selection.getStep())
                .status(selection.getStatus())
                .reviewOrNot(selection.getReviewOrNot())
                .build();
    }
}
