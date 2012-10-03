package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import static org.junit.Assert.*;

public class SiteRepositoryIT extends RepositoryITBase {
    @Autowired private SiteRepository siteRepository;

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
    public void findByNonexistentDomain() {
        this.siteRepository.findByDomain("test.comzzzzz");
    }
}
