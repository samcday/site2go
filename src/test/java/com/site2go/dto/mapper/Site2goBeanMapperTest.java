package com.site2go.dto.mapper;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.PageEntity;
import com.site2go.dao.entities.SiteEntity;
import com.site2go.dto.Layout;
import com.site2go.dto.Page;
import com.site2go.dto.Site;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Site2goBeanMapperTest {
    private Site2goBeanMapper beanMapper;

    @Before
    public void setup() {
        this.beanMapper = new Site2goBeanMapper();
    }

    @Test
    public void testStandardSiteEntityMapping() {
        SiteEntity src = new SiteEntity();
        src.setName("Test Site");
        src.setModifiedDate(new DateTime("2012-02-01"));
        src.setCreatedDate(new DateTime("2012-01-01"));
        src.setDomain("test.com");
        src.setDefaultLayout(new LayoutEntity() {{
            this.setName("testlayout");
        }});

        Site dest = this.beanMapper.map(src, Site.class);
        assertEquals(dest.getName(), "Test Site");
        assertEquals(dest.getDomain(), "test.com");
        assertEquals(dest.getDefaultLayout(), "testlayout");
    }
    
    @Test
    public void testEmptySiteEntityMapping() {
    	SiteEntity src = new SiteEntity();
        Site dest = this.beanMapper.map(src, Site.class);
        assertNull(dest.getDefaultLayout());
        assertNull(dest.getCreatedDate());
        assertNull(dest.getModifiedDate());
        assertNull(dest.getDomain());
        assertNull(dest.getName());
    }

    @Test
    public void testStandardLayoutEntityMapping() {
        LayoutEntity src = new LayoutEntity();
        src.setName("testlayout");
        src.setSite(new SiteEntity() {{
            this.setDomain("test.com");
        }});

        Layout dest = this.beanMapper.map(src, Layout.class);
        assertEquals(dest.getName(), "testlayout");
        assertEquals(dest.getSite(), "test.com");
    }

    @Test
    public void testEmptyLayoutEntityMapping() {
        LayoutEntity src = new LayoutEntity();
        Layout dest = this.beanMapper.map(src, Layout.class);
        assertNull(dest.getName());
        assertNull(dest.getSite());
        assertNull(dest.getCreatedDate());
        assertNull(dest.getModifiedDate());
    }

    @Test
    public void testStandardPageEntityMapping() {
        PageEntity src = new PageEntity();
        src.setName("testpage");
        src.setLayout(new LayoutEntity() {{
            this.setName("testlayout");
        }});
        src.setMetaTitle("Test Title");
        src.setMetaDescription("Test Description");
        src.setMetaKeywords("Test Keywords");

        Page dest = this.beanMapper.map(src, Page.class);
        assertEquals(dest.getName(), "testpage");
        assertEquals(dest.getLayout(), "testlayout");
        assertEquals(dest.getMeta().get("title"), "Test Title");
        assertEquals(dest.getMeta().get("description"), "Test Description");
        assertEquals(dest.getMeta().get("keywords"), "Test Keywords");
    }

    @Test
    public void testDatesCopyByRef() {
        SiteEntity srcSite = new SiteEntity();
        srcSite.setModifiedDate(new DateTime("2012-02-01"));
        srcSite.setCreatedDate(new DateTime("2012-01-01"));
        Site destSite = this.beanMapper.map(srcSite, Site.class);
        assertSame(srcSite.getModifiedDate(), destSite.getModifiedDate());
        assertSame(srcSite.getCreatedDate(), destSite.getCreatedDate());

        LayoutEntity srcLayout = new LayoutEntity();
        srcLayout.setModifiedDate(new DateTime("2012-02-01"));
        srcLayout.setCreatedDate(new DateTime("2012-01-01"));
        Layout destLayout = this.beanMapper.map(srcLayout, Layout.class);
        assertSame(srcLayout.getModifiedDate(), destLayout.getModifiedDate());
        assertSame(srcLayout.getCreatedDate(), destLayout.getCreatedDate());

        PageEntity srcPage = new PageEntity();
        srcPage.setModifiedDate(new DateTime("2012-02-01"));
        srcPage.setCreatedDate(new DateTime("2012-01-01"));
        Page destPage = this.beanMapper.map(srcPage, Page.class);
        assertSame(srcPage.getModifiedDate(), destPage.getModifiedDate());
        assertSame(srcPage.getCreatedDate(), destPage.getCreatedDate());
    }
}
