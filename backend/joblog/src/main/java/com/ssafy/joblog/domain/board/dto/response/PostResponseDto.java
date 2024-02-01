package com.ssafy.joblog.domain.board.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {
    private int postId;
    private int userId;
    private PostCategory category;
    private String title;
    private String content;
    private int hit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime modifiedDate;
    private int totalLike;
}