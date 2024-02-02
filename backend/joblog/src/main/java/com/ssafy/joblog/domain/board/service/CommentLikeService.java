package com.ssafy.joblog.domain.board.service;

import com.ssafy.joblog.domain.board.dto.request.CommentLikeRequestDto;
import com.ssafy.joblog.domain.board.entity.PostComment;
import com.ssafy.joblog.domain.board.entity.PostCommentLike;
import com.ssafy.joblog.domain.board.repository.CommentLikeRepository;
import com.ssafy.joblog.domain.board.repository.CommentRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional
    public void likeComment(CommentLikeRequestDto commentLikeRequestDto) {
        PostComment postComment = commentRepository.findById(commentLikeRequestDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다"));
        User user = userRepository.findById(commentLikeRequestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));

        PostCommentLike postCommentLike = PostCommentLike.builder()
                .postComment(postComment)
                .user(user)
                .build();
        // 이미 좋아요를 누른 경우(post_like db에 저장되어있는 경우), 좋아요 다시 누르기
        if (commentLikeRepository.existsByUserAndCommentAndIsDelete(postComment.getId(), user.getId(), postComment.isDelete())) {
            commentLikeRepository.markLikeComment(postComment.getId(), user.getId());
            // 이미 좋아요를 누른 경우(post_like db에 저장되어있는 경우), 좋아요 취소
        } else if (commentLikeRepository.existsByUserAndPostComment(user, postComment)){
            commentLikeRepository.markDeleteLikeComment(postComment.getId(), user.getId());
            // post_like db에 저장되어있지 않은 경우
        } else {
            commentLikeRepository.save(postCommentLike);
        }
    }

}
