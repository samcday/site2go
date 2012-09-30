package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.spring.EmbeddedDBConfig;
import com.site2go.spring.PersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
    EmbeddedDBConfig.class,
    PersistenceConfig.class
})
public class SiteRepositoryIT {
    @Autowired private SiteRepository siteRepository;

    @Test
    public void testGetById() {
        SiteEntity siteEntity = this.siteRepository.getById(1);

        assertNotNull(siteEntity);

        System.out.println("It works!" + this.siteRepository);
    }
}
