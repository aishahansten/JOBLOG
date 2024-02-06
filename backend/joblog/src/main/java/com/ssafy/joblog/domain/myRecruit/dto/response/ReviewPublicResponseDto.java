package com.ssafy.joblog.domain.myRecruit.dto.response;

import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewPublicResponseDto {
    private String nickname;
    private boolean userIsDelete;
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
    public static ReviewPublicResponseDto fromEntity(User user, Selection selection) {
        return ReviewPublicResponseDto.builder()
                .nickname(user.getNickname())
                .userIsDelete(user.isDelete())
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
                .build();
    }
}
