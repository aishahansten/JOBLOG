package com.ssafy.joblog.JsonDataReadWrite.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobCategoryRecruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_recruit_id")
    private Long id;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL) //단방향
    @JoinColumn(name = "job_code")
    private JobCategory jobCategory;

    @Setter
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @Builder
    public JobCategoryRecruit(JobCategory jobCategory, Recruit recruit) {
        this.jobCategory = jobCategory;
        this.recruit = recruit;
    }

}
