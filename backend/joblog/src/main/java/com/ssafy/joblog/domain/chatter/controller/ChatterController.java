package com.ssafy.joblog.domain.chatter.controller;

import com.ssafy.joblog.domain.chatter.dto.request.ChatterCreateRequestDto;
import com.ssafy.joblog.domain.chatter.dto.response.ChatterResponseDto;
import com.ssafy.joblog.domain.chatter.service.ChatterService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatterController {

    private final ChatterService chatterService;
//    private final ChatterResponseDto chatterResponseDto;

    // 1. 커피 채터 프로필 등록하기
    @PostMapping("/profile/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createChatter(@RequestBody ChatterCreateRequestDto chatterCreateRequestDto) {
        chatterService.createChatter(chatterCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 커피 채터 조회하기
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getChatters() {
        return new ResponseEntity<>(chatterService.getChatters(), HttpStatus.OK);
    }


    // 3. 커피 채터 상세 조회하기
    @GetMapping("/profile/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getChatter(@PathVariable Integer userId) {
        return new ResponseEntity<>(chatterService.getChatter(userId), HttpStatus.OK);
    }


    // 4. 커피 채터 프로필 수정하기

    // 5. 커피 채터 비활성화하기



}
