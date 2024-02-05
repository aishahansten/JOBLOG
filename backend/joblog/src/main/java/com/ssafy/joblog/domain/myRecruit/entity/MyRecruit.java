package com.ssafy.joblog.domain.myRecruit.entity;

import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyRecruit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    private boolean scrapOrNot;
    private String title;
    private String companyName;
    private Long companyCode;
    private String job;
    private LocalDateTime openingDate;
    private LocalDateTime expirationDate;
    private String description;

    @OneToMany(mappedBy = "myRecruit", cascade = CascadeType.ALL)
    private List<Selection> selections = new ArrayList<>();

    @Builder
    public MyRecruit(int id, User user, Recruit recruit, boolean scrapOrNot, String title, String companyName, Long companyCode, String job, LocalDateTime openingDate, LocalDateTime expirationDate, String description, List<Selection> selections) {
        this.id = id;
        this.user = user;
        this.recruit = recruit;
        this.scrapOrNot = scrapOrNot;
        this.title = title;
        this.companyName = companyName;
        this.companyCode = companyCode;
        this.job = job;
        this.openingDate = openingDate;
        this.expirationDate = expirationDate;
        this.description = description;
        this.selections = selections;
    }

    public void updateScrap() {
        this.isDelete = false;
    }

    public void deleteScrap() {
        this.isDelete = true;
    }

    public void updateMyRecruit(MyRecruitRequestDto myRecruitRequestDto) {
        this.title = myRecruitRequestDto.getTitle();
        this.companyName = myRecruitRequestDto.getCompanyName();
        this.job = myRecruitRequestDto.getJob();
        this.openingDate = myRecruitRequestDto.getOpeningDate();
        this.expirationDate = myRecruitRequestDto.getExpirationDate();
        this.description = myRecruitRequestDto.getDescription();
    }

    public void deleteMyRecruit() {
        this.isDelete = true;
    }
}
