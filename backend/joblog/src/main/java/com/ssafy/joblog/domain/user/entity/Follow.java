package com.ssafy.joblog.domain.user.entity;

import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "follower_id")
    private User follower; //팔로우 신청한 채티

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "following_id")
    private User following;

    @Builder
    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    public void updateFollow(){
        this.isDelete = false;
    }

    public void deleteFollow() {
        this.isDelete = true;
    }
}
