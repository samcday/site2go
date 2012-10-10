package com.site2go.spring;

import com.site2go.authentication.UserAuthenticator;
import com.site2go.dto.User;
import com.site2go.services.UserService;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Autowired private UserService userService;

    @Bean
    public UserAuthenticator userAuthenticator() {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        userAuthenticator.setUserService(this.userService);
        return userAuthenticator;
    }

    @Bean
    public BasicAuthProvider<User> basicAuthProvider() {
        return new BasicAuthProvider(this.userAuthenticator(), "Site2go");
    }
}
