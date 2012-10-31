package com.site2go.resources;

import com.site2go.dto.Site;
import com.site2go.resources.fixtures.MockSiteProvider;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SiteResourceTest extends ResourceTest {
    private SiteResource siteResource;

    private Site site = new Site() {{
        this.setDomain("test.com");
        this.setName("Test");
    }};

    @Override
    protected void setUpResources() throws Exception {
        this.addProvider(new MockSiteProvider(this.site));
        this.addResource(this.siteResource = new SiteResource());
    }

    @Test
    public void testGet() {
        ClientResponse resp = this.client().resource("/site/1").get(ClientResponse.class);
        assertThat(resp.getClientResponseStatus(), is(ClientResponse.Status.OK));
        assertThat(resp.getEntity(Site.class).getDomain(), is(equalTo("test.com")));
    }

}
