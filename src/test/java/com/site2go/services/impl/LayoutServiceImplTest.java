package com.site2go.services.impl;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dao.repositories.LayoutRepository;
import com.site2go.dao.repositories.SiteRepository;
import com.site2go.dto.Layout;
import com.site2go.dto.Site;
import com.site2go.dto.mapper.Site2goBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import static com.site2go.test.matchers.LayoutMatchers.layoutWithSlug;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LayoutServiceImplTest {
    private LayoutServiceImpl layoutServiceImpl;
    private LayoutRepository mockLayoutRepository;
    private SiteRepository mockSiteRepository;

    @Before
    public void setup() {
        this.mockLayoutRepository = mock(LayoutRepository.class);
        this.mockSiteRepository = mock(SiteRepository.class);
        this.layoutServiceImpl = new LayoutServiceImpl();
        this.layoutServiceImpl.setBeanMapper(new Site2goBeanMapper());
        this.layoutServiceImpl.setLayoutRepository(this.mockLayoutRepository);
        this.layoutServiceImpl.setSiteRepository(this.mockSiteRepository);
    }

    @Test
    public void testGetLayoutBySlug() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(new SiteEntity() {{ this.setId(1); }});
        when(this.mockLayoutRepository.findBySiteAndSlug(1, "testlayout")).thenReturn(new LayoutEntity() {{
            this.setSlug("testlayout");
        }});

        Layout layout = this.layoutServiceImpl.getBySlug(new Site() {{ this.setDomain("test.com"); }}, "testlayout");
        assertThat(layout, is(notNullValue()));
        assertThat(layout, is(layoutWithSlug("testlayout")));
    }

    @Test
    public void testGetLayoutByNonexistentSlug() {
        when(this.mockSiteRepository.findByDomain("test.com")).thenReturn(new SiteEntity() {{ this.setId(1); }});
        when(this.mockLayoutRepository.findBySiteAndSlug(anyInt(), anyString())).thenThrow(EmptyResultDataAccessException.class);
        Layout layout = this.layoutServiceImpl.getBySlug(new Site() {{ this.setDomain("test.com"); }}, "noexists");
        assertThat(layout, is(nullValue()));
    }
}
