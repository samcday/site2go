package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.util.DevDataBootstrap;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Set;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SiteRepositoryIT extends RepositoryITBase {
    @Autowired private SiteRepository siteRepository;
    @Autowired private DevDataBootstrap devDataBootstrap;

    @Test
    public void testGetById() {
        SiteEntity siteEntity = this.siteRepository.getById(1);

        assertNotNull(siteEntity);
        assertEquals(siteEntity.getName(), "Test Site");
    }

    @Test
    public void testDelete() {
        SiteEntity siteEntity = this.siteRepository.getById(1);
        this.siteRepository.delete(siteEntity);

        assertNull(this.siteRepository.getById(1));
    }

    @Test
    public void testFindByDomain() {
        SiteEntity siteEntity = this.siteRepository.findByDomain("test.com");
        assertNotNull(siteEntity);
        assertEquals(siteEntity.getName(), "Test Site");
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testFindByNonexistentDomain() {
        this.siteRepository.findByDomain("test.comzzzzz");
    }

    @Test
    public void testUsersCollection() {
        SiteEntity siteEntity = this.siteRepository.getById(1);
        Set<UserEntity> users = siteEntity.getUsers();
        assertThat(users.size(), is(1));
        assertThat(users, hasItem(this.devDataBootstrap.user1));
    }
}
