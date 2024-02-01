package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<PostComment, Integer> {
}
