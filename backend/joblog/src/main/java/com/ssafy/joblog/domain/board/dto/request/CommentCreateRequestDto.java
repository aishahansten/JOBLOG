package com.ssafy.joblog.domain.board.dto.request;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostComment;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {
    private int postId;
    private int userId;
    private String content;

    // DTO -> Entity
    public PostComment createComment(User user, Post post) {
        return PostComment.builder()
                .post(post)
                .content(this.content)
                .user(user)
                .build();
    }
}
