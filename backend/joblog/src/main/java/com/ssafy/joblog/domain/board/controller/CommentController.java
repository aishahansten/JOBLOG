package com.ssafy.joblog.domain.board.controller;

import com.ssafy.joblog.domain.board.dto.request.CommentCreateRequestDto;
import com.ssafy.joblog.domain.board.service.CommentService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/comment")
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


}
