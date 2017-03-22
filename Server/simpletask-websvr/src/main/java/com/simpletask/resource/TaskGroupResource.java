package com.simpletask.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.simpletask.model.TaskGroup;
import com.simpletask.repository.TaskGroupRepositoryStub;

@Path("taskgroups")
public class TaskGroupResource {
	
	private Logger logger = Logger.getLogger(TaskGroupResource.class);
	
	@GET
	@Path("/{userId}")
	//@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskGroup> getTaskGroupsForUser(@PathParam("userId") long userId) {
		logger.debug(String.format("getTaskGroupsForUser: %d", userId));
		
		return TaskGroupRepositoryStub.getInstance().getAllTaskGroupsForUser(userId);
	}
	
	@PUT
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public TaskGroup createNewTaskGroup(@FormParam("userId") long userId, @FormParam("title") String title, 
			@FormParam("importance") int importance, @FormParam("labels") String labels) {
		logger.debug(String.format("createNewTaskGroup: userId: %d, title: %s, importance: %d, labels:%s ", 
				userId, title, importance, labels));
		
		return TaskGroupRepositoryStub.getInstance().createTaskGroup(userId, title, importance, labels);
	}
}