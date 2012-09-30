package com.site2go.resources;

import com.yammer.metrics.annotation.Timed;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
@Component
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {
    @GET
    @Timed
    public Response gogo() {
        return Response.ok()
            .entity("Ohai")
            .build();
    }
}
