package com.github.wakingrufus.microblog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.github.wakingrufus.microblog.api.TestObject;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
public class IntegrationTest {
    @Test
    public void integrationTest() throws Exception {
        // create a jersey client
        final String baseUrl = "http://localhost:9005";
        Main.main(new String[0]);
        ObjectMapper mapper = new ObjectMapperFactory().buildObjectMapper();
        Client client = JerseyClientBuilder.newBuilder().build();
        // register our ObjectMapper to the client
        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJaxbJsonProvider.setMapper(mapper);
        client.register(jacksonJaxbJsonProvider);

        log.info("Test text get");
        WebTarget target = client.target(baseUrl).path("microblog/test/testvalue");
        Response testResponse = target.request(MediaType.TEXT_PLAIN_TYPE).get();
        log.info(testResponse.toString());
        Assert.assertEquals(200, testResponse.getStatus());
        Assert.assertEquals("testvalue", testResponse.readEntity(String.class));

        log.info("test post object");
        TestObject toPost = TestObject.builder().testProp("testvalue").build();
        WebTarget postTarget = client.target(baseUrl).path("microblog/test");
        TestObject responseObject = postTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(toPost, MediaType.APPLICATION_JSON_TYPE), TestObject.class);
        log.info(responseObject.toString());
        Assert.assertEquals("testvalue", responseObject.getTestProp());
    }

}