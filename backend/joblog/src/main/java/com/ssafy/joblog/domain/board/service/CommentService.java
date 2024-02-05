package com.ssafy.joblog.domain.board.service;

import com.ssafy.joblog.domain.board.dto.request.CommentCreateRequestDto;
import com.ssafy.joblog.domain.board.dto.request.CommentUpdateRequestDto;
import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostComment;
import com.ssafy.joblog.domain.board.repository.CommentRepository;
import com.ssafy.joblog.domain.board.repository.PostRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 1. 댓글 등록하기
    @Transactional
    public void createComment(CommentCreateRequestDto commentCreateRequestDto){
        User user = userRepository.findById(commentCreateRequestDto.getUserId()).orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Post post = postRepository.findById(commentCreateRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        PostComment comment = commentCreateRequestDto.createComment(user, post);
        commentRepository.save(comment);
    }

    // 2. 댓글 수정하기
    @Transactional
    public void updateComment(CommentUpdateRequestDto commentUpdateRequestDto) {
        PostComment comment = commentRepository.findById(commentUpdateRequestDto.getCommentId())
                .orElseThrow(()-> new IllegalArgumentException("해당 댓글이 존재하지 않습니다"));

        comment.updateComment(commentUpdateRequestDto);
    }

    // 3. 댓글 삭제하기
    @Transactional
    public void markDeletedComment(int comment_id) {
        PostComment postComment = commentRepository.findById(comment_id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다"));
        commentRepository.markDeletedComment(comment_id);

    }



}
