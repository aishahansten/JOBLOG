package com.ssafy.joblog.domain.resume.dto.response;

import com.ssafy.joblog.domain.resume.entity.Info;
import com.ssafy.joblog.domain.resume.entity.InfoCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoResponseDto {
    private int id;
    private int userId;
    private int resumeId;
    private InfoCategory infoCategory;
    private String title;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer yesOrNot; //boolean
    private Integer dayOrNight; //boolean
    private String description;
    private String level;
    private Integer skillLevel;
    private String techStack;
    private String role;
    private Integer graduationStatus;
    private Integer totalMember;
    private String link;

    public static InfoResponseDto fromEntity(Info info) {
        return InfoResponseDto.builder()
                .id(info.getId())
                .userId(info.getUser().getId())
                .resumeId(info.getResumeId())
                .infoCategory(info.getInfoCategory())
                .title(info.getTitle())
                .institutionName(info.getInstitutionName())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .yesOrNot(info.getYesOrNot())
                .dayOrNight(info.getDayOrNight())
                .description(info.getDescription())
                .level(info.getLevel())
                .skillLevel(info.getSkillLevel())
                .techStack(info.getTechStack())
                .role(info.getRole())
                .graduationStatus(info.getGraduationStatus())
                .totalMember(info.getTotalMember())
                .link(info.getLink())
                .build();
    }
}
