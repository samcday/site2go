package com.site2go.resources.providers;

import com.site2go.dto.Site;
import com.site2go.resources.SiteResource;
import com.site2go.resources.providers.fixtures.SiteProviderTestResource;
import com.site2go.services.SiteService;
import com.sun.jersey.api.client.ClientResponse;
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        // This is somewhat unfortunate, this.addProvider only accepts a Class, which isn't terribly useful if we need
        // to inject collaborators onto it. By calling this.addResource with a Provider below, we're exploiting the fact
        // that ResourceTest doesn't differentiate, it just has a List of Objects it registers with Jersey.
        this.addResource(siteProvider);

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
}
