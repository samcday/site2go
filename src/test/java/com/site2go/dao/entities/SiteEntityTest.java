package com.site2go.dao.entities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SiteEntityTest {
    @Test
    public void testEquality() {
        SiteEntity siteEntity = new SiteEntity() {{
            this.setDomain("foo.com");
            this.setName("Foo Site");
        }};

        assertFalse(siteEntity.equals(null));
        assertTrue(siteEntity.equals(siteEntity));
        assertTrue(siteEntity.equals(new SiteEntity() {{
            this.setDomain("foo.com");
        }}));

        assertFalse(siteEntity.equals(new SiteEntity() {{
            this.setDomain("test.com");
        }}));
    }
}
