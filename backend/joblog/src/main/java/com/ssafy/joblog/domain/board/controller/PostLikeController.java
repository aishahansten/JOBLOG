package com.ssafy.joblog.domain.board.controller;

import com.ssafy.joblog.domain.board.dto.request.PostLikeRequestDto;
import com.ssafy.joblog.domain.board.service.PostLikeService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/like")
@RequiredArgsConstructor
@RestController
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto insertLike(@RequestBody @Valid PostLikeRequestDto postLikeRequestDto) throws Exception {
        postLikeService.likePost(postLikeRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}
