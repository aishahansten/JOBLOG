package com.ssafy.joblog.domain.user.controller;

import com.ssafy.joblog.domain.user.dto.request.FollowRequestDto;
import com.ssafy.joblog.domain.user.dto.response.FollowListResponseDto;
import com.ssafy.joblog.domain.user.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
    private final FollowService followService;

    @PostMapping("")
    public ResponseEntity<Void> followChatter(@RequestBody FollowRequestDto followRequestDto) {
        followService.followChatter(followRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{userId}") //유저의 팔로잉 리스트
    public ResponseEntity<List<FollowListResponseDto>> followChatter(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(followService.findAllFollowingListByUserId(userId));
    }

}
