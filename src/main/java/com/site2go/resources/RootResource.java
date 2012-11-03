package com.site2go.resources;

import com.site2go.dto.User;
import com.yammer.dropwizard.auth.Auth;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {
    @GET
    public Response get(@Auth User user) {
        return Response.ok("").build();
    }
}
