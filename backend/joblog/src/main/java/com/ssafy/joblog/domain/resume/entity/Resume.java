package com.ssafy.joblog.domain.resume.entity;

import com.ssafy.joblog.domain.resume.dto.request.ResumeUpdateRequestDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String job;

    @Builder
    public Resume(int id, User user, String title, String job) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.job = job;
    }

    public void updateResume(ResumeUpdateRequestDto resumeUpdateRequestDto) {
        this.title = resumeUpdateRequestDto.getTitle();
        this.job = resumeUpdateRequestDto.getJob();
    }

    public void deleteResume() {
        this.isDelete = true;
    }
}
