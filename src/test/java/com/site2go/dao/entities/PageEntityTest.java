package com.site2go.dao.entities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PageEntityTest {
    @Test
    public void testEquality() {
        final SiteEntity siteEntity = new SiteEntity() {{
            this.setDomain("test.com");
        }};
        PageEntity pageEntity = new PageEntity() {{
            this.setSlug("testpage");
            this.setSite(siteEntity);
        }};

        assertFalse(pageEntity.equals(null));
        assertTrue(pageEntity.equals(pageEntity));
        assertTrue(pageEntity.equals(new PageEntity() {{
            this.setSlug("testpage");
            this.setSite(siteEntity);
        }}));
        assertTrue(pageEntity.equals(new PageEntity() {{
            this.setSlug("testpage");
            this.setSite(new SiteEntity() {{
                this.setDomain("test.com");
            }});
        }}));
        assertFalse(pageEntity.equals(new PageEntity() {{
            this.setSlug("differentpage");
            this.setSite(siteEntity);
        }}));
        assertFalse(pageEntity.equals(new PageEntity() {{
            this.setSlug("testpage");
            this.setSite(null);
        }}));
    }
}
