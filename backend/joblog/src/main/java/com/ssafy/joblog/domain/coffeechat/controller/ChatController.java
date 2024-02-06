package com.ssafy.joblog.domain.coffeechat.controller;

import com.ssafy.joblog.domain.coffeechat.dto.request.ChatCreateRequestDto;
import com.ssafy.joblog.domain.coffeechat.dto.request.ChatUpdateRequestDto;
import com.ssafy.joblog.domain.coffeechat.service.ChatService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;

    // 1. 커피챗 신청하기
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createChat(@RequestBody ChatCreateRequestDto chatCreateRequestDto) {
        chatService.createChat(chatCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 커피챗 조회하기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getChats(@PathVariable Integer userId) {
        return new ResponseEntity<>(chatService.getChatsByUserId(userId), HttpStatus.OK);
    }

    // 3. 커피챗 수락하기
    @PatchMapping("/accept/{chatId}")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto acceptChat(@PathVariable Integer chatId) {
        chatService.markAcceptChat(chatId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 4. 커피챗 거절하기
    @DeleteMapping("/delete/{chatId}")
    public StatusResponseDto markBanChat(@PathVariable Integer chatId) {
        chatService.markBanChat(chatId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}
