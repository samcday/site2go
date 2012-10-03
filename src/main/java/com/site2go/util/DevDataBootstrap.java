package com.site2go.util;

import com.site2go.annotations.Dev;
import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.LayoutRepository;
import com.site2go.dao.repositories.PageRepository;
import com.site2go.dao.repositories.SiteRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Dev
@Component
public class DevDataBootstrap implements ApplicationListener {
    @Autowired SiteRepository siteRepository;
    @Autowired PageRepository pageRepository;
    @Autowired LayoutRepository layoutRepository;

    // Maybe I just don't understand AOP and Spring transaction management, but I'm pretty sure this is a bug: have to
    // annotate this method, rather than the bootstrap call, otherwise a transaction isn't configured.
    @Transactional
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ContextRefreshedEvent) {
            this.bootstrap();
        }
    }

    public void bootstrap() {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setName("Test Site");
        siteEntity.setDomain("test.com");
        siteEntity.setCreatedDate(new DateTime("2012-01-01"));
        siteEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.siteRepository.save(siteEntity);

        PageEntity pageEntity = new PageEntity();
        pageEntity.setSite(siteEntity);
        pageEntity.setSlug("testpage");
        pageEntity.setTitle("Title");
        pageEntity.setMetaTitle("Meta Title");
        pageEntity.setMetaDescription("Meta Description");
        pageEntity.setMetaKeywords("Meta Keywords");
        pageEntity.setCreatedDate(new DateTime("2012-01-01"));
        pageEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.pageRepository.save(pageEntity);

        LayoutEntity layoutEntity = new LayoutEntity();
        layoutEntity.setSite(siteEntity);
        layoutEntity.setSlug("testlayout");
        layoutEntity.setName("Test Layout");
        layoutEntity.setTemplate("test template");
        layoutEntity.setCreatedDate(new DateTime("2012-01-01"));
        layoutEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.layoutRepository.save(layoutEntity);
    }
}
