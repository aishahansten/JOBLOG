package com.ssafy.joblog.domain.board.controller;

import com.ssafy.joblog.domain.board.dto.request.PostCreateRequestDto;
import com.ssafy.joblog.domain.board.dto.request.PostUpdateRequestDto;
import com.ssafy.joblog.domain.board.entity.PostCategory;
import com.ssafy.joblog.domain.board.service.PostService;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/community")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    // 1. 게시글 작성
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createPost(@RequestBody PostCreateRequestDto postCreateRequestDto) {
        postService.createPost(postCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 카테고리별 게시글 목록 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    // 3. 상세 게시글 조회
    @GetMapping("/detail/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPost(@PathVariable Integer postId){
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    // 4. 게시글 수정
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updatePost(@RequestBody PostUpdateRequestDto postUpdateRequestDto){
        postService.updatePost(postUpdateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 5. 게시글 삭제
    @DeleteMapping("/delete/{postId}")
    public StatusResponseDto markDeletedPost(@PathVariable Integer postId) {
        postService.markDeletedPost(postId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}