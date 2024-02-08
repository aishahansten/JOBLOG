package com.ssafy.joblog.domain.essay.controller;

import com.ssafy.joblog.domain.essay.dto.request.EssayCreateRequestDto;
import com.ssafy.joblog.domain.essay.dto.request.EssayUpdateRequestDto;
import com.ssafy.joblog.domain.essay.service.EssayService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/essay")
@RequiredArgsConstructor
@RestController
public class EssayController {

    private final EssayService essayService;

    // 1. 자소서 등록하기
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createEssay(@RequestBody EssayCreateRequestDto essayCreateRequestDto) {
        essayService.createEssay(essayCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 자소서 조회하기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEssays(@PathVariable Integer userId) {
        return new ResponseEntity<>(essayService.getEssays(userId), HttpStatus.OK);
    }

    // 3. 자소서 상세 조회하기
    @GetMapping("/detail/{essayId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPost(@PathVariable Integer essayId){
        return new ResponseEntity<>(essayService.getEssay(essayId), HttpStatus.OK);
    }

    // 4. 자소서 수정하기
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateEssay(@RequestBody EssayUpdateRequestDto essayUpdateRequestDto){
        essayService.updateEssay(essayUpdateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 5. 자소서 삭제하기
    @DeleteMapping("/delete/{essayId}")
    public StatusResponseDto markDeletedEssay(@PathVariable Integer essayId) {
        essayService.markDeletedEssay(essayId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}
