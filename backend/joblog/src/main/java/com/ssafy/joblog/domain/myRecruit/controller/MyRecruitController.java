package com.ssafy.joblog.domain.myRecruit.controller;

import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.service.MyRecruitService;
import com.ssafy.joblog.domain.resume.dto.request.ResumeCreateRequestDto;
import com.ssafy.joblog.domain.user.dto.request.FollowRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/myRecruit")
public class MyRecruitController {
    private final MyRecruitService myRecruitService;

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
//
//    @DeleteMapping("/delete/{resumeId}")
//    public ResponseEntity<Void> delete(@PathVariable(value = "resumeId") int resumeId){
//        resumeService.delete(resumeId);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
}
