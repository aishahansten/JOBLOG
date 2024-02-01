package com.ssafy.joblog.domain.board.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeRequestDto {

    private Integer postId;
    private Integer userId;

//    public PostLikeRequestDto(Integer userId, Integer postId) {
//        this.userId = userId;
//        this.postId = postId;
//    }
}
