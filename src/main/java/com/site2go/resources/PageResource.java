package com.site2go.resources;

import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public class PageResource {
    private PageService pageService;

    private Site site;

    public PageResource() {
        System.out.println("I AM REBORN");
    }

    @GET
    public Page get(@PathParam("slug") String slug) {
        Page page = this.pageService.getPageBySlug(this.site, slug);
        return page;
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
