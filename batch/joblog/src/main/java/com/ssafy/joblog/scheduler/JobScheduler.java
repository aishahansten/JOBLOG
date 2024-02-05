package com.ssafy.joblog.scheduler;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JobScheduler {
    @Autowired
    private Job JsonDataReadWriteJob;

    @Autowired //joblauncher 직접 지정
    private JobLauncher jobLauncher;

//    @Scheduled(cron = "0 */60 * * * *") //60분마다 실행
    @Scheduled(cron = "0 0 0,4,8,12,16,20 * * ?", initialDelay = 60000, zone = "Asia/Seoul")
    public void JsonDataReadWriteJobRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParameters(
                Collections.singletonMap("requestTime", new JobParameter(System.currentTimeMillis(), Long.class))
        );
        // .run(실행시킬 job 명시, job 실행시 필요한 parameter)
        //계속 변하는 파라미터의 값을 넣어줘야 계속해서 job이 실행됨
        jobLauncher.run(JsonDataReadWriteJob, jobParameters);
    }
}
