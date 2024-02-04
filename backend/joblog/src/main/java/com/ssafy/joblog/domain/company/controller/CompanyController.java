package com.ssafy.joblog.domain.company.controller;

import com.ssafy.joblog.domain.company.dto.responseDto.CompanyResponseDto;
import com.ssafy.joblog.domain.company.dto.responseDto.CompanyReviewListResponseDto;
import com.ssafy.joblog.domain.company.service.CompanyService;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("") //모든 회사 리스트 조회
    public ResponseEntity<List<CompanyResponseDto>> findAllCompany() {
        return ResponseEntity.ok(companyService.findAllCompany());
    }

    @GetMapping("/{companyCode}") //회사의 단일 조회
    public ResponseEntity<CompanyResponseDto> findCompany(@PathVariable(value = "companyCode") Long companyCode) {
        return ResponseEntity.ok(companyService.findCompany(companyCode));
    }

    @GetMapping("/{companyCode}/recruit") //회사의 채용 리스트 조회
    public ResponseEntity<List<RecruitListResponseDto>> findAllRecruitByCompany(@PathVariable(value = "companyCode") Long companyCode) {
        return ResponseEntity.ok(companyService.findAllRecruitByCompany(companyCode));
    }

    @GetMapping("/{companyCode}/companyReview") //회사의 기업리뷰 리스트 조회
    public ResponseEntity<List<CompanyReviewListResponseDto>> findAllReviewByCompany(@PathVariable(value = "companyCode") Long companyCode) {
        return ResponseEntity.ok(companyService.findAllReviewByCompany(companyCode));
    }


//    @GetMapping("/{companyCode}/recruitReview") //회사의 전형리뷰 리스트 조회


}
