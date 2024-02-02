package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findByCategory(PostCategory category);

    @Modifying
    @Query("UPDATE Post post SET post.isDelete = true WHERE post.id = :id")
    void markDeletedPost(@Param("id") int id);
}