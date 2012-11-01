package com.site2go.services.impl;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.LayoutRepository;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dto.Layout;
import com.site2go.dto.Site;
import com.site2go.services.LayoutService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

public class LayoutServiceImpl implements LayoutService {
    private SiteRepository siteRepository;
    private LayoutRepository layoutRepository;
    private Mapper beanMapper;

    @Override
    public Layout getBySlug(Site site, String slug) {
        try {
            SiteEntity siteEntity = this.siteRepository.findByDomain(site.getDomain());
            LayoutEntity layoutEntity = this.layoutRepository.findBySiteAndSlug(siteEntity.getId(), slug);
            return this.beanMapper.map(layoutEntity, Layout.class);
        }
        catch(EmptyResultDataAccessException erdae) {
            return null;
        }
    }

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Autowired
    public void setLayoutRepository(LayoutRepository layoutRepository) {
        this.layoutRepository = layoutRepository;
    }

    @Autowired
    public void setBeanMapper(Mapper beanMapper) {
        this.beanMapper = beanMapper;
    }
}
