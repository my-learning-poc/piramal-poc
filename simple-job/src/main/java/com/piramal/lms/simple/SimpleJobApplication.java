package com.piramal.lms.simple;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@SpringBootApplication
@EnableTask
public class SimpleJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleJobApplication.class, args);
    }

}
