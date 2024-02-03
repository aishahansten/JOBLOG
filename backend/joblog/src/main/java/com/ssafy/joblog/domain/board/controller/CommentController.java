package com.ssafy.joblog.domain.board.controller;

import com.ssafy.joblog.domain.board.dto.request.CommentCreateRequestDto;
import com.ssafy.joblog.domain.board.dto.request.CommentUpdateRequestDto;
import com.ssafy.joblog.domain.board.service.CommentService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/comment")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 1. 댓글 등록하기
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createComment(@RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        commentService.createComment(commentCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 댓글 수정하기
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateComment(@RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        commentService.updateComment(commentUpdateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 3. 댓글 삭제하기
    @DeleteMapping("/delete/{commentId}")
    public StatusResponseDto deleteComment(@PathVariable Integer commentId) {
        commentService.markDeletedComment(commentId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}
