package com.ssafy.joblog.domain.recruit.service;

import com.ssafy.joblog.domain.recruit.Specification.RecruitSpecification;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitDetailResponseDto;
import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import com.ssafy.joblog.domain.recruit.entity.Recruit;
import com.ssafy.joblog.domain.recruit.repository.RecruitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<RecruitListResponseDto> findAllRecruit() {
        List<Recruit> allRecruit = recruitRepository.findAll();

        List<RecruitListResponseDto> allRecruitDto = new ArrayList<>();
        for (Recruit recruit : allRecruit) {
            allRecruitDto.add(RecruitListResponseDto.fromEntity(recruit));
        }
        return allRecruitDto;
    }

    public RecruitDetailResponseDto findRecruit(Long recruitId) {
        Recruit recruit = recruitRepository.findById(recruitId)
                .orElseThrow(()-> new IllegalArgumentException("해당 채용공고가 존재하지 않습니다"));

        return RecruitDetailResponseDto.fromEntity(recruit);
    }

    public List<RecruitListResponseDto> findSearchRecruit(String active, String expLv, String jobCategory, String keyword) {
        //전체 검색(필터링 x)
        if (active == null && expLv == null && jobCategory == null && keyword == null) {
            return findAllRecruit();
        }

        Map<String, Object> searchKeys = new HashMap<>();

        if (active != null) searchKeys.put("activeCode", active);
        if (expLv != null) searchKeys.put("experienceLevelCode", expLv);
        if (jobCategory != null) searchKeys.put("jobCategory", jobCategory);
        if (keyword != null) searchKeys.put("keyword", keyword);

        return recruitRepository.findAll(RecruitSpecification.searchRecruit(searchKeys))
                .stream().map(RecruitListResponseDto::fromEntity)
                .collect(Collectors.toList());

    }


}
