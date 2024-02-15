package com.ssafy.joblog.JsonDataReadWrite;

import com.ssafy.joblog.JsonDataReadWrite.dto.requestDto.RecruitRequestDto;
import com.ssafy.joblog.JsonDataReadWrite.entity.*;
import com.ssafy.joblog.JsonDataReadWrite.repository.CompanyRepository;
import com.ssafy.joblog.JsonDataReadWrite.repository.JobCategoryRepository;
import com.ssafy.joblog.JsonDataReadWrite.repository.RecruitRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class JsonDataReadWriteConfig {
    @Autowired
    private JsonDataUtils jsonDataUtils;

    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final EntityManager entityManager;


    @Bean
    public Job jsonDataReadWriteJob(JobRepository jobRepository,
                                    Step jsonDataReadWriteStep) {
        return new JobBuilder("jsonDataReadWriteJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(jsonDataReadWriteStep)
                .build();
    }

    @JobScope
    @Bean
    public Step jsonDataReadWriteStep(JobRepository jobRepository,
                                      PlatformTransactionManager platformTransactionManager,
                                      ItemReader jsonDataReader,
                                      ItemProcessor jsonDataProcessor,
                                      ItemWriter jsonDataWriter) {
        return new StepBuilder("jsonDataReadWriteStep", jobRepository)
                .<RecruitRequestDto, Recruit>chunk(110, platformTransactionManager)
                .reader(jsonDataReader) //json 읽어오기
                .processor(jsonDataProcessor) //json dto[] -> entity[] 가공
                .writer(jsonDataWriter) //가공된 entity, DB에 save
                .build();
    }

    @StepScope
    @Bean
    public ItemWriter<Recruit> jsonDataWriter() {
        return recruitRepository::saveAll;
    }


    @StepScope
    @Bean
    public ItemProcessor<RecruitRequestDto, Recruit> jsonDataProcessor() {
        return item -> {
            if (item.getCompany().getDetail().getHref() != null) {
                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(item.getCompany().getDetail().getHref());
                String companyCodeValue = builder.build().getQueryParams().get("csn").get(0);
                Long companyCode = Long.parseLong(companyCodeValue);

                Optional<Company> company = companyRepository.findById(companyCode);
                if (!company.isPresent()) {
                    Company newCompany = companyRepository.save(
                            Company.builder()
                                    .companyCode(companyCode)
                                    .companyName(item.getCompany().getDetail().getName())
                                    .build()
                    );
                    entityManager.persist(newCompany);
                }
            }


            RecruitRequestDto.IndustryDTO industryDTO = item.getPosition().getIndustry();
            List<Industry> industries = new ArrayList<>();
            int industrySize = 0;
            if (industryDTO.getCode() != null) {
                List<String> industry = Arrays.asList(industryDTO.getName().split(","));
                List<String> industryCode = Arrays.asList(industryDTO.getCode().split(","));
                industrySize = industry.size();
                for (int i = 0; i < industrySize; i++) {
                    industries.add(Industry.builder()
                            .industry(industry.get(i))
                            .industryCode(Integer.parseInt(industryCode.get(i)))
                            .build());
                }
            }


            RecruitRequestDto.LocationDTO locationDTO = item.getPosition().getLocation();
            List<Location> locations = new ArrayList<>();
            int locationSize = 0;
            if (locationDTO.getCode() != null) {
                List<String> location = Arrays.asList(locationDTO.getName().split(","));
                List<String> locationCode = Arrays.asList(locationDTO.getCode().split(","));
                locationSize = location.size();
                for (int i = 0; i < locationSize; i++) {
                    locations.add(Location.builder()
                            .location(location.get(i))
                            .locationCode(Integer.parseInt(locationCode.get(i)))
                            .build());
                }
            }


            RecruitRequestDto.JobTypeDTO jobTypeDTO = item.getPosition().getJobType();
            List<JobType> jobTypes = new ArrayList<>();
            int jobTypeSize = 0;
            if (jobTypeDTO.getCode() != null) {
                List<String> jobType = jobTypeDTO.getName() != null ? Arrays.asList(jobTypeDTO.getName().split(",")) : new ArrayList<>();
                List<String> jobTypeCode = jobTypeDTO.getCode() != null ? Arrays.asList(jobTypeDTO.getCode().split(",")) : new ArrayList<>();
                jobTypeSize = jobType.size();
                for (int i = 0; i < jobTypeSize; i++) {
                    jobTypes.add(JobType.builder()
                            .jobType(jobType.get(i))
                            .jobTypeCode(Integer.parseInt(jobTypeCode.get(i)))
                            .build());
                }

            }

            RecruitRequestDto.JobMidCodeDTO jobMidCodeDTO = item.getPosition().getJobMidCode();
            RecruitRequestDto.JobCodeDTO jobCodeDTO = item.getPosition().getJobCode();
            //상위, 하위 코드 합치기
            String job = "";
            if (jobMidCodeDTO != null && jobCodeDTO != null) {
                job = jobMidCodeDTO.getCode() + "," + jobCodeDTO.getCode();
            } else if (jobMidCodeDTO != null) {
                job = jobMidCodeDTO.getCode();
            } else if (jobCodeDTO != null) {
                job = jobCodeDTO.getCode();
            }
            List<String> jobCode = new ArrayList<>();
            if (!job.equals("")) {
                jobCode = Arrays.asList(job.split(","));
            }

            List<JobCategoryRecruit> jobCodes = new ArrayList<>();
            int jobCodeSize = jobCode.size();
            for (int i = 0; i < jobCodeSize; i++) {
                JobCategory jobCategory = jobCategoryRepository.getReferenceById(Integer.parseInt(jobCode.get(i)));
                jobCodes.add(JobCategoryRecruit.builder()
                        .jobCategory(jobCategory)
                        .build());
            }

            return Recruit.createRecruit(item,
                    industries.toArray(new Industry[industrySize]),
                    locations.toArray(new Location[locationSize]),
                    jobTypes.toArray(new JobType[jobTypeSize]),
                    jobCodes.toArray(new JobCategoryRecruit[jobCodeSize]));
        };
    }

    @StepScope
    @Bean //json, jdbc, repository 등에서 데이터 읽어올 수 있음
    public ItemReader<RecruitRequestDto> jsonDataReader() throws IOException {
        return new ListItemReader(jsonDataUtils.getJsonDataAsDtoList());
    }

}
