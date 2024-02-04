package com.ssafy.joblog.domain.recruit.service;

import com.ssafy.joblog.domain.recruit.dto.responseDto.JobCategoryResponseDto;
import com.ssafy.joblog.domain.recruit.entity.JobCategory;
import com.ssafy.joblog.domain.recruit.repository.JobCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JobCategoryService {
    private final JobCategoryRepository jobCategoryRepository;

    public List<JobCategoryResponseDto> findAllJobCategory() {
        List<JobCategory> jobCategories = jobCategoryRepository.findAllByParentIsNull();
        List<JobCategoryResponseDto> jobCategoryResponseDtos = new ArrayList<>();
        for (JobCategory jobCategory: jobCategories){
            jobCategoryResponseDtos.add(JobCategoryResponseDto.fromEntity(jobCategory));
        }
        return jobCategoryResponseDtos;
    }

}
