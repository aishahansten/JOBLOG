package com.ssafy.joblog.domain.myRecruit.dto.response;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPrivateResponseDto {
    private String myRecruitTitle;
    private LocalDateTime progressDate;
    private String title;
    private int step;
    private String status;
    private String type;
    private Integer totalQuestion;
    private Integer timeRequired;
    private String applyJob;
    private int difficulty;
    private int atmosphere;
    private String comment;
    private String review;

    public static ReviewPrivateResponseDto fromEntity(Selection selection) {
        return ReviewPrivateResponseDto.builder()
                .myRecruitTitle(selection.getMyRecruit().getTitle())
                .progressDate(selection.getProgressDate())
                .title(selection.getTitle())
                .step(selection.getStep())
                .type(selection.getType())
                .totalQuestion(selection.getTotalQuestion())
                .timeRequired(selection.getTimeRequired())
                .applyJob(selection.getApplyJob())
                .difficulty(selection.getDifficulty())
                .atmosphere(selection.getAtmosphere())
                .comment(selection.getComment())
                .review(selection.getReview())
                .build();

    }
}
