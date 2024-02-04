package com.ssafy.joblog.domain.recruit.dto.responseDto;

import com.ssafy.joblog.domain.recruit.entity.JobCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobCategoryResponseDto {
    private int jobCode;
    private String jobName;
    private List<JobCategoryResponseDto> child;

    public static JobCategoryResponseDto fromEntity(JobCategory job) {
        List<JobCategoryResponseDto> children = new ArrayList<>();
        for (JobCategory jobChild : job.getChild()) {
            children.add(JobCategoryResponseDto.fromEntity(jobChild));
        }
        return JobCategoryResponseDto.builder()
                .jobCode(job.getJobCode())
                .jobName(job.getJobName())
                .child(children)
                .build();
    }
}
