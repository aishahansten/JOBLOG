package com.ssafy.joblog.JsonDataReadWrite.dto.requestDto;

import com.ssafy.joblog.JsonDataReadWrite.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecruitRequestDto {

    @JsonProperty("url")
    private String url;

    @JsonProperty("active")
    private int active;

    @JsonProperty("company")
    private CompanyDTO company;

    @JsonProperty("position")
    private PositionDTO position;

    @JsonProperty("keyword")
    private String keyword;

    @JsonProperty("salary")
    private SalaryDTO salary;

    @JsonProperty("id")
    private String id;

    @JsonProperty("posting-timestamp")
    private String postingTimestamp;

    @JsonProperty("modification-timestamp")
    private String modificationTimestamp;

    @JsonProperty("opening-timestamp")
    private String openingTimestamp;

    @JsonProperty("expiration-timestamp")
    private String expirationTimestamp;

    @JsonProperty("close-type")
    private CloseTypeDTO closeType;

    public Recruit toEntity(List<Industry> industries, List<Location> locations, List<JobType> jobTypes, List<JobCategoryRecruit> jobCategoryRecruits) {
        Long companyCode = -1l;
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.company.detail.href);
            String companyCodeValue = builder.build().getQueryParams().get("csn").get(0);
            if (companyCodeValue != null) {
                companyCode = Long.parseLong(companyCodeValue);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Recruit.builder()
                .company(Company.builder()
                        .companyName(this.company.detail.name)
                        .companyCode(companyCode)
                        .build())
//                .companyCode(companyCode)
//                .companyName(this.company.detail.name)
                .jobId(Long.valueOf(this.id))
                .title(this.position.title)
                .locations(locations)
                .jobDescription(this.position.jobCode.name)
                .jobTypes(jobTypes)
                .industries(industries)
                .jobCategoryRecruits(jobCategoryRecruits)
                .experienceLevel(this.position.experienceLevel.name)
                .experienceLevelCode(this.position.experienceLevel.code)
                .experienceLevelMin(this.position.experienceLevel.min)
                .experienceLevelMax(this.position.experienceLevel.max)
                .requiredEducationLevel(this.position.requiredEducationLevel.name)
                .requiredEducationLevelCode(Integer.parseInt(this.position.requiredEducationLevel.code))
                .salary(this.salary.name)
                .salaryCode(Integer.parseInt(this.salary.code))
                .postingTimestamp(Long.valueOf(this.postingTimestamp))
                .postingDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(this.postingTimestamp)), ZoneId.systemDefault()))
                .modificationTimestamp(Long.valueOf(this.modificationTimestamp))
                .openingTimestamp(Long.valueOf(this.openingTimestamp))
                .expirationTimestamp(Long.valueOf(this.expirationTimestamp))
                .expirationDate(LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(this.expirationTimestamp)), ZoneId.systemDefault()))
                .active((this.active == 0) ? "마감" : "진행중")
                .activeCode(this.active)
                .closeType(this.closeType.name)
                .closeTypeCode(Integer.parseInt(this.closeType.code))
                .build();
    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class CompanyDTO {

        @JsonProperty("detail")
        private CompanyDetailDTO detail;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class CompanyDetailDTO {

        @JsonProperty("href")
        private String href;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class PositionDTO {

        @JsonProperty("title")
        private String title;

        @JsonProperty("industry")
        private IndustryDTO industry;

        @JsonProperty("location")
        private LocationDTO location;

        @JsonProperty("job-type")
        private JobTypeDTO jobType;

        @JsonProperty("job-mid-code")
        private JobMidCodeDTO jobMidCode;

        @JsonProperty("job-code")
        private JobCodeDTO jobCode;

        @JsonProperty("experience-level")
        private ExperienceLevelDTO experienceLevel;

        @JsonProperty("required-education-level")
        private RequiredEducationLevelDTO requiredEducationLevel;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class IndustryDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class LocationDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class JobTypeDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class JobMidCodeDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class JobCodeDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class ExperienceLevelDTO {

        @JsonProperty("code")
        private int code;

        @JsonProperty("min")
        private int min;

        @JsonProperty("max")
        private int max;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class RequiredEducationLevelDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class SalaryDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

    @Getter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static public class CloseTypeDTO {

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

    }

}
