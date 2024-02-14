package com.ssafy.joblog.domain.myRecruit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.joblog.domain.myRecruit.dto.request.ReviewRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.SelectionRequestDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Selection extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selection_id")
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "myrecruit_id")
    private MyRecruit myRecruit;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private long companyCode;
    private LocalDateTime progressDate;
    private String title;
    private int step;
    private String status;
    private Boolean reviewOrNot;
    private String type;
    private Integer totalQuestion;
    private Integer timeRequired;
    private String applyJob;
    private int difficulty;
    private int atmosphere;

    @Column(length = 2000)
    private String comment;

    @Column(length = 5000)
    private String review; //private

    @Builder
    public Selection(int id, MyRecruit myRecruit, User user, LocalDateTime progressDate, long companyCode, String title, int step, String status, Boolean reviewOrNot, String type, Integer totalQuestion, Integer timeRequired, String applyJob, int difficulty, int atmosphere, String comment, String review) {
        this.id = id;
        this.myRecruit = myRecruit;
        this.user = user;
        this.progressDate = progressDate;
        this.companyCode = companyCode;
        this.title = title;
        this.step = step;
        this.status = status;
        this.reviewOrNot = reviewOrNot;
        this.type = type;
        this.totalQuestion = totalQuestion;
        this.timeRequired = timeRequired;
        this.applyJob = applyJob;
        this.difficulty = difficulty;
        this.atmosphere = atmosphere;
        this.comment = comment;
        this.review = review;
    }

    public void updateSelection(SelectionRequestDto selectionRequestDto) {
        this.progressDate = selectionRequestDto.getProgressDate();
        this.title = selectionRequestDto.getTitle();
        this.step = selectionRequestDto.getStep();
        this.status = selectionRequestDto.getStatus();
    }

    public void deleteSelection() {
        this.isDelete = true;
    }

    public void createReview(ReviewRequestDto reviewRequestDto) {
        this.reviewOrNot = true;
        this.type = reviewRequestDto.getType();
        this.totalQuestion = reviewRequestDto.getTotalQuestion();
        this.timeRequired = reviewRequestDto.getTimeRequired();
        this.applyJob = reviewRequestDto.getApplyJob();
        this.difficulty = reviewRequestDto.getDifficulty();
        this.atmosphere = reviewRequestDto.getAtmosphere();
        this.comment = reviewRequestDto.getComment();
        this.review = reviewRequestDto.getReview();
    }

    public void updateReview(ReviewRequestDto reviewRequestDto) {
        this.type = reviewRequestDto.getType();
        this.totalQuestion = reviewRequestDto.getTotalQuestion();
        this.timeRequired = reviewRequestDto.getTimeRequired();
        this.applyJob = reviewRequestDto.getApplyJob();
        this.difficulty = reviewRequestDto.getDifficulty();
        this.atmosphere = reviewRequestDto.getAtmosphere();
        this.comment = reviewRequestDto.getComment();
        this.review = reviewRequestDto.getReview();
    }

    public void deleteReview() {
        this.reviewOrNot = false;
        this.type = null;
        this.totalQuestion = null;
        this.timeRequired = null;
        this.applyJob = null;
        this.difficulty = 0;
        this.atmosphere = 0;
        this.comment = null;
        this.review = null;
    }


}
