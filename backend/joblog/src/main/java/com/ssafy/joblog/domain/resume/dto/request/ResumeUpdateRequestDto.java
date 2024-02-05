package com.ssafy.joblog.domain.resume.dto.request;

import com.ssafy.joblog.domain.resume.entity.Resume;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeUpdateRequestDto {
    private int resumeId;
    private int userId;
    private String title;
    private String job;
    private List<Integer> infoList = new ArrayList<>();

    public Resume createResume(User user){
        return Resume.builder()
                .id(this.resumeId)
                .user(user)
                .title(this.title)
                .job(this.job)
                .build();
    }
}