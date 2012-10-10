package com.site2go.util;

import com.site2go.annotations.Dev;
import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.LayoutRepository;
import com.site2go.dao.repositories.PageRepository;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dao.repositories.UserRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;

@Dev
@Component
public class DevDataBootstrap {
    @Autowired private SiteRepository siteRepository;
    @Autowired private PageRepository pageRepository;
    @Autowired private LayoutRepository layoutRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PlatformTransactionManager transactionManager;

    public SiteEntity site1;
    public PageEntity site1_page1;
    public UserEntity superuser;

    @PostConstruct
    public void bootstrap() {
        new TransactionTemplate(this.transactionManager).execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus) {
                DevDataBootstrap.this.generateSuperUser();
                DevDataBootstrap.this.generateTestSite1();
                DevDataBootstrap.this.generateTestSite2();
                return null;
            }
        });
    }

    private void generateSuperUser() {
        UserEntity userEntity = this.superuser = new UserEntity();
        userEntity.setEmail("super@user.com");
        userEntity.setPassword("foo");
        userEntity.setCreatedDate(new DateTime("2012-01-01"));
        userEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.userRepository.save(userEntity);
    }

    private void generateTestSite1() {
        SiteEntity siteEntity = this.site1 = new SiteEntity();
        siteEntity.setName("Test Site");
        siteEntity.setDomain("test.com");
        siteEntity.setCreatedDate(new DateTime("2012-01-01"));
        siteEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.siteRepository.save(siteEntity);

        PageEntity pageEntity = this.site1_page1 = new PageEntity();
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

    private void generateTestSite2() {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setName("Another Site");
        siteEntity.setDomain("another.com");
        siteEntity.setCreatedDate(new DateTime("2012-01-01"));
        siteEntity.setModifiedDate(new DateTime("2012-02-01"));
        this.siteRepository.save(siteEntity);
    }
}
