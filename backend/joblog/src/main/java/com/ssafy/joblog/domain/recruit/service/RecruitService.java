package com.ssafy.joblog.domain.recruit.service;

import com.ssafy.joblog.domain.recruit.Specification.RecruitSpecification;
import com.ssafy.joblog.domain.recruit.dto.responseDto.PageRecruitListResponseDto;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitDetailResponseDto;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RecruitService {
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;

    public PageRecruitListResponseDto findAllRecruit(Pageable pageable) {
        Page<Recruit> recruitPages = recruitRepository.findAll(pageable);

        List<Recruit> allRecruit = recruitPages.getContent();

        List<RecruitListResponseDto> allRecruitDto = new ArrayList<>();
        for (Recruit recruit : allRecruit) {
            allRecruitDto.add(RecruitListResponseDto.fromEntity(recruit));
        }
        return PageRecruitListResponseDto.fromEntity(recruitPages.getTotalPages(), recruitPages.getTotalElements(), allRecruitDto);
    }

    public RecruitDetailResponseDto findRecruit(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("해당 채용공고가 존재하지 않습니다"));

        return RecruitDetailResponseDto.fromEntity(recruit);
    }


    public PageRecruitListResponseDto findRecommendRecruit(Pageable pageable, int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다"));
        return findSearchRecruit(pageable, null, null, Integer.toString(user.getObjective()), null);
    }

    public PageRecruitListResponseDto findSearchRecruit(Pageable pageable, String active, String expLv, String jobCategory, String keyword) {
        //전체 검색(필터링 x)
        if (active == null && expLv == null && jobCategory == null && keyword == null) {
            return findAllRecruit(pageable);
        }

        Map<String, Object> searchKeys = new HashMap<>();

        if (active != null) searchKeys.put("activeCode", active);
        if (expLv != null) searchKeys.put("experienceLevelCode", expLv);
        if (jobCategory != null) searchKeys.put("jobCategory", jobCategory);
        if (keyword != null) searchKeys.put("keyword", keyword);

        Page<Recruit> recruitPages = recruitRepository.findAll(RecruitSpecification.searchRecruit(searchKeys), pageable);
        List<RecruitListResponseDto> recruitListResponseDtos = recruitPages.getContent()
                .stream()
                .map(RecruitListResponseDto::fromEntity)
                .collect(Collectors.toList());
        return PageRecruitListResponseDto.fromEntity(recruitPages.getTotalPages(), recruitPages.getTotalElements(), recruitListResponseDtos);
    }

}
