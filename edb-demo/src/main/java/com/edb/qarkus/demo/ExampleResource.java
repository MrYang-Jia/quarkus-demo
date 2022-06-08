package com.edb.qarkus.demo;

import com.edb.plugin.dubbo.service.DubboDemoService;
import com.edb.qarkus.demo.service.DemoService;
import com.edb.qarkus.demo.service.DemoTwoService;
import org.apache.dubbo.config.annotation.DubboReference;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {


    @Inject
    DemoService demoService;

    @Inject
    DemoTwoService demoTwoService;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        demoTwoService.check();
        return demoService.check();
    }
}