package com.fabiorapanelo;



import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Provider
public class EcommerceObjectMapperProvider implements ContextResolver<ObjectMapper> {
 
    final ObjectMapper defaultObjectMapper;
 
    public EcommerceObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
    }
 
    public ObjectMapper getContext(Class<?> type) {
        return defaultObjectMapper;
    }
 
    private static ObjectMapper createDefaultMapper() {
        final ObjectMapper result = new ObjectMapper();
        
        result.registerModule(new Hibernate5Module());        
 
        return result;
    }
}