package com.simpletask;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper>{

	final ObjectMapper defaultObjectMapper;
	
	public ObjectMapperProvider() {
		defaultObjectMapper = createDefaultMapper();
	}
	
	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return defaultObjectMapper;
	}

	private static ObjectMapper createDefaultMapper() {
		return new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, true)
                .configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true)
                .setAnnotationIntrospector(createJaxbJacksonAnnotationIntrospector());
    }
	
    private static AnnotationIntrospector createJaxbJacksonAnnotationIntrospector() {

        final AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector(TypeFactory.defaultInstance());
        final AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

        return AnnotationIntrospector.pair(jacksonIntrospector, jaxbIntrospector);
    }
}
