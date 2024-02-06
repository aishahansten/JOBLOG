package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Modifying
    @Query("UPDATE Post post SET post.isDelete = true WHERE post.id = :id")
    void markDeletedPost(@Param("id") int id);

    // is_delete=0인 댓글 개수
    @Query("SELECT COUNT(comment) FROM PostComment comment WHERE comment.post.id = :postId AND comment.isDelete = false")
    int countActiveComments(@Param("postId") int postId);

    // is_delete=0인 좋아요 개수
    @Query("SELECT COUNT(like) FROM PostLike like WHERE like.post.id = :postId AND like.isDelete = false")
    int countActiveLikes(@Param("postId") int postId);

    // is_delete인 게시글만 표시

    List<Post> findAllByIsDeleteIsFalseOrderByCreatedDateDesc();
    Optional<Post> findByIdAndIsDeleteIsFalse(Integer id);
}