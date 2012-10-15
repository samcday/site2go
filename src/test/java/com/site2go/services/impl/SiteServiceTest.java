package com.site2go.services.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.entities.UserEntity;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dao.repositories.UserRepository;
import com.site2go.dto.Site;
import com.site2go.dto.User;
import com.site2go.dto.mapper.Site2goBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SiteServiceTest {
    private Mapper beanMapper;
    private SiteServiceImpl siteService;
    private SiteRepository mockSiteRepository;
    private UserRepository mockUserRepository;

    @Before
    public void setup() {
        this.beanMapper = new Site2goBeanMapper();
        this.siteService = new SiteServiceImpl();
        this.siteService.setMapper(this.beanMapper);
        this.mockSiteRepository = mock(SiteRepository.class);
        this.mockUserRepository = mock(UserRepository.class);
        this.siteService.setSiteRepository(this.mockSiteRepository);
        this.siteService.setUserRepository(this.mockUserRepository);
    }

    @Test
    public void testFindByDomain() {
        SiteEntity entity = new SiteEntity();
        entity.setDomain("test.com");
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(entity);

        Site site = this.siteService.getSiteByDomain("test.com");
        assertNotNull(site);
        assertEquals("test.com", site.getDomain());
    }

    @Test
    public void testFindByNonexistentDomainReturnsNull() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenThrow(new EmptyResultDataAccessException(1));
        Site site = this.siteService.getSiteByDomain("test.com");
        assertNull(site);
    }

    @Test
    public void testFindByUser() {
        when(this.mockUserRepository.findByEmail("test@user.com")).thenReturn(new UserEntity() {{
            SiteEntity siteEntity = new SiteEntity() {{
                this.setDomain("test.com");
            }};
            this.setSites(Sets.newHashSet(siteEntity));
        }});

        List<Site> sites = this.siteService.getSitesByUser(new User() {{
            this.setEmail("test@user.com");
        }});
        assertThat(sites, is(notNullValue()));
        assertThat(sites.size(), is(1));
        assertThat(sites.get(0).getDomain(), is("test.com"));
    }

    @Test
    public void testFindBySuperUser() {
        SiteEntity site = new SiteEntity() {{
            this.setDomain("test.com");
        }};
        when(this.mockSiteRepository.list()).thenReturn(Lists.newArrayList(site));
        List<Site> sites = this.siteService.getSitesByUser(new User() {{
            this.setSuperAdmin(true);
        }});
        assertThat(sites, is(notNullValue()));
        assertThat(sites.size(), is(1));
        assertThat(sites.get(0).getDomain(), is("test.com"));
    }
}
