package com.ncmrestapi;

import com.ncmrestapi.resources.Authentication;
import com.ncmrestapi.resources.ExampleResource;
import com.ncmrestapi.resources.MyResource;
import com.ncmrestapi.resources.Roles;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/")
//The java class declares root resource and provider classes
public class NcmRestApiApplication extends Application{
    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add( MyResource.class );
        h.add( Authentication.class );
        h.add( ExampleResource.class );
        h.add( Roles.class );

        return h;
    }
}