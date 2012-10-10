package com.site2go.resources;

import com.site2go.dto.Site;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import org.junit.Test;

import javax.ws.rs.core.Context;
import java.lang.reflect.Type;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SiteResourceTest extends ResourceTest {
    private Site site = new Site() {{
        this.setDomain("test.com");
        this.setName("Test");
    }};

    @Override
    protected void setUpResources() throws Exception {
        this.addProvider(new InjectableProvider<Context, Type>() {
            @Override
            public ComponentScope getScope() {
                return ComponentScope.PerRequest;
            }

            @Override
            public Injectable getInjectable(ComponentContext ic, Context context, Type type) {
                return new Injectable<Site>() {
                    @Override
                    public Site getValue() {
                        return SiteResourceTest.this.site;
                    }
                };
            }
        });
        this.addResource(new SiteResource());
    }

    @Test
    public void testGet() {
        ClientResponse resp = this.client().resource("/site/1").get(ClientResponse.class);
        assertThat(resp.getClientResponseStatus(), is(ClientResponse.Status.OK));
        assertThat(resp.getEntity(Site.class).getDomain(), is(equalTo("test.com")));
    }
}
