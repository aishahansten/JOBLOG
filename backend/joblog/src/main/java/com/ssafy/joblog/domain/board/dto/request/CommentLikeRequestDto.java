package com.ssafy.joblog.domain.board.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLikeRequestDto {

    private Integer commentId;
    private Integer userId;
}
