package com.ssafy.joblog.domain.board.dto.request;

import com.ssafy.joblog.domain.board.entity.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostUpdateRequestDto {
    private int postId;
    private int userId;
    private String title;
    private PostCategory category;
    private String content;
}