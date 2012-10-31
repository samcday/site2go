package com.site2go.resources;

import com.site2go.dto.Page;
import com.site2go.dto.Site;
import com.site2go.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Produces(MediaType.APPLICATION_JSON)
public class PagesResource {
    private PageService pageService;

    @GET
    public List<Page> get(@Context Site site) {
        return this.pageService.getPagesBySite(site);
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }
}
