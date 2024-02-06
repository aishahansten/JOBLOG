package com.ssafy.joblog.domain.myRecruit.controller;

import com.ssafy.joblog.domain.myRecruit.dto.request.ReviewRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.ReviewPrivateResponseDto;
import com.ssafy.joblog.domain.myRecruit.service.ReviewService;
import com.ssafy.joblog.domain.myRecruit.service.SelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/selectionReview")
public class ReviewController {
    private final ReviewService reviewService;


    @GetMapping("/{userId}") //유저의 전형리뷰 리스트 조회
    public ResponseEntity<List<ReviewPrivateResponseDto>> findAllSelectionReview(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(reviewService.findAllSelectionReview(userId));
    }


    @PostMapping("/register") //전형리뷰 생성
    public ResponseEntity<Void> create(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.create(reviewRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ReviewRequestDto reviewRequestDto){
        reviewService.update(reviewRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{selectionId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "selectionId") int selectionId){
        reviewService.delete(selectionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
