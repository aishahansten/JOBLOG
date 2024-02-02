package com.ssafy.joblog.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class PostWithCommentsResponseDto {
    private PostResponseDto postResponseDto;
    private List<CommentResponseDto> comments;

    public PostWithCommentsResponseDto(PostResponseDto postResponseDto, List<CommentResponseDto> comments) {
        this.postResponseDto = postResponseDto;
        this.comments = comments;
    }
}
