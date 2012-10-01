package com.site2go.spring;

import com.site2go.resources.SiteResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.site2go.resources", "com.site2go.services"})
public class ApplicationConfig {

}
