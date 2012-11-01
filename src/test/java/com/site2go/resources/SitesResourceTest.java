package com.site2go.resources;

import com.google.common.collect.Lists;
import com.site2go.dto.Site;
import com.site2go.dto.User;
import com.site2go.resources.fixtures.MockBasicAuthProvider;
import com.site2go.services.SiteService;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SitesResourceTest extends ResourceTest {
    private SiteService mockSiteService;
    private User user = new User() {{
       this.setEmail("test@test.com");
    }};

    public SitesResourceTest() {
        this.mockSiteService = mock(SiteService.class);
    }

    @Override
    protected void setUpResources() throws Exception {
        SitesResource sitesResource = new SitesResource();
        sitesResource.setSiteService(this.mockSiteService);
        this.addResource(sitesResource);
        this.addProvider(new MockBasicAuthProvider(this.user));
    }

    @Test
    public void testListSites() {
        Site site = new Site() {{
            this.setDomain("test.com");
        }};
        List<Site> sites = Lists.newArrayList(site);
        when(this.mockSiteService.getSitesByUser(this.user)).thenReturn(sites);
        assertThat(this.client().resource("/sites").get(String.class), is(this.getJson().writeValueAsString(sites)));
    }
}
