package com.ssafy.joblog.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentUpdateRequestDto {
    private int commentId;
    private String content;
}
