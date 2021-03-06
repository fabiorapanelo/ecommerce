package com.fabiorapanelo;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class EcommerceResourceConfig extends ResourceConfig  {
	
	public EcommerceResourceConfig() {
        packages("com.fabiorapanelo.customer");
        packages("com.fabiorapanelo.catalog");
        packages("com.fabiorapanelo.order");
        register(JacksonFeature.class);
        register(EcommerceObjectMapperProvider.class);
    }
	
}