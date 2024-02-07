package com.ssafy.joblog.domain.recruit.dto.responseDto;

import com.ssafy.joblog.domain.recruit.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecruitListResponseDto {
    private Long id;
    private Long companyCode;
    private String companyName;
    private String title;
    private Long jobId;
    private String locationDesc; //location list
    private String jobTypeDesc; //jobtype list
    private String industryDesc; //industry list
    private String jobCategoryDesc; //job child list
    private String experienceLevel;
    private String requiredEducationLevel;
    private LocalDateTime expirationDate;
    private String active;
    private int activeCode;
    private String closeType;

    public static RecruitListResponseDto fromEntity(Recruit recruit) {
        List<String> industryNames = new ArrayList<>();
        for (Industry industry : recruit.getIndustries()) {
            industryNames.add(industry.getIndustry());
        }
        String industryDesc = String.join(", ", industryNames);

        List<String> locationNames = new ArrayList<>();
        for (Location location : recruit.getLocations()) {
            locationNames.add(location.getLocation());
        }
        String locationDesc = String.join(", ", locationNames).replaceAll("&gt;", ">");

        List<String> jobTypeNames = new ArrayList<>();
        for (JobType jobType : recruit.getJobTypes()) {
            jobTypeNames.add(jobType.getJobType());
        }
        String jobTypeDesc = String.join(", ", jobTypeNames);

        List<String> jobCategoryNames = new ArrayList<>();
        for (JobCategoryRecruit jobCategoryRecruit : recruit.getJobCategoryRecruits()) {
            // 자식 카테고리만 가져옴
            if (jobCategoryRecruit.getJobCategory().getParent() != null) {
                jobCategoryNames.add(jobCategoryRecruit.getJobCategory().getJobName());
            }
        }
        String jobCategoryDesc = String.join(", ", jobCategoryNames);

        return RecruitListResponseDto.builder()
                .id(recruit.getId())
                .companyCode(recruit.getCompanyCode())
                .companyName(recruit.getCompanyName())
                .title(recruit.getTitle())
                .jobId(recruit.getJobId())
                .locationDesc(locationDesc)
                .jobTypeDesc(jobTypeDesc)
                .industryDesc(industryDesc)
                .jobCategoryDesc(jobCategoryDesc)
                .experienceLevel(recruit.getExperienceLevel())
                .requiredEducationLevel(recruit.getRequiredEducationLevel())
                .expirationDate(recruit.getExpirationDate())
                .active(recruit.getActive())
                .activeCode(recruit.getActiveCode())
                .closeType(recruit.getCloseType())
                .build();
    }
}
