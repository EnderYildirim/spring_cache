package com.example.spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.spring.service.CustomerDataService;

@Configuration
public class ServiceConfig {

    @Bean
    public CustomerDataService customerDataService(JdbcTemplate jdbcTemplate) {
        return new CustomerDataService(jdbcTemplate);
    }
    
}
