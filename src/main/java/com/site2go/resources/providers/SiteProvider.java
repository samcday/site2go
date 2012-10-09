package com.site2go.resources.providers;

import com.site2go.annotations.ServerProviderOnly;
import com.site2go.dto.Site;
import com.site2go.services.SiteService;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Providers;
import java.lang.reflect.Type;
import java.util.Map;

@Component
@ServerProviderOnly
public class SiteProvider extends AbstractHttpContextInjectable<Site> implements InjectableProvider<Context, Type> {
    private static final String SITE_PROPERTY_KEY = "site2go_site";

    private SiteService siteService;

    @Override
    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    @Override
    public Injectable getInjectable(ComponentContext componentContext, Context context, Type type) {
        if(type.equals(Site.class)) {
            return this;
        }
        return null;
    }

    @Override
    public Site getValue(HttpContext httpContext) {
        Map<String, Object> props = httpContext.getProperties();
        MultivaluedMap<String, String> pathParams = httpContext.getUriInfo().getPathParameters();
        Site site = (Site)props.get(SITE_PROPERTY_KEY);
        if(site != null) {
            return site;
        }

        site = this.siteService.getSiteByDomain(pathParams.getFirst("domain"));
        if(site == null) {
            throw new NotFoundException();
        }

        props.put(SITE_PROPERTY_KEY, site);
        return site;
    }

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }

    @Context
    public void setAwesome(HttpContext app) {
        System.out.println("lol");
    }
}
