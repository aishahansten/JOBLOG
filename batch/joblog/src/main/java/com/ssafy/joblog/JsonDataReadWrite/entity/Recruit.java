package com.ssafy.joblog.JsonDataReadWrite.entity;

import com.ssafy.joblog.JsonDataReadWrite.dto.requestDto.RecruitRequestDto;
import com.ssafy.joblog.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyCode;
    private String companyName;

    private Long jobId;
    private String title;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<>();

    @Column(length = 2000)
    private String jobDescription;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL)
    private List<JobType> jobTypes = new ArrayList<>();

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL)
    private List<JobCategoryRecruit> jobCategoryRecruits = new ArrayList<>();

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL)
    private List<Industry> industries = new ArrayList<>();

    private String experienceLevel;
    private int experienceLevelCode;
    private int experienceLevelMin;
    private int experienceLevelMax;
    private String requiredEducationLevel;
    private int requiredEducationLevelCode;
    private String salary;
    private int salaryCode;
    private Long postingTimestamp;
    private LocalDateTime postingDate;
    private Long modificationTimestamp;
    private Long openingTimestamp;
    private Long expirationTimestamp;
    private LocalDateTime expirationDate;
    private String active;
    private int activeCode;
    private String closeType;
    private int closeTypeCode;

    @Builder
    public Recruit(Long companyCode, String companyName, Long jobId, String title, List<Location> locations, String jobDescription, List<JobType> jobTypes, List<JobCategoryRecruit> jobCategoryRecruits, List<Industry> industries, String experienceLevel, int experienceLevelCode, int experienceLevelMin, int experienceLevelMax, String requiredEducationLevel, int requiredEducationLevelCode, String salary, int salaryCode, Long postingTimestamp, LocalDateTime postingDate, Long modificationTimestamp, Long openingTimestamp, Long expirationTimestamp, LocalDateTime expirationDate, String active, int activeCode, String closeType, int closeTypeCode) {
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.jobId = jobId;
        this.title = title;
        this.locations = locations;
        this.jobDescription = jobDescription;
        this.jobTypes = jobTypes;
        this.jobCategoryRecruits = jobCategoryRecruits;
        this.industries = industries;
        this.experienceLevel = experienceLevel;
        this.experienceLevelCode = experienceLevelCode;
        this.experienceLevelMin = experienceLevelMin;
        this.experienceLevelMax = experienceLevelMax;
        this.requiredEducationLevel = requiredEducationLevel;
        this.requiredEducationLevelCode = requiredEducationLevelCode;
        this.salary = salary;
        this.salaryCode = salaryCode;
        this.postingTimestamp = postingTimestamp;
        this.postingDate = postingDate;
        this.modificationTimestamp = modificationTimestamp;
        this.openingTimestamp = openingTimestamp;
        this.expirationTimestamp = expirationTimestamp;
        this.expirationDate = expirationDate;
        this.active = active;
        this.activeCode = activeCode;
        this.closeType = closeType;
        this.closeTypeCode = closeTypeCode;
    }

    public void addIndustry(Industry industry) {
        industries.add(industry);
        industry.setRecruit(this);
    }

    public void addLocation(Location location) {
        locations.add(location);
        location.setRecruit(this);
    }

    public void addJobType(JobType jobType) {
        jobTypes.add(jobType);
        jobType.setRecruit(this);
    }

    public void addJobCategoryRecruit(JobCategoryRecruit jobCategoryRecruit) {
        jobCategoryRecruits.add(jobCategoryRecruit);
        jobCategoryRecruit.setRecruit(this);
    }


    public static Recruit createRecruit(RecruitRequestDto item, Industry[] industries, Location[] locations, JobType[] jobTypes, JobCategoryRecruit[] jobCategoryRecruits) {
        //배열로 받아야 연관관계 함수 addXXX 작동해도 순서 안바뀜
        Recruit recruit = item.toEntity(
                new ArrayList<>(Arrays.asList(industries)),
                new ArrayList<>(Arrays.asList(locations)),
                new ArrayList<>(Arrays.asList(jobTypes)),
                new ArrayList<>(Arrays.asList(jobCategoryRecruits)));

        for (Industry industry : industries) {
            recruit.addIndustry(industry);
        }

        for (Location location : locations) {
            recruit.addLocation(location);
        }
        for (JobType jobType : jobTypes) {
            recruit.addJobType(jobType);
        }

        for (JobCategoryRecruit jobCategoryRecruit : jobCategoryRecruits) {
            recruit.addJobCategoryRecruit(jobCategoryRecruit);
        }
        return recruit;
    }
}
