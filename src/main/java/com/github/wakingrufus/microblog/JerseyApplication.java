package com.github.wakingrufus.microblog;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

@Slf4j
public class JerseyApplication extends ResourceConfig {

    public JerseyApplication() {
        log.info("setting up hk2");
        packages("com.github.wakingrufus.microblog", "com.github.wakingrufus.microblog.jersey");

        JacksonJaxbJsonProvider jacksonJaxbJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJaxbJsonProvider.setMapper(new ObjectMapperFactory().buildObjectMapper());
        register(jacksonJaxbJsonProvider);
        register(new AbstractBinder() {
            @Override
            protected void configure() {

                // Shop to manually bind objects, in the case that the Jersey Auto-scan isn't working
                // e.g. bind(x.class).to(y.class).in(Singleton.class);
                // e.g. bind(x.class).to(y.class);
                //
                // note: if the object is generic, use TypeLiteral
                // e.g. bind(x.class).to(new TypeLiteral&lt;InjectionResolver&gt;(){});
                //
            }
        });
    }
}
