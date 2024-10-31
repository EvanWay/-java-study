package com.evan.study.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.evan.study.mybatis")
@SpringBootApplication
public class StudyMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisApplication.class, args);
    }

}
