package com.site2go.dao.repositories;

import com.site2go.dao.entities.PageEntity;

public interface PageRepository extends GenericRepository<PageEntity> {
    public PageEntity findBySiteAndSlug(Integer siteId, String slug);
}
