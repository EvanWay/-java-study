package org.evan.config;

import org.evan.service.TestProperties;
import org.evan.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Evan
 * @date 2021/12/27
 */
@Configuration
@EnableConfigurationProperties(TestProperties.class)
public class TestConfiguration {

    @Autowired
    TestProperties testProperties;

    /**
     * 写好的Service类，注册为Bean
     */
    @Bean
    public TestService testService() {
        TestService service = new TestService();
        service.setTestProperties(testProperties);
        return service;
    }
}
