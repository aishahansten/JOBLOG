package com.ssafy.joblog.domain.resume.controller;

import com.ssafy.joblog.domain.resume.dto.request.ResumeCreateRequestDto;
import com.ssafy.joblog.domain.resume.dto.request.ResumeUpdateRequestDto;
import com.ssafy.joblog.domain.resume.dto.response.ResumeListResponseDto;
import com.ssafy.joblog.domain.resume.dto.response.ResumeWithInfoResponseDto;
import com.ssafy.joblog.domain.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/{userId}") //유저의 이력서 리스트
    public ResponseEntity<List<ResumeListResponseDto>> findAllResume(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(resumeService.findAllResume(userId));
    }

    @GetMapping("/detail/{resumeId}") //이력서 상세정보
    public ResponseEntity<ResumeWithInfoResponseDto> findResumeWithInfo(@PathVariable(value = "resumeId") int resumeId) {
        return ResponseEntity.ok(resumeService.findResumeWithInfo(resumeId));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@RequestBody ResumeCreateRequestDto resumeRequestDto){
        resumeService.create(resumeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody ResumeUpdateRequestDto resumeUpdateRequestDto){
        resumeService.update(resumeUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{resumeId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "resumeId") int resumeId){
        resumeService.delete(resumeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
