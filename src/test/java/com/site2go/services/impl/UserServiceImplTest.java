package com.site2go.services.impl;

import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.UserRepository;
import com.site2go.dto.User;
import com.site2go.dto.mapper.Site2goBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private UserRepository mockUserRepository;
    private UserServiceImpl userServiceImpl;

    @Before
    public void setUp() {
        this.mockUserRepository = mock(UserRepository.class);
        this.userServiceImpl = new UserServiceImpl();
        this.userServiceImpl.setBeanMapper(new Site2goBeanMapper());
        this.userServiceImpl.setUserRepository(this.mockUserRepository);
    }

    @Test
    public void testLogin() {
        when(this.mockUserRepository.findByEmail("test@user.com")).thenReturn(new UserEntity() {{
            this.setEmail("test@user.com");
            this.setPassword(BCrypt.hashpw("bacon", BCrypt.gensalt()));
        }});

        User user = this.userServiceImpl.login("test@user.com", "bacon");
        assertThat(user, is(notNullValue()));
        assertThat(user.getEmail(), is(equalTo("test@user.com")));
    }

    @Test
    public void testInvalidLoginWrongPassword() {
        when(this.mockUserRepository.findByEmail("test@user.com")).thenReturn(new UserEntity() {{
            this.setEmail("test@user.com");
            this.setPassword(BCrypt.hashpw("bacon", BCrypt.gensalt()));
        }});

        User user = this.userServiceImpl.login("test@user.com", "eggs");
        assertThat(user, is(nullValue()));
    }

    @Test
    public void testInvalidLoginWrongEmail() {
        when(this.mockUserRepository.findByEmail("test@user.com")).thenThrow(new EmptyResultDataAccessException(1));
        User user = this.userServiceImpl.login("test@user.com", "eggs");
        assertThat(user, is(nullValue()));
    }
}
