package com.ssafy.joblog.domain.resume.entity;

import com.ssafy.joblog.domain.resume.dto.request.InfoUpdateRequestDto;
import com.ssafy.joblog.domain.user.entity.User;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "info", indexes = {
        @Index(name = "idx_resume_id", columnList = "resume_id")
})
public class Info extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = LAZY )
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "resume_id")
    private int resumeId;

    @Enumerated(EnumType.STRING)
    private InfoCategory infoCategory;
    //Info 종류 : education,activity, career, certificate,
    //language_ability, award, project, training, skill
    private String title;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer yesOrNot;
    private Integer dayOrNight;

    @Column(length=1000)
    private String description;

    private String level;
    private Integer skillLevel;
    private String techStack;
    private String role;
    private Integer graduationStatus;
    private Integer totalMember;
    private String link;

    @Builder
    public Info(int id, User user, int resumeId, InfoCategory infoCategory, String title, String institutionName, LocalDate startDate, LocalDate endDate, Integer yesOrNot, Integer dayOrNight, String description, String level, Integer skillLevel, String techStack, String role, Integer graduationStatus, Integer totalMember, String link) {
        this.id = id;
        this.user = user;
        this.resumeId = resumeId;
        this.infoCategory = infoCategory;
        this.title = title;
        this.institutionName = institutionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.yesOrNot = yesOrNot;
        this.dayOrNight = dayOrNight;
        this.description = description;
        this.level = level;
        this.skillLevel = skillLevel;
        this.techStack = techStack;
        this.role = role;
        this.graduationStatus = graduationStatus;
        this.totalMember = totalMember;
        this.link = link;
    }

    public Info createResumeInfo(User user, int resumeId){
        return Info.builder()
                .user(user)
                .resumeId(resumeId)
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

    public void updateInfo(InfoUpdateRequestDto infoUpdateRequestDto) {
        this.title = infoUpdateRequestDto.getTitle();
        this.institutionName = infoUpdateRequestDto.getInstitutionName();
        this.startDate = infoUpdateRequestDto.getStartDate();
        this.endDate = infoUpdateRequestDto.getEndDate();
        this.yesOrNot = infoUpdateRequestDto.getYesOrNot();
        this.dayOrNight = infoUpdateRequestDto.getDayOrNight();
        this.description = infoUpdateRequestDto.getDescription();
        this.level = infoUpdateRequestDto.getLevel();
        this.skillLevel = infoUpdateRequestDto.getSkillLevel();
        this.techStack = infoUpdateRequestDto.getTechStack();
        this.role = infoUpdateRequestDto.getRole();
        this.graduationStatus = infoUpdateRequestDto.getGraduationStatus();
        this.totalMember = infoUpdateRequestDto.getTotalMember();
        this.link = infoUpdateRequestDto.getLink();
    }

    public void deleteInfo() {
        this.isDelete = true;
    }
}
