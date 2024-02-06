package com.ssafy.joblog.domain.board.repository;

import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.board.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<PostComment, Integer> {

    // is_delete=0인 댓글만 표시
    public List<PostComment> findByPostAndIsDeleteIsFalseOrderByCreatedDateDesc(Post post);

    @Modifying
    @Query("UPDATE PostComment postComment SET postComment.isDelete = true WHERE postComment.id = :id")
    void markDeletedComment(@Param("id") int id);

    // is_delete=0인 댓글 좋아요 개수
    @Query("SELECT COUNT(commentlike) FROM PostCommentLike commentlike WHERE commentlike.postComment.id = :commentId AND commentlike.isDelete = false")
    int countActiveCommentLikes(@Param("commentId") int commentId);


}
