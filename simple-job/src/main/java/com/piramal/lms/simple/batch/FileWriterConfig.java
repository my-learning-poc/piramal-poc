package com.piramal.lms.simple.batch;

import com.piramal.lms.simple.model.ActiveLoan;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

@Configuration
public class FileWriterConfig {

    @Value("file.output")
    private String outputCsv;

    @StepScope
    @Bean
    public FlatFileItemWriter<ActiveLoan> flatFileItemWriter() {
        FlatFileItemWriter<ActiveLoan> loanFlatFileItemWriter = new FlatFileItemWriter<ActiveLoan>();
        FileSystemResource outputResource = new FileSystemResource("outputCsv");
        loanFlatFileItemWriter.setAppendAllowed(true);
        loanFlatFileItemWriter.setResource(outputResource);
        DelimitedLineAggregator<ActiveLoan> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");
        BeanWrapperFieldExtractor<ActiveLoan> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<>();
        beanWrapperFieldExtractor.setNames(new String[] { "loanNumber", "productId", "sanctionAmount", "dueAmount" });
        lineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        loanFlatFileItemWriter.setLineAggregator(lineAggregator);
        return loanFlatFileItemWriter;
    }
}
