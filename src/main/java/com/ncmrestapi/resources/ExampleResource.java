package com.ncmrestapi.resources;

import com.ncmrestapi.annotations.Secured;
import com.ncmrestapi.models.Role;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/example")
@Secured({Role.USER})
public class ExampleResource {

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response myUnsecuredMethod() {
        // This method is not annotated with @Secured
        // The authentication filter won't be executed before invoking this method
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured({Role.ADMIN})
    public Response mySecuredMethod(@PathParam("id") Long id) {
        // This method is not annotated with @Secured
        // The authentication filter won't be executed before invoking this method

        //User user = (User) securityContext.getUserPrincipal();

        //System.out.println(user.getName());
        //System.out.println(user.getRole().name());

        return Response.ok().build();
    }
}