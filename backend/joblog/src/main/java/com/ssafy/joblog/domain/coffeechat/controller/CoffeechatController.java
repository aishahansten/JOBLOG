package com.ssafy.joblog.domain.coffeechat.controller;

import com.ssafy.joblog.domain.coffeechat.dto.request.CoffeechatCreateRequestDto;
import com.ssafy.joblog.domain.coffeechat.service.CoffeechatService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class CoffeechatController {

    private final CoffeechatService coffeechatService;

    // 1. 커피챗 신청하기
    @PostMapping("/apply")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createRoom(@RequestBody CoffeechatCreateRequestDto coffeechatCreateRequestDto) {
        coffeechatService.createRoom(coffeechatCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }


    // 2. 커피챗 조회하기
    // 3. 커피챗 상세 조회하기
    // 4. 머피챗 수락하기
    // 5. 커피챗 거절하기
}
