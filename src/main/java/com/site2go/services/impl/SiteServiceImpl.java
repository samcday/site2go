package com.site2go.services.impl;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dto.Site;
import com.site2go.services.SiteService;
import org.springframework.beans.factory.annotation.Autowired;

public class SiteServiceImpl implements SiteService {
    private SiteRepository siteRepository;

    @Override
    public Site getSiteByDomain(String domain) {
        SiteEntity siteEntity = this.siteRepository.getById(1);

        return null;
    }

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
}
