package com.github.wakingrufus.microblog.api;

import org.jvnet.hk2.annotations.Contract;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Contract
@Path("test")
@Singleton
public interface TestResource {
    @GET
    @Produces("text/plain")
    @Path("{value}")
    String getValue(@PathParam("value") String value);

    @POST
    @Produces("application/json")
    TestObject post(TestObject testObject);
}

