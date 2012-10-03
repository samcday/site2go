package com.site2go.spring;

import com.site2go.annotations.Dev;
import com.site2go.constants.Profiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Dev
@Configuration
public class EmbeddedDBConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:com/site2go/sql/tables.sql")
            .addScript("classpath:com/site2go/sql/sequences.sql")
            .build();
    }
}
