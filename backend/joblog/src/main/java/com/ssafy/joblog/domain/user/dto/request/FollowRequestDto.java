package com.ssafy.joblog.domain.user.dto.request;

import com.ssafy.joblog.domain.user.entity.Follow;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowRequestDto {
    private int followerId;
    private int followingId;

    public Follow createFollow(User follower, User following) {
        return Follow.builder()
                .follower(follower)
                .following(following)
                .build();
    }
}
