package com.ssafy.joblog.JsonDataReadWrite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class JobCategory {

    @Id
    @Column(name = "job_code")
    private int jobCode;

    private String jobName;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private JobCategory parent;

    @Setter
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<JobCategory> child = new ArrayList<>();

}