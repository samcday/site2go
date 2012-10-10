package com.site2go.resources;

import com.site2go.dto.Site;
import com.site2go.dto.User;
import com.site2go.services.SiteService;
import com.yammer.dropwizard.auth.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/sites")
@Produces(MediaType.APPLICATION_JSON)
public class SitesResource {
    private SiteService siteService;

    @GET
    public List<Site> get(@Auth User user) {
        return null;
    }

    @Autowired
    public void setSiteService(SiteService siteService) {
        this.siteService = siteService;
    }
}
