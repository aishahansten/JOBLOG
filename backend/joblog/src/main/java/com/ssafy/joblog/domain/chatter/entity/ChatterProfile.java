package com.ssafy.joblog.domain.chatter.entity;


import com.ssafy.joblog.domain.chatter.dto.request.ChatterUpdateRequestDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ChatterProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatter_profile_id")
    private int id;

    // 연관관계 주인 ( 외래키 가지고 있음)
    @OneToOne(fetch = LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String job;
    private String career;
    @Column(length = 1000)
    private String description;

    // 생성자
    @Builder
    public ChatterProfile(int id, User user, String job, String career, String description) {
        this.id=id;
        this.user=user;
        this.job=job;
        this.career=career;
        this.description=description;
    }

    // update 더티체킹
    public void updateChatter(ChatterUpdateRequestDto chatterUpdateRequestDto) {
        this.job = chatterUpdateRequestDto.getJob();
        this.career = chatterUpdateRequestDto.getCareer();
        this.description = chatterUpdateRequestDto.getDescription();
    }



}
