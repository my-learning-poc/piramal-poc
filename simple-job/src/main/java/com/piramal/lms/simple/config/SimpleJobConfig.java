package com.piramal.lms.simple.config;

import com.piramal.lms.simple.batch.FileItemProcessor;
import com.piramal.lms.simple.model.ActiveLoan;
import com.piramal.lms.simple.model.Loan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration
@Slf4j
public class SimpleJobConfig {
    private final FlatFileItemReader<Loan> flatFileItemReader;
	private final FileItemProcessor fileItemProcessor;
    private final FlatFileItemWriter<ActiveLoan> flatFileItemWriter;
	private final JobRepository jobRepository;
	private final PlatformTransactionManager transactionManager;

    public SimpleJobConfig(FlatFileItemReader<Loan> flatFileItemReader,
						   FileItemProcessor fileItemProcessor,
						   FlatFileItemWriter<ActiveLoan> flatFileItemWriter,
						   JobRepository jobRepository,
						   PlatformTransactionManager transactionManager) {
        this.flatFileItemReader = flatFileItemReader;
		this.fileItemProcessor = fileItemProcessor;
		this.flatFileItemWriter = flatFileItemWriter;
		this.jobRepository = jobRepository;
		this.transactionManager = transactionManager;
	}

    @Bean
	public Job job(Step step) {
        return new JobBuilder("simple job", jobRepository).preventRestart().start(step).build();
	}

	@Bean
	public Step step() {
        return new StepBuilder("active loan", jobRepository).<Loan, ActiveLoan> chunk(3, transactionManager)
                .reader(flatFileItemReader).processor(fileItemProcessor).writer(flatFileItemWriter).build();
	}

}
