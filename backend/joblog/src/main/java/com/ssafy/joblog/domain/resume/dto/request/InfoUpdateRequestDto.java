package com.ssafy.joblog.domain.resume.dto.request;

import com.ssafy.joblog.domain.resume.entity.InfoCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoUpdateRequestDto {
    private int userId;
    private int infoId;
    private String title;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer yesOrNot;
    private Integer dayOrNight;
    private String description;
    private String level;
    private Integer skillLevel;
    private String techStack;
    private String role;
    private Integer graduationStatus;
    private Integer totalMember;
    private String link;
}
