package com.cor;

import com.cor.service.HelloWorldService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;


@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(HelloWorldService.class);
    }


}
