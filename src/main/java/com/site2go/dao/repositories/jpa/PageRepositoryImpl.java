package com.site2go.dao.repositories.jpa;

import com.google.common.collect.Lists;
import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.repositories.PageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageRepositoryImpl extends GenericRepositoryImpl<PageEntity> implements PageRepository {
    @Override
    public PageEntity findBySiteAndSlug(Integer siteId, String slug) {
        return this.entityManager.createNamedQuery("findPageBySiteAndSlug", PageEntity.class)
            .setParameter("siteId", siteId)
            .setParameter("slug", slug)
            .getSingleResult();
    }

    @Override
    public List<PageEntity> listBySite(Integer siteId) {
        return Lists.newArrayList(this.entityManager.createNamedQuery("listPagesBySite", PageEntity.class)
            .setParameter("siteId", siteId)
            .getResultList());
    }
}
