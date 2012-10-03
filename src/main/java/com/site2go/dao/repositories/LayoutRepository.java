package com.site2go.dao.repositories;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.SiteEntity;

import java.util.Set;

public interface LayoutRepository extends GenericRepository<LayoutEntity> {
    Set<LayoutEntity> listBySite(Integer siteId);
    LayoutEntity findBySiteAndSlug(Integer siteId, String slug);
}
