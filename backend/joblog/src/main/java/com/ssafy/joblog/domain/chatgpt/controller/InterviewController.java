package com.ssafy.joblog.domain.chatgpt.controller;

import com.ssafy.joblog.domain.chatgpt.dto.request.EssayListRequestDto;
import com.ssafy.joblog.domain.chatgpt.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interview")
public class InterviewController {
    private final InterviewService interviewService;

    @PostMapping("/prompt")
    public ResponseEntity<String> createPrompt(@RequestBody EssayListRequestDto essayListRequestDto){
        return ResponseEntity.ok(interviewService.createPrompt(essayListRequestDto));
    }

}