package com.site2go.dao.repositories.jpa;

import com.google.common.collect.Sets;
import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.repositories.LayoutRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class LayoutRepositoryImpl extends GenericRepositoryImpl<LayoutEntity> implements LayoutRepository {
    @Override
    public Set<LayoutEntity> listBySite(Integer siteId) {
        return Sets.newHashSet(this.entityManager.createNamedQuery("findLayoutBySite", LayoutEntity.class)
            .setParameter("id", siteId)
            .getResultList());
    }

    @Override
    public LayoutEntity findBySiteAndSlug(Integer siteId, String slug) {
        return this.entityManager.createNamedQuery("findLayoutBySiteAndSlug", LayoutEntity.class)
            .setParameter("id", siteId)
            .setParameter("slug", slug)
            .getSingleResult();
    }
}
