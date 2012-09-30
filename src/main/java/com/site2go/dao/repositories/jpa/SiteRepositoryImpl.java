package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;

@Repository
public class SiteRepositoryImpl extends GenericRepositoryImpl<SiteEntity> implements SiteRepository {
    @Override
    public SiteEntity findByDomain(String domain) {
        return this.entityManager.createNamedQuery("findSiteByDomain", SiteEntity.class)
            .setParameter("domain", domain)
            .getSingleResult();
    }
}
