package com.fabiorapanelo.authserver;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/auth-server/")
public class AuthServerApplication extends Application  {
	
	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(AuthorizeResource.class);
        s.add(TokenResource.class);
        return s;
    }
	
}