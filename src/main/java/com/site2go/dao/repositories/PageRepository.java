package com.site2go.dao.repositories;

import com.site2go.dao.entities.PageEntity;

import java.util.List;

public interface PageRepository extends GenericRepository<PageEntity> {
    public List<PageEntity> listBySite(Integer siteId);
    public PageEntity findBySiteAndSlug(Integer siteId, String slug);
}
