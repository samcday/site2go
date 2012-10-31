package com.site2go.resources;

import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.resources.fixtures.MockSiteProvider;
import com.site2go.services.PageService;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import static com.site2go.test.matchers.SiteMatchers.siteWithDomain;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageResourceTest extends ResourceTest {
    private PageService mockPageService;

    private Site site = new Site() {{
       this.setDomain("test.com");
    }};

    private Page page = new Page() {{
        this.setSlug("testpage");
    }};

    @Override
    protected void setUpResources() throws Exception {
        this.mockPageService = mock(PageService.class);
        SiteResource siteResource = new SiteResource();
        PageResource pageResource = new PageResource();
        pageResource.setPageService(this.mockPageService);
        siteResource.setPageResource(pageResource);
        this.addResource(siteResource);

        this.addProvider(new MockSiteProvider(this.site));
    }

    @Test
    public void testGet() {
        when(this.mockPageService.getPageBySlug(argThat(is(siteWithDomain("test.com"))), eq("testpage"))).thenReturn(this.page);
        ClientResponse response = this.client().resource("/site/test.com/page/testpage").get(ClientResponse.class);
        assertThat(response.getClientResponseStatus(), is(ClientResponse.Status.OK));
        assertThat(response.getEntity(String.class), equalTo(this.getJson().writeValueAsString(this.page)));
    }

    @Test
    public void testGetNonexistent() {
        ClientResponse response = this.client().resource("/site/test.com/page/notapage").get(ClientResponse.class);
        assertThat(response.getClientResponseStatus(), is(ClientResponse.Status.NOT_FOUND));
    }
}
