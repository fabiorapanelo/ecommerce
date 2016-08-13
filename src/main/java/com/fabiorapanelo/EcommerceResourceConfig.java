package com.fabiorapanelo;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class EcommerceResourceConfig extends ResourceConfig  {
	
	public EcommerceResourceConfig() {
        packages("com.fabiorapanelo");
        register(JacksonFeature.class);
    }
	
}