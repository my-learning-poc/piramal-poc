package com.piramal.lms.simple.batch;

import com.piramal.lms.simple.model.Loan;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class FileReaderConfig {

    @Value("file.input")
    private Resource inputCsv;

    @StepScope
    @Bean
    public FlatFileItemReader<Loan> flatFileItemReader() {
        FlatFileItemReader<Loan> flatFileItemReader = new FlatFileItemReader<Loan>();
        flatFileItemReader.setResource(inputCsv);
        DefaultLineMapper<Loan> defaultLineMapper =  new DefaultLineMapper<Loan>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setNames("Loan Number", "Product Id", "Active", "Sanction Amount", "Due Amount");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        BeanWrapperFieldSetMapper<Loan> fieldSetMapper =new BeanWrapperFieldSetMapper<Loan>();
        fieldSetMapper.setTargetType(Loan.class);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
        flatFileItemReader.setLineMapper(defaultLineMapper);
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }
}
