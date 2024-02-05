package com.ssafy.joblog.JsonDataReadWrite.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JobType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_type_id")
    private Long id;

    @Setter
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    private String jobType;
    private int jobTypeCode;

    @Builder
    public JobType(Recruit recruit, String jobType, int jobTypeCode) {
        this.recruit = recruit;
        this.jobType = jobType;
        this.jobTypeCode = jobTypeCode;
    }
}
