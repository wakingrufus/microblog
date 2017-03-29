package com.github.wakingrufus.microblog;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class Main {
    public static void main(String[] args) {

        String BASE_URI = "http://0.0.0.0:9005/microblog/";
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();

        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), new JerseyApplication(), locator);

        try {
            httpServer.start();

            System.out.println(String.format("Jersey app started.\nHit enter to stop it...", BASE_URI));
            System.in.read();
        } catch (IOException e) {
            log.error("error starting server: "+e.getLocalizedMessage(), e);
        }
    }
}
