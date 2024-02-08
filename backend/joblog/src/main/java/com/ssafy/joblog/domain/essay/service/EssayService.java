package com.ssafy.joblog.domain.essay.service;

import com.ssafy.joblog.domain.essay.dto.request.EssayCreateRequestDto;
import com.ssafy.joblog.domain.essay.dto.request.EssayUpdateRequestDto;
import com.ssafy.joblog.domain.essay.dto.response.EssayResponseDto;
import com.ssafy.joblog.domain.essay.entity.Essay;
import com.ssafy.joblog.domain.essay.entity.EssayCategory;
import com.ssafy.joblog.domain.essay.repository.EssayCategoryRepository;
import com.ssafy.joblog.domain.essay.repository.EssayRepository;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Data
public class EssayService {

    private final EssayRepository essayRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final EssayCategoryRepository essayCategoryRepository;

    // 1. 자소서 등록하기
    public void createEssay(EssayCreateRequestDto essayCreateRequestDto) {
        User user = userRepository.findById(essayCreateRequestDto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        Recruit recruit = recruitRepository.findById(essayCreateRequestDto.getRecruitId())
                .orElseThrow(()-> new IllegalArgumentException("해당 채용이 존재하지 않습니다"));
        EssayCategory essayCategory = essayCategoryRepository.findById(essayCreateRequestDto.getCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("해당 자소서 항목이 존재하지 않습니다"));
        Essay essay = essayCreateRequestDto.createEssay(user, recruit, essayCategory);
        essayRepository.save(essay);
    }

    // 2. 자소서 조회하기
    @Transactional(readOnly = true)
    public List<EssayResponseDto> getEssays(Integer userId) {
        List<Essay> essays = essayRepository.findByUserIdAndIsDeleteIsFalse(userId);
        List<EssayResponseDto> getEssaysList = new ArrayList<>();
        essays.forEach(essay -> getEssaysList.add(EssayResponseDto.builder()
                .essayId(essay.getId())
                .recruitId(essay.getRecruit().getId())
                .categoryId(essay.getEssayCategory().getId())
                .userId(essay.getUser().getId())
                .question(essay.getQuestion())
                .answer(essay.getAnswer())
                .build()));
        return getEssaysList;
    }

    // 3. 자소서 상세 조회하기
    @Transactional(readOnly = true)
    public EssayResponseDto getEssay(Integer id) {
        Optional<Essay> optionalEssay = essayRepository.findByIdAndIsDeleteIsFalse(id);
        Essay essay = optionalEssay.orElseThrow(() -> new NotFoundException("해당 자소서가 존재하지 않습니다"));
        EssayResponseDto essayResponseDto = essay.toEssayResponseDto();
        return essayResponseDto;
    }

    // 4. 자소서 수정하기
    @Transactional
    public void updateEssay(EssayUpdateRequestDto essayUpdateRequestDto) {
        Essay essay = essayRepository.findById(essayUpdateRequestDto.getEssayId())
                .orElseThrow(()-> new IllegalArgumentException("해당 자소서가 존재하지 않습니다"));
        Recruit recruit = recruitRepository.findById(essayUpdateRequestDto.getRecruitId())
                .orElseThrow(()-> new IllegalArgumentException("해당 채용이 존재하지 않습니다"));
        EssayCategory essayCategory = essayCategoryRepository.findById(essayUpdateRequestDto.getCategoryId())
                .orElseThrow(()-> new IllegalArgumentException("해당 자소서 항목이 존재하지 않습니다"));
        essay.updateEssay(essayUpdateRequestDto, recruit, essayCategory);
    }

    // 5. 자소서 삭제하기
    @Transactional
    public void markDeletedEssay(int essayId) {
        Essay essay = essayRepository.findById(essayId).orElseThrow(() -> {
            return new IllegalArgumentException("해당 자소서를 찾을 수 없습니다");
        });
        essayRepository.markDeletedEssay(essayId);
    }
}
