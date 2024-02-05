package com.ssafy.joblog.domain.resume.controller;

import com.ssafy.joblog.domain.resume.dto.request.InfoCreateRequestDto;
import com.ssafy.joblog.domain.resume.dto.request.InfoUpdateRequestDto;
import com.ssafy.joblog.domain.resume.dto.response.InfoResponseDto;
import com.ssafy.joblog.domain.resume.service.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/info")
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<InfoResponseDto>> findAllInfo(@PathVariable(value = "userId") int userId) {
        return ResponseEntity.ok(infoService.findAllInfo(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> create(@RequestBody InfoCreateRequestDto infoCreateRequestDto){
        infoService.create(infoCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody InfoUpdateRequestDto infoUpdateRequestDto){
        infoService.update(infoUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{infoId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "infoId") int infoId){
        infoService.delete(infoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
