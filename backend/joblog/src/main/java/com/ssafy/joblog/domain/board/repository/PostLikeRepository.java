package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostLike;
import com.ssafy.joblog.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    boolean existsByUserAndPost(User user, Post post);

    // 좋아요 취소
    @Modifying
    @Query("UPDATE PostLike postLike SET postLike.isDelete = true WHERE postLike.post.id = :postId AND postLike.user.id = :userId")
    void markDeleteLikePost(@Param("postId") int postId, @Param("userId") int userId);

    // 다시 좋아요
    @Modifying
    @Query("UPDATE PostLike postLike SET postLike.isDelete = false WHERE postLike.post.id = :postId AND postLike.user.id = :userId")
    void markLikePost(@Param("postId") int postId, @Param("userId") int userId);

    @Query("SELECT CASE WHEN COUNT(postLike) > 0 THEN true ELSE false END FROM PostLike postLike WHERE postLike.user.id =:userId AND postLike.post.id =:postId AND postLike.isDelete =:isDelete AND postLike.isDelete = true")
    boolean existsByUserAndPostAndIsDelete(@Param("postId") int postId, @Param("userId") int userId, @Param("isDelete") boolean isDelete);
}
