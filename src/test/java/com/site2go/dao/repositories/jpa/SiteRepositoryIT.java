package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.spring.EmbeddedDBConfig;
import com.site2go.spring.PersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
    EmbeddedDBConfig.class,
    PersistenceConfig.class
})
@Transactional
public class SiteRepositoryIT {
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
