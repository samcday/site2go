package com.site2go.services.impl;

import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dto.Site;
import com.site2go.services.SiteService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {
    private SiteRepository siteRepository;
    private Mapper mapper;

    @Override
    public Site getSiteByDomain(String domain) {
        SiteEntity siteEntity;
        try {
            siteEntity = this.siteRepository.findByDomain(domain);
        }
        catch(EmptyResultDataAccessException erdae) {
            return null;
        }

        return this.mapper.map(siteEntity, Site.class);
    }

    @Autowired
    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
