package com.ssafy.joblog.domain.diary.service;


import com.ssafy.joblog.domain.diary.dto.request.DiaryCreateRequestDto;
import com.ssafy.joblog.domain.diary.dto.request.DiaryUpdateRequestDto;
import com.ssafy.joblog.domain.diary.dto.response.DiaryResponseDto;
import com.ssafy.joblog.domain.diary.entity.Diary;
import com.ssafy.joblog.domain.diary.repository.DiaryRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Data
public class DiaryService {

    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    // 1. 일기 등록하기
    public void createDiary(DiaryCreateRequestDto diaryCreateRequestDto) {
        User user = userRepository.findById(diaryCreateRequestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));;
        Diary diary = diaryCreateRequestDto.createDiary(user);
        diaryRepository.save(diary);
    }

    // 2. 일기 조회하기
    @Transactional(readOnly = true)
    public List<DiaryResponseDto> getDiaries(Integer userId) {
        List<Diary> diaries = diaryRepository.findByUserId(userId);
        List<DiaryResponseDto> getDiariesList = new ArrayList<>();
        diaries.forEach(diary -> getDiariesList.add(diary.toDiaryResponseDto()));
        return getDiariesList;
    }

    // 3. 일기 상세 조회하기
    @Transactional(readOnly = true)
    public DiaryResponseDto getDiary(Integer diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 복기를 조회할 수 없습니다");
        });
        DiaryResponseDto diaryResponseDto = diary.toDiaryResponseDto();
        return diaryResponseDto;
    }

    // 4.  일기 수정하기
    @Transactional
    public void updateDiary(DiaryUpdateRequestDto diaryUpdateRequestDto) {
        Diary diary = diaryRepository.findById(diaryUpdateRequestDto.getDiaryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 복기가 존재하지 않습니다"));
        diary.updateDiary(diaryUpdateRequestDto);
    }

    // 5. 일기 삭제하기
    @Transactional
    public void markDeletedDiary(int diaryId) {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 복기를 찾을 수 없습니다");
        });
        diaryRepository.markDeletedDiary(diaryId);
    }

}