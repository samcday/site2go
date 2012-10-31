package com.site2go.deployment;

import com.github.nhuray.dropwizard.spring.SpringService;
import com.github.nhuray.dropwizard.spring.config.ConfigurationPlaceholderConfigurer;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.json.Json;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Site2goService extends SpringService<Site2goConfiguration> {
    public static void main(String args[]) throws Exception {
        new Site2goService().run(args);
    }

    private Site2goService() {
        super("site2go");
    }

    // This is where we customize the Jackson ObjectMapper instance DW creates and registers as a JerseyProvider.
    @Override
    public Json getJson() {
        Json json = super.getJson();
        json.disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
        return json;
    }

    @Override
    protected ConfigurableApplicationContext initializeApplicationContext(Site2goConfiguration config, Environment environment) throws BeansException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles(config.getProfile());
        ctx.scan("com.site2go");

        ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

        // Register ConfigurationPlaceholderConfigurer
        ConfigurationPlaceholderConfigurer placeholderConfigurer = new ConfigurationPlaceholderConfigurer(config);
        placeholderConfigurer.setIgnoreUnresolvablePlaceholders(false);
        placeholderConfigurer.setPlaceholderPrefix("${dw.");
        placeholderConfigurer.setPlaceholderSuffix("}");
        beanFactory.registerSingleton("placeholderConfigurer", placeholderConfigurer);

        // Register Configuration
        beanFactory.registerSingleton("dw", config);

        ctx.refresh();

        return ctx;
    }
}
