package com.ssafy.joblog.domain.recruit.controller;

import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.RecruitScrapRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.MyRecruitResponseDto;
import com.ssafy.joblog.domain.myRecruit.service.MyRecruitService;
import com.ssafy.joblog.domain.recruit.dto.responseDto.JobCategoryResponseDto;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitDetailResponseDto;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import com.ssafy.joblog.domain.recruit.service.JobCategoryService;
import com.ssafy.joblog.domain.recruit.service.RecruitService;
import com.ssafy.joblog.domain.user.dto.request.FollowRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recruit")
public class RecruitController {

    private final RecruitService recruitService;
    private final JobCategoryService jobCategoryService;
    private final MyRecruitService myRecruitService;

    @GetMapping("/{recruitId}") //채용공고 상세조회
    public ResponseEntity<RecruitDetailResponseDto> findRecruit(@PathVariable(value = "recruitId") Long recruitId) {
        return ResponseEntity.ok(recruitService.findRecruit(recruitId));
    }

    @GetMapping("/recommend/{userId}") //유저 추천 채용공고 리스트
    public ResponseEntity<List<RecruitListResponseDto>> findRecommendRecruit(
            @PageableDefault(size = 20) Pageable pageable,
            @PathVariable(value = "userId") int userId
    ) {
        return ResponseEntity.ok(recruitService.findRecommendRecruit(pageable, userId));
    }

    @GetMapping("/search") //검색 및 전체 조회 리스트
    public ResponseEntity<List<RecruitListResponseDto>> findSearchRecruit(
            @PageableDefault(size = 20) Pageable pageable,
            @RequestParam(value = "active", required = false) String active, //1:진행중, 0:마감
            @RequestParam(value = "expLv", required = false) String expLv, //0,1,2,3 경력사항
            @RequestParam(value = "jobCategory", required = false) String jobCategory, //<다중 가능> 직무 카테고리
            @RequestParam(value = "keyword", required = false) String keyword //공고명+기업명 키워드
    ) {
        return ResponseEntity.ok(recruitService.findSearchRecruit(pageable, active, expLv, jobCategory, keyword));
    }

    @PostMapping("/scrap") //채용공고 스크랩 등록,취소
    public ResponseEntity<Void> scrapRecruit(@RequestBody RecruitScrapRequestDto recruitScrapRequestDto) {
        myRecruitService.createScrap(recruitScrapRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/category")
    public ResponseEntity<List<JobCategoryResponseDto>> findAllParentCategory() {
        return ResponseEntity.ok(jobCategoryService.findAllJobCategory());
    }


}
