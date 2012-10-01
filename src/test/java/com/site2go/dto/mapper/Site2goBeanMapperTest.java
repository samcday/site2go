package com.site2go.dto.mapper;

import com.site2go.dao.entities.LayoutEntity;
import com.site2go.dao.entities.SiteEntity;
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
            setId(123);
        }});

        Site dest = this.beanMapper.map(src, Site.class);
        assertEquals(dest.getName(), "Test Site");
        assertEquals(dest.getDomain(), "test.com");
        assertEquals(dest.getDefaultLayout(), new Integer(123));
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
}
