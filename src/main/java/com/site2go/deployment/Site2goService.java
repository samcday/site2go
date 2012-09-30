package com.site2go.deployment;

import com.github.nhuray.dropwizard.spring.SpringService;
import com.github.nhuray.dropwizard.spring.config.ConfigurationPlaceholderConfigurer;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Environment;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//public class Site2goService extends Service<Site2goConfiguration> {
public class Site2goService extends SpringService<Site2goConfiguration> {
    public static void main(String args[]) throws Exception {
        new Site2goService().run(args);
    }

    private Site2goService() {
        super("site2go");
    }

    /*@Override
    protected void initialize(Site2goConfiguration config, Environment environment) throws Exception {

    }*/

    @Override
    protected ConfigurableApplicationContext initializeApplicationContext(Site2goConfiguration config, Environment environment) throws BeansException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.getEnvironment().setActiveProfiles("dev");
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
