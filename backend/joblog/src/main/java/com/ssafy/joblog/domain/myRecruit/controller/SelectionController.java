package com.ssafy.joblog.domain.myRecruit.controller;

import com.ssafy.joblog.domain.myRecruit.dto.request.MyRecruitRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.SelectionRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.MyRecruitResponseDto;
import com.ssafy.joblog.domain.myRecruit.service.MyRecruitService;
import com.ssafy.joblog.domain.myRecruit.service.SelectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/selection")
public class SelectionController {
    private final SelectionService selectionService;

//    @GetMapping("/{myRecruitId}") //해당 마이채용의 전형 리스트 조회
//    public ResponseEntity<MyRecruitResponseDto> findAllSelection(@PathVariable(value = "myRecruitId") int myRecruitId) {
//        return ResponseEntity.ok(myRecruitService.findAllSelection(myRecruitId));
//    }


    @PostMapping("/register") //전형 생성
    public ResponseEntity<Void> create(@RequestBody SelectionRequestDto selectionRequestDto){
        selectionService.create(selectionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<Void> update(@RequestBody SelectionRequestDto selectionRequestDto){
        selectionService.update(selectionRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{selectionId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "selectionId") int selectionId){
        selectionService.delete(selectionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
