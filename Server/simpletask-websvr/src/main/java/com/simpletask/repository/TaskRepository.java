package com.simpletask.repository;

import java.util.List;

import com.simpletask.model.Task;

public interface TaskRepository {
	/*
	 * Return all the Tasks created by the specified user
	 */
	public List<Task> getAllTasksForUser(int userId);
	
	/*
	 * Return all the Tasks specified by user id and task group id
	 */
	public List<Task> getAllTasksForUserAndGroup(int userId, String groupId);
	
	/*
	 * Create a new Task 
	 */
	public Task createTask(int userId, String description);
}
