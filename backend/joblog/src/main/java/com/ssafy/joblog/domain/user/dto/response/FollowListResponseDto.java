package com.ssafy.joblog.domain.user.dto.response;

import com.ssafy.joblog.domain.user.entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FollowListResponseDto {
    private int id;
    private int followingId;
    private String followingNickname;

    public static FollowListResponseDto fromEntity(Follow following) {
        return FollowListResponseDto.builder()
                .id(following.getId())
                .followingId(following.getFollowing().getId())
                .followingNickname(following.getFollowing().getNickname())
                .build();
    }
}
