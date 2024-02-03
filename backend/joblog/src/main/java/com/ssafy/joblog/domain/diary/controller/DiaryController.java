package com.ssafy.joblog.domain.diary.controller;

import com.ssafy.joblog.domain.diary.dto.request.DiaryCreateRequestDto;
import com.ssafy.joblog.domain.diary.dto.request.DiaryUpdateRequestDto;
import com.ssafy.joblog.domain.diary.service.DiaryService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value="/api/diary")
@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;


    // 1. 일기 등록하기
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createDiary(@RequestBody DiaryCreateRequestDto diaryCreateRequestDto) {
        diaryService.createDiary(diaryCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 일기 조회하기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDiaries(@PathVariable Integer userId) {
        return new ResponseEntity<>(diaryService.getDiaries(userId), HttpStatus.OK);
    }

    // 3. 일기 상세 조회하기
    @GetMapping("/detail/{diaryId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getDiary(@PathVariable Integer diaryId) {
        return new ResponseEntity<>(diaryService.getDiary(diaryId), HttpStatus.OK);
    }

    // 4. 일기 수정하기
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateDiary(@RequestBody DiaryUpdateRequestDto diaryUpdateRequestDto) {
        diaryService.updateDiary(diaryUpdateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 5. 일기 삭제하기
    @DeleteMapping("/delete/{diaryId}")
    public StatusResponseDto markDeletedDiary(@PathVariable Integer diaryId) {
        diaryService.markDeletedDiary(diaryId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

}