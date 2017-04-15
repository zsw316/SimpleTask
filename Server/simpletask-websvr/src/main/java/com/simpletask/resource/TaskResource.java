package com.simpletask.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.simpletask.model.Task;
import com.simpletask.repository.TaskRepositoryImpl;

@Path("tasks")
public class TaskResource {

	private Logger logger = Logger.getLogger(TaskResource.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Task> getTasksForUser(@QueryParam("userId") long userId) {
		logger.debug(String.format("getTasksForUser: %d", userId));
		
		TaskRepositoryImpl repository = new TaskRepositoryImpl();
		return repository.getAllTasksForUser(userId);
	}
	
	@PUT
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Task createTask(@FormParam("userId") long userId, @FormParam("desc") String description, @FormParam("groupId") String groupId, 
			@FormParam("dueTime") String dueTimeStr, @FormParam("priority") int priority, @FormParam("labels") String labels, 
			@FormParam("parentId") String parentId, @FormParam("owner") long owner) {
		
		TaskRepositoryImpl repository = new TaskRepositoryImpl();
		return repository.createTask(userId, description, groupId, dueTimeStr, priority, labels, parentId, owner);
	}
	
	@DELETE
	@Path("/{taskId}/del")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public boolean deleteTask(@PathParam("taskId") String taskId, @FormParam("userId") long userId) {
		TaskRepositoryImpl repository = new TaskRepositoryImpl();
		return repository.deleteTask(userId, taskId);
	}
}
