package com.simpletask;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.simpletask.resource.TaskGroupResource;
import com.simpletask.resource.TaskResource;

public class SimpleApplication extends ResourceConfig {
	public SimpleApplication() {
		super(
				TaskGroupResource.class,
				TaskResource.class,
				JacksonFeature.class
				);
	}
}
