package com.site2go.resources;

import com.site2go.dto.Site;
import com.site2go.services.SiteService;
import com.yammer.metrics.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/site/{domain}")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class SiteResource {
    private SiteService siteService;

    @GET
    @Timed
    public Site get(@PathParam("domain") String domain) {
        return this.siteService.getSiteByDomain(domain);
    }

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }
}
