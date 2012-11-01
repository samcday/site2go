package com.site2go.resources.fixtures;

import com.site2go.dto.User;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Auth;

import java.lang.reflect.Type;

public class MockBasicAuthProvider implements InjectableProvider<Auth, Type> {
    private User user;

    public MockBasicAuthProvider(User user) {
        this.user = user;
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext ic, Auth auth, Type type) {
        return new Injectable<User>() {
            @Override
            public User getValue() {
                return MockBasicAuthProvider.this.user;
            }
        };
    }
}