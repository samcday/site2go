package com.site2go.resources;

import com.site2go.dto.Layout;
import com.site2go.dto.Site;
import com.site2go.resources.fixtures.MockSiteProvider;
import com.site2go.services.LayoutService;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import static com.site2go.test.matchers.ClientResponseMatchers.isNotFound;
import static com.site2go.test.matchers.ClientResponseMatchers.isOk;
import static com.site2go.test.matchers.LayoutMatchers.layoutWithSlug;
import static com.site2go.test.matchers.SiteMatchers.siteWithDomain;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LayoutResourceTest extends ResourceTest {
    private LayoutService mockLayoutService;
    private Site site;

    @Override
    protected void setUpResources() throws Exception {
        this.mockLayoutService = mock(LayoutService.class);
        this.site = new Site();
        this.site.setDomain("test.com");

        LayoutResource layoutResource = new LayoutResource();
        layoutResource.setLayoutService(this.mockLayoutService);
        SiteResource siteResource = new SiteResource();
        siteResource.setLayoutResource(layoutResource);
        this.addProvider(new MockSiteProvider(this.site));
        this.addResource(siteResource);
    }

    @Test
    public void testGet() {
        when(this.mockLayoutService.getBySlug(argThat(is(siteWithDomain("test.com"))), eq("testlayout"))).thenReturn(new Layout() {{
            this.setSlug("testlayout");
        }});
        ClientResponse response = this.client().resource("/site/test.com/layout/testlayout").get(ClientResponse.class);
        assertThat(response, isOk());

        Layout layout = response.getEntity(Layout.class);
        assertThat(layout, is(layoutWithSlug("testlayout")));
    }

    @Test
    public void testGetNonexistent() {
        ClientResponse response = this.client().resource("/site/test.com/layout/zzz").get(ClientResponse.class);
        assertThat(response, isNotFound());
    }
}
