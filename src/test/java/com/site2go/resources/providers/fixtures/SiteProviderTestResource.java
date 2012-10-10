package com.site2go.resources.providers.fixtures;

import com.site2go.dto.Site;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/{domain}")
@Produces(MediaType.APPLICATION_JSON)
public class SiteProviderTestResource {
    @GET
    public Site get(@Context Site site) {
        return site;
    }

    @Path("/twice")
    @GET
    public Site get(@Context Site site, @Context Site site2) {
        assert site == site2;
        return site;
    }
}
