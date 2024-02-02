package com.ssafy.joblog.domain.resume.entity;

import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import static jakarta.persistence.FetchType.LAZY;


@NoArgsConstructor
@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE resume set is_delete = true WHERE resume_id = ?")
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private int id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}