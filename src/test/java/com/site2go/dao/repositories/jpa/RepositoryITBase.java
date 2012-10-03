package com.site2go.dao.repositories.jpa;

import com.site2go.spring.EmbeddedDBConfig;
import com.site2go.spring.PersistenceConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("dev")
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
    EmbeddedDBConfig.class,
    PersistenceConfig.class
})
@Transactional
public abstract class RepositoryITBase {
}
