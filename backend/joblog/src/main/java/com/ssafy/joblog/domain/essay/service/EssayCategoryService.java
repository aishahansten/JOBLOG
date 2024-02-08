package com.ssafy.joblog.domain.essay.service;

import com.ssafy.joblog.domain.essay.dto.request.EssayCategoryCreateRequestDto;
import com.ssafy.joblog.domain.essay.dto.request.EssayCategoryUpdateRequestDto;
import com.ssafy.joblog.domain.essay.dto.response.EssayCategoryResponseDto;
import com.ssafy.joblog.domain.essay.entity.EssayCategory;
import com.ssafy.joblog.domain.essay.repository.EssayCategoryRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Data
public class EssayCategoryService {

    private final EssayCategoryRepository essayCategoryRepository;
    private final UserRepository userRepository;

    // 1. 자소서 카테고리 등록하기
    public void createEssayCategory(EssayCategoryCreateRequestDto essayCategoryCreateRequestDto) {
        User user = userRepository.findById(essayCategoryCreateRequestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        EssayCategory essayCategory = essayCategoryCreateRequestDto.createEssayCategory(user, essayCategoryCreateRequestDto.getCategoryName());
        essayCategoryRepository.save(essayCategory);
    }

    // 2. 자소서 카테고리 조회하기
    @Transactional(readOnly = true)
    public List<EssayCategoryResponseDto> getEssayCategories(Integer userId) {
        List<EssayCategory> categories = essayCategoryRepository.findByUserIdAndIsDeleteIsFalse(userId);
        List<EssayCategoryResponseDto> getCategoryList = new ArrayList<>();
        categories.forEach(category -> getCategoryList.add(EssayCategoryResponseDto.builder()
                .categoryId(category.getId())
                .categoryName(category.getQuestion_category_name())
                .build()));
        return getCategoryList;
    }

    // 3. 자소서 카테고리 수정하기
    @Transactional
    public void updateEssayCategory(EssayCategoryUpdateRequestDto essayCategoryUpdateRequestDto) {
        EssayCategory essayCategory = essayCategoryRepository.findById(essayCategoryUpdateRequestDto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다"));
        essayCategory.updateEssayCategory(essayCategoryUpdateRequestDto);
    }

    // 4. 자소서 카테고리 삭제하기
    @Transactional
    public void markDeletedEssayCategory(int categoryId) {
        EssayCategory essayCategory = essayCategoryRepository.findById(categoryId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("해당 카테고리가 존재하지 않습니다");
                });
        essayCategoryRepository.markDeletedEssayCategory(categoryId);
    }
}
