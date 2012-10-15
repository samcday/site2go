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
import org.mindrot.jbcrypt.BCrypt;
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
    public UserEntity user1;

    @PostConstruct
    public void bootstrap() {
        new TransactionTemplate(this.transactionManager).execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus transactionStatus) {
                DevDataBootstrap.this.generateUsers();
                DevDataBootstrap.this.generateTestSite1();
                DevDataBootstrap.this.generateTestSite2();
                return null;
            }
        });
    }

    private void generateUsers() {
        UserEntity userEntity = this.superuser = new UserEntity();
        userEntity.setEmail("super@user.com");
        userEntity.setPassword(BCrypt.hashpw("supa", BCrypt.gensalt()));
        this.userRepository.save(userEntity);

        this.user1 = new UserEntity();
        this.user1.setEmail("user@one.com");
        this.user1.setPassword(BCrypt.hashpw("one", BCrypt.gensalt()));
        this.userRepository.save(this.user1);
    }

    private void generateTestSite1() {
        SiteEntity siteEntity = this.site1 = new SiteEntity();
        siteEntity.setName("Test Site");
        siteEntity.setDomain("test.com");
        siteEntity.getUsers().add(this.user1);
        this.user1.getSites().add(siteEntity);
        this.siteRepository.save(siteEntity);
        this.userRepository.save(this.user1);

        PageEntity pageEntity = this.site1_page1 = new PageEntity();
        pageEntity.setSite(siteEntity);
        pageEntity.setSlug("testpage");
        pageEntity.setTitle("Title");
        pageEntity.setMetaTitle("Meta Title");
        pageEntity.setMetaDescription("Meta Description");
        pageEntity.setMetaKeywords("Meta Keywords");
        this.pageRepository.save(pageEntity);

        LayoutEntity layoutEntity = new LayoutEntity();
        layoutEntity.setSite(siteEntity);
        layoutEntity.setSlug("testlayout");
        layoutEntity.setName("Test Layout");
        layoutEntity.setTemplate("test template");
        this.layoutRepository.save(layoutEntity);
    }

    private void generateTestSite2() {
        SiteEntity siteEntity = new SiteEntity();
        siteEntity.setName("Another Site");
        siteEntity.setDomain("another.com");
        this.siteRepository.save(siteEntity);
    }
}
