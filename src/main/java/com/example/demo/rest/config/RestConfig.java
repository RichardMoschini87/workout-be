package com.example.demo.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.demo.rest.api.Risorse;

@Component
public class RestConfig extends ResourceConfig {

	public RestConfig(Class<?>... classes) {
		register(Risorse.class);
	}

}
