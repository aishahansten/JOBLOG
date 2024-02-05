package com.ssafy.joblog.domain.resume.dto.request;

import com.ssafy.joblog.domain.resume.entity.Info;
import com.ssafy.joblog.domain.resume.entity.InfoCategory;
import com.ssafy.joblog.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoCreateRequestDto {
    private int userId;
    private InfoCategory infoCategory;
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

    public Info createInfo(User user){
        return Info.builder()
                .user(user)
                .infoCategory(this.infoCategory)
                .title(this.title)
                .institutionName(this.institutionName)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .yesOrNot(this.yesOrNot)
                .dayOrNight(this.dayOrNight)
                .description(this.description)
                .level(this.level)
                .skillLevel(this.skillLevel)
                .techStack(this.techStack)
                .role(this.role)
                .graduationStatus(this.graduationStatus)
                .totalMember(this.totalMember)
                .link(this.link)
                .build();
    }



}
