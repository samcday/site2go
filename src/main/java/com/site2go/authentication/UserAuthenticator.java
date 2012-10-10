package com.site2go.authentication;

import com.google.common.base.Optional;
import com.site2go.dto.User;
import com.site2go.services.UserService;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthenticator implements Authenticator<BasicCredentials, User> {
    @Autowired private UserService userService;

    @Override
    public Optional<User> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
        throw new NotImplementedException();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
