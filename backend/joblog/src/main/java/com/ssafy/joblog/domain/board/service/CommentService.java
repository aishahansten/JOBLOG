package com.ssafy.joblog.domain.board.service;

import com.ssafy.joblog.domain.board.dto.request.CommentCreateRequestDto;
import com.ssafy.joblog.domain.board.entity.Post;
import com.ssafy.joblog.domain.board.entity.PostComment;
import com.ssafy.joblog.domain.board.repository.CommentRepository;
import com.ssafy.joblog.domain.board.repository.PostRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
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
        User user = userRepository.findById(commentCreateRequestDto.getUserId());
        Post post = postRepository.findById(commentCreateRequestDto.getPostId()).orElseThrow(() -> new IllegalArgumentException("해당하는 게시글이 없습니다."));
        PostComment comment = commentCreateRequestDto.createComment(user, post);
        commentRepository.save(comment);
    }




}
