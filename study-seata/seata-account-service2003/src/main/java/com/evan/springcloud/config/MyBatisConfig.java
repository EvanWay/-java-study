package com.evan.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.evan.springcloud.dao"})
public class MyBatisConfig {
}
