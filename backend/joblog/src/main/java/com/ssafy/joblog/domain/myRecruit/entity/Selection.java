package com.ssafy.joblog.domain.myRecruit.entity;

import com.ssafy.joblog.domain.recruit.entity.Recruit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Selection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "myrecruit_id")
    private MyRecruit myRecruit;
}
