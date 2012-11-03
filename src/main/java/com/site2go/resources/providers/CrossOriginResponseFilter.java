package com.site2go.resources.providers;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.springframework.stereotype.Component;

@Component
public class CrossOriginResponseFilter implements ContainerResponseFilter {
    public CrossOriginResponseFilter() {
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
        return response;
    }
}
