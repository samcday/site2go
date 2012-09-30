package com.site2go.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public StringBuilder test() {
        return new StringBuilder();
    }


}
