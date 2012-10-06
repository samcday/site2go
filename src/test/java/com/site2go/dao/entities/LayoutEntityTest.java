package com.site2go.dao.entities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LayoutEntityTest {
    @Test
    public void testEquality() {
        final SiteEntity siteEntity = new SiteEntity() {{
            this.setDomain("test.com");
        }};
        LayoutEntity layoutEntity = new LayoutEntity() {{
            this.setSlug("testlayout");
            this.setSite(siteEntity);
        }};

        assertFalse(layoutEntity.equals(null));
        assertTrue(layoutEntity.equals(new LayoutEntity() {{
            this.setSlug("testlayout");
            this.setSite(siteEntity);
        }}));
        assertTrue(layoutEntity.equals(new LayoutEntity() {{
            this.setSlug("testlayout");
            this.setSite(new SiteEntity() {{
                this.setDomain("test.com");
            }});
        }}));
        assertFalse(layoutEntity.equals(new LayoutEntity() {{
            this.setSlug("differentlayout");
            this.setSite(siteEntity);
        }}));
        assertFalse(layoutEntity.equals(new LayoutEntity() {{
            this.setSlug("testlayout");
            this.setSite(null);
        }}));
    }
}
