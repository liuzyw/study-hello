package com.study.cloud.feign.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public interface RsClient {

    @GET
    @Path("/hello")
    String hello();
}
