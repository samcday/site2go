package com.site2go.spring;

import com.site2go.dto.mapper.Site2goBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public Mapper dtoMapper() {
        Site2goBeanMapper mapper = new Site2goBeanMapper();
        return mapper;
    }
}
