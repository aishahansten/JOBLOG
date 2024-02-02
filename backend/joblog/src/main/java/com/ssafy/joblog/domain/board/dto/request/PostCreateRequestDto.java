package com.ssafy.joblog.domain.board.dto.request;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 1. 게시글 작성
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateRequestDto {
    private int userId;
    private String title;
    private PostCategory category;
    private String content;

    // DTO to Entity
    public Post createPost(User user){
        return Post.builder()
                .title(this.title)
                .category(this.category)
                .content(this.content)
                .user(user)
                .build();
    }
}