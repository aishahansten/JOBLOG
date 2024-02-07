package com.ssafy.joblog.domain.myRecruit.service;

import com.ssafy.joblog.domain.myRecruit.dto.request.ReviewRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.request.SelectionRequestDto;
import com.ssafy.joblog.domain.myRecruit.dto.response.ReviewPrivateResponseDto;
import com.ssafy.joblog.domain.myRecruit.entity.MyRecruit;
import com.ssafy.joblog.domain.myRecruit.entity.Selection;
import com.ssafy.joblog.domain.myRecruit.repository.MyRecruitRepository;
import com.ssafy.joblog.domain.myRecruit.repository.SelectionRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final MyRecruitRepository myRecruitRepository;
    private final UserRepository userRepository;
    private final SelectionRepository selectionRepository;

    public List<ReviewPrivateResponseDto> findAllSelectionReview(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        List<Selection> selectionList = selectionRepository.findAllSelectionByUserIdAndIsDeleteIsFalseAndReviewOrNotIsTrue(user.getId());
        List<ReviewPrivateResponseDto> reviewPrivateResponseDto = new ArrayList<>();
        for (Selection selection : selectionList) {
            reviewPrivateResponseDto.add(ReviewPrivateResponseDto.fromEntity(selection));
        }
        return reviewPrivateResponseDto;
    }

    public void create(ReviewRequestDto reviewRequestDto) {
        Selection selection = selectionRepository.findByIdAndIsDeleteIsFalse(reviewRequestDto.getSelectionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전형 정보가 존재하지 않습니다"));
        selection.createReview(reviewRequestDto);
    }

    public void update(ReviewRequestDto reviewRequestDto) {
        Selection selection = selectionRepository.findByIdAndIsDeleteIsFalse(reviewRequestDto.getSelectionId())
                .orElseThrow(() -> new IllegalArgumentException("해당 전형 정보가 존재하지 않습니다"));
        selection.updateReview(reviewRequestDto);
    }

    public void delete(int selectionId) {
        Selection selection = selectionRepository.findByIdAndIsDeleteIsFalse(selectionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 전형 정보가 존재하지 않습니다"));
        if (!selection.getReviewOrNot())
            throw new IllegalArgumentException("리뷰가 존재하지 않습니다");
        selection.deleteReview();
    }


}
