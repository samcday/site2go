package com.site2go.authentication;

import com.google.common.base.Optional;
import com.site2go.dto.User;
import com.site2go.services.UserService;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserAuthenticatorTest {
    private User principal;
    private UserService mockUserService;
    private UserAuthenticator userAuthenticator;

    @Before
    public void setUp() {
        this.principal = new User() {{
            this.setEmail("test@user.com");
        }};
        this.mockUserService = mock(UserService.class);
        this.userAuthenticator = new UserAuthenticator();
        this.userAuthenticator.setUserService(this.mockUserService);
    }

    @Test
    public void testValidAuthentication() throws Exception {
        when(this.mockUserService.login("test@user.com", "bacon")).thenReturn(this.principal);

        Optional<User> result = this.userAuthenticator.authenticate(new BasicCredentials("test@user.com", "bacon"));
        assertTrue(result.isPresent());
        assertThat(result.get(), is(equalTo(this.principal)));
    }

    @Test
    public void testInvalidAuthentication() throws Exception {
        Optional<User> result = this.userAuthenticator.authenticate(new BasicCredentials("test@user.com", "bacon"));
        assertFalse(result.isPresent());
    }

    @Test(expected = AuthenticationException.class)
    public void testErroredAuthentication() throws Exception {
        when(this.mockUserService.login("test@user.com", "bacon")).thenThrow(new ArithmeticException());
        this.userAuthenticator.authenticate(new BasicCredentials("test@user.com", "bacon"));
    }
}
