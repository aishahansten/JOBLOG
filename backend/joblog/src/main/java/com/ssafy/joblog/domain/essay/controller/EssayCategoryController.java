package com.ssafy.joblog.domain.essay.controller;

import com.ssafy.joblog.domain.essay.dto.request.EssayCategoryCreateRequestDto;
import com.ssafy.joblog.domain.essay.dto.request.EssayCategoryUpdateRequestDto;
import com.ssafy.joblog.domain.essay.service.EssayCategoryService;
import com.ssafy.joblog.global.dto.StatusResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/category")
@RequiredArgsConstructor
@RestController
public class EssayCategoryController {

    private final EssayCategoryService essayCategoryService;

    // 1. 자소서 카테고리 등록하기
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusResponseDto createEssayCategory(@RequestBody EssayCategoryCreateRequestDto essayCategoryCreateRequestDto) {
        essayCategoryService.createEssayCategory(essayCategoryCreateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 2. 자소서 카테고리 조회하기
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getEssayCategories(@PathVariable Integer userId) {
        return new ResponseEntity<>(essayCategoryService.getEssayCategories(userId), HttpStatus.OK);
    }
    // 3. 자소서 카테고리 수정하기
    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public StatusResponseDto updateEssayCategory(@RequestBody EssayCategoryUpdateRequestDto essayCategoryUpdateRequestDto) {
        essayCategoryService.updateEssayCategory(essayCategoryUpdateRequestDto);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }

    // 4. 자소서 카테고리 삭제하기
    @DeleteMapping("/delete/{categoryId}")
    public StatusResponseDto markDeletedEssayCategory(@PathVariable Integer categoryId) {
        essayCategoryService.markDeletedEssayCategory(categoryId);
        String message = "ok";
        return new StatusResponseDto(200, message);
    }
}
