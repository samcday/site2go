package com.site2go.resources;

import com.site2go.dto.Site;
import com.site2go.services.SiteService;
import com.sun.jersey.api.NotFoundException;
import com.sun.jersey.api.Responses;
import com.yammer.metrics.annotation.Timed;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/site/{domain}")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class SiteResource {
    private PageResource pageResource;

    @GET
    @Timed
    public Response get(@Context Site site) {
        return Response.ok(site).build();
    }

    @Path("/page/{slug}")
    public PageResource pageResource() {
        return this.pageResource;
    }

    @Autowired
    public void setPageResource(PageResource pageResource) {
        this.pageResource = pageResource;
    }
}
