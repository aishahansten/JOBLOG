package com.ssafy.joblog.domain.resume.dto.response;

import com.ssafy.joblog.domain.recruit.dto.responseDto.RecruitListResponseDto;
import com.ssafy.joblog.domain.resume.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeListResponseDto {
    private int id;
    private String title;
    private String job;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public static ResumeListResponseDto fromEntity(Resume resume){
        return ResumeListResponseDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .job(resume.getJob())
                .createDate(resume.getCreatedDate())
                .modifiedDate(resume.getModifiedDate())
                .build();
    }
}
