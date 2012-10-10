package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.UserRepository;
import com.site2go.util.DevDataBootstrap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UserRepositoryIT extends RepositoryITBase {
    @Autowired private UserRepository userRepository;
    @Autowired private DevDataBootstrap devDataBootstrap;

    @Test
    public void testFindByEmail() {
        UserEntity userEntity = this.userRepository.findByEmail("super@user.com");
        assertThat(userEntity, is(notNullValue()));
        assertThat(userEntity, is(equalTo(this.devDataBootstrap.superuser)));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByNonexistentEmail() {
        this.userRepository.findByEmail("foo@nope.com");
    }
}
