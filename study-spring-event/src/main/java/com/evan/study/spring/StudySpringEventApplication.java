package com.evan.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class StudySpringEventApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudySpringEventApplication.class, args);
    }

}
