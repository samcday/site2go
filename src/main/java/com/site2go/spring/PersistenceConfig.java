package com.site2go.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@ComponentScan("com.site2go.dao")
@EnableTransactionManagement
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
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(this.entityManagerFactoryBean().getObject());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor jpaExceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
