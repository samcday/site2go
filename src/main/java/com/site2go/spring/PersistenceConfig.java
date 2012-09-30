package com.site2go.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@ComponentScan("com.site2go.dao")
public class PersistenceConfig {
    @Autowired private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(this.dataSource);
        factoryBean.setPackagesToScan("com.site2go.dao.entities");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {
            {

                // Hibernate specific properties here if needed.
            }
        };
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setJpaPropertyMap(new HashMap<String, Object>() {{
            put("hibernate.ejb.naming_strategy", "com.site2go.dao.util.hibernate.Site2goNamingStrategy");
        }});
        return factoryBean;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor jpaExceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
