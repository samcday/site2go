package com.site2go.resources;

import com.google.common.collect.Lists;
import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.resources.fixtures.MockSiteProvider;
import com.site2go.services.PageService;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.junit.Test;

import java.util.List;

import static com.site2go.test.matchers.ClientResponseMatchers.isOk;
import static com.site2go.test.matchers.PageMatchers.pageWithSlug;
import static com.site2go.test.matchers.SiteMatchers.siteWithDomain;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagesResourceTest extends ResourceTest {
    private PageService mockPageService;
    private Site site = new Site() {{
        this.setDomain("test.com");
    }};

    @Override
    protected void setUpResources() throws Exception {
        this.mockPageService = mock(PageService.class);
        SiteResource siteResource = new SiteResource();
        PagesResource pagesResource = new PagesResource();
        pagesResource.setPageService(this.mockPageService);
        siteResource.setPagesResource(pagesResource);

        this.addProvider(new MockSiteProvider(this.site));
        this.addResource(siteResource);
    }

    @Test
    public void testGet() {
        when(this.mockPageService.getPagesBySite(argThat(siteWithDomain("test.com")))).thenReturn(Lists.<Page>newArrayList(
            new Page() {{
                this.setSlug("testone");
            }},
            new Page() {{
                this.setSlug("testtwo");
            }}
        ));
        ClientResponse response = this.client().resource("/site/test.com/pages").get(ClientResponse.class);
        assertThat(response, isOk());
        List<Page> pages = response.getEntity(new GenericType<List<Page>>() {});
        assertThat(pages, hasItem(pageWithSlug("testone")));
        assertThat(pages, hasItem(pageWithSlug("testtwo")));
    }
}
