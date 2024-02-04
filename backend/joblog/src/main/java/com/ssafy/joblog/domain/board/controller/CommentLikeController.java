package com.ssafy.joblog.domain.board.controller;

import com.ssafy.joblog.domain.board.dto.request.CommentLikeRequestDto;
import com.ssafy.joblog.domain.board.service.CommentLikeService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/commentLike")
@RequiredArgsConstructor
@RestController
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto insertLikeComment(@RequestBody @Valid CommentLikeRequestDto commentLikeRequestDto) throws Exception{
        commentLikeService.likeComment(commentLikeRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

}
