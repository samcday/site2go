package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SiteRepositoryJPAImpl implements SiteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SiteEntity getById(Integer id) {
        return this.entityManager.find(SiteEntity.class, id);
    }
}
