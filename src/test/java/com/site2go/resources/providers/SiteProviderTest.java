package com.site2go.resources.providers;

import com.site2go.dto.Site;
import com.site2go.resources.ResourceTest;
import com.site2go.resources.providers.fixtures.SiteProviderTestResource;
import com.site2go.services.SiteService;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class SiteProviderTest extends ResourceTest {
    private Site site = new Site() {{
        this.setDomain("test.com");
        this.setName("Test");
    }};
    private SiteService siteService = mock(SiteService.class);

    @Override
    protected void setUpResources() throws Exception {
        when(this.siteService.getSiteByDomain("test.com")).thenReturn(site);
        SiteProvider siteProvider = new SiteProvider();
        siteProvider.setSiteService(this.siteService);
        this.addProvider(siteProvider);
        this.addResource(new SiteProviderTestResource());
    }

    @Test
    public void testValidSite() {
        assertThat(client().resource("/test.com").get(Site.class).getDomain(), equalTo("test.com"));
        verify(this.siteService).getSiteByDomain("test.com");
    }

    @Test
    public void testInvalidSite() {
        assertThat(client().resource("/idont.exist").get(ClientResponse.class).getClientResponseStatus(), equalTo(ClientResponse.Status.NOT_FOUND));
        verify(this.siteService).getSiteByDomain("idont.exist");
    }

    @Test
    public void testValidSiteOnlyLoadedOnce() {
        assertThat(client().resource("/test.com/twice").get(Site.class).getDomain(), equalTo("test.com"));
        verify(this.siteService).getSiteByDomain("test.com");
    }
}
