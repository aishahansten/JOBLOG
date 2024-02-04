package com.ssafy.joblog.domain.company.controller;

import com.ssafy.joblog.domain.company.dto.requestDto.CompanyReviewCreateRequestDto;
import com.ssafy.joblog.domain.company.dto.requestDto.CompanyReviewUpdateRequestDto;
import com.ssafy.joblog.domain.company.service.CompanyReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companyReview")
public class CompanyReviewController {
    private final CompanyReviewService companyReviewService;

    @PostMapping("/register")
    public ResponseEntity<Void> create(@RequestBody CompanyReviewCreateRequestDto companyReviewRequestDto) {
        companyReviewService.create(companyReviewRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody CompanyReviewUpdateRequestDto companyReviewUpdateRequestDto) {
        companyReviewService.update(companyReviewUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "reviewId") Long reviewId) {
        companyReviewService.delete(reviewId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
