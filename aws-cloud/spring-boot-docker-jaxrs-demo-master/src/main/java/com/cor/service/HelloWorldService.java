package com.cor.service;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Component
@Path("/demo")
public class HelloWorldService {

    @GET
    @Path("/hello")
    public Response test() {
        return Response.status(200).entity("Hello World").build();
    }

}
