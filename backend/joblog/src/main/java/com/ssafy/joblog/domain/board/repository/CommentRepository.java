package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.board.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<PostComment, Integer> {
    public List<PostComment> findByPost(Post post);
}
