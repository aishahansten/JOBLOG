package com.ssafy.joblog.domain.myRecruit.controller;

import com.ssafy.joblog.domain.company.dto.responseDto.CompanyResponseDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.MyRecruitResponseDto;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.service.MyRecruitService;
import com.ssafy.joblog.domain.resume.dto.request.ResumeCreateRequestDto;
import com.ssafy.joblog.domain.user.dto.request.FollowRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myRecruit")
public class MyRecruitController {
    private final MyRecruitService myRecruitService;

    @GetMapping("/{userId}") //모든 마이채용 리스트 조회
    public ResponseEntity<List<MyRecruitResponseDto>> findAllMyRecruit(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(myRecruitService.findAllMyRecruit(userId));
    }

    @GetMapping("/detail/{myRecruitId}") //마이채용 상세 조회
    public ResponseEntity<MyRecruitResponseDto> findRecruit(@PathVariable(value = "myRecruitId") int myRecruitId) {
        return ResponseEntity.ok(myRecruitService.findMyRecruit(myRecruitId));
    }


    @PostMapping("/register") //마이채용 수동 생성
    public ResponseEntity<Void> create(@RequestBody MyRecruitRequestDto myRecruitRequestDto){
        myRecruitService.create(myRecruitRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody MyRecruitRequestDto myRecruitRequestDto){
        myRecruitService.update(myRecruitRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{myRecruitId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "myRecruitId") int myRecruitId){
        myRecruitService.delete(myRecruitId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
