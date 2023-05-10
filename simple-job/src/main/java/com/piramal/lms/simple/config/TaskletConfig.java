package com.piramal.lms.simple.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class TaskletConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public TaskletConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job taskletJob(Step firstStep, Step secondStep) {
        return new JobBuilder("simple job", jobRepository)
                .start(firstStep)
                .next(secondStep)
                .build();
    }

    @Bean
    public Step firstStep(Tasklet firstTasklet) {
        return new StepBuilder("first step", jobRepository).tasklet(firstTasklet, transactionManager).build();
    }

    @Bean
    public Step secondStep(Tasklet secondTasklet) {
        return new StepBuilder("second step", jobRepository).tasklet(secondTasklet, transactionManager).build();
    }

    @Bean
    public Tasklet firstTasklet() {
        return (contribution, chunkContext) -> {
            log.info("First Tasklet Executed");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Tasklet secondTasklet() {
        return (contribution, chunkContext) -> {
            log.info("Second Tasklet Executed");
            return RepeatStatus.FINISHED;
        };
    }
}
