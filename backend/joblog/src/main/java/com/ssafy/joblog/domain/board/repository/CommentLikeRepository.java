package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostComment;
import com.ssafy.joblog.domain.board.entity.PostCommentLike;
import com.ssafy.joblog.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentLikeRepository extends JpaRepository<PostCommentLike, Integer> {
    boolean existsByUserAndPostComment(User user, PostComment postComment);

    //좋아요 취소
    @Modifying
    @Query("UPDATE PostCommentLike postCommentLike SET postCommentLike.isDelete = true WHERE postCommentLike.postComment.id = :postCommentId AND postCommentLike.user.id = :userId")
    void markDeleteLikeComment(@Param("postCommentId") int postCommentId, @Param("userId") int userId);

    // 다시 좋아요
    @Modifying
    @Query("UPDATE PostCommentLike postCommentLike SET postCommentLike.isDelete = false WHERE postCommentLike.postComment.id = :postCommentId AND postCommentLike.user.id = :userId")
    void markLikeComment(@Param("postCommentId") int postCommentId, @Param("userId") int userId);

    // db에 존재하는데 is_delete = true인 경우
    @Query("SELECT CASE WHEN COUNT(postCommentLike) > 0 THEN true ELSE false END FROM PostCommentLike postCommentLike WHERE postCommentLike.user.id =:userId AND postCommentLike.postComment.id =:postCommentId AND postCommentLike.isDelete =:isDelete AND postCommentLike.isDelete = true")
    boolean existsByUserAndCommentAndIsDelete(@Param("postCommentId") int postCommentId, @Param("userId") int userId, @Param("isDelete") boolean isDelete);

}
