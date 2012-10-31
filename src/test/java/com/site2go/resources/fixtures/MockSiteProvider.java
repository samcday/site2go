package com.site2go.resources.fixtures;

import com.site2go.dto.Site;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

import javax.ws.rs.core.Context;
import java.lang.reflect.Type;

public class MockSiteProvider implements InjectableProvider<Context, Type> {
    private Site site;

    public MockSiteProvider(Site site) {
        this.site = site;
    }

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext ic, Context context, Type type) {
        return new Injectable<Site>() {
            @Override
            public Site getValue() {
                return MockSiteProvider.this.site;
            }
        };
    }
}
