package com.site2go.resources;

import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.services.PageService;
import com.sun.jersey.api.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Component
@Produces(MediaType.APPLICATION_JSON)
public class PageResource {
    private PageService pageService;

    @GET
    public Page get(@Context Site site, @PathParam("slug") String slug) {
        Page page = this.pageService.getPageBySlug(site, slug);
        if(page == null) {
            throw new NotFoundException();
        }
        return page;
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }
}
