package com.site2go.resources;

import com.site2go.dto.Layout;
import com.site2go.dto.Site;
import com.site2go.services.LayoutService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class LayoutResource {
    private LayoutService layoutService;

    @GET
    public Layout get(@Context Site site, @PathParam("slug") String slug) {
        Layout layout = this.layoutService.getBySlug(site, slug);
        if(layout == null) throw new NotFoundException();
        return layout;
    }

    @Autowired
    public void setLayoutService(LayoutService layoutService) {
        this.layoutService = layoutService;
    }
}
