package com.site2go.dao.repositories.jpa;

import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.repositories.PageRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PageRepositoryImpl extends GenericRepositoryImpl<PageEntity> implements PageRepository {
    @Override
    public PageEntity findBySiteAndSlug(Integer siteId, String slug) {
        return this.entityManager.createNamedQuery("findPageBySiteAndSlug", PageEntity.class)
            .setParameter("siteId", siteId)
            .setParameter("slug", slug)
            .getSingleResult();
    }
}
