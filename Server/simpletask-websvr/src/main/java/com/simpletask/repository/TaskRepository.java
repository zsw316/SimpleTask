package com.simpletask.repository;

import java.util.List;

import com.simpletask.model.Task;

public interface TaskRepository {
	/*
	 * Return all the Tasks created by the specified user
	 */
	public List<Task> getAllTasksForUser(long userId);
	
	/*
	 * Return all the Tasks specified by user id and task group id
	 */
	public List<Task> getAllTasksForUserAndGroup(long userId, String groupId);
	
	/*
	 * Create a new Task 
	 */
	public Task createTask(long userId, String description, String groupId, String dueTimeStr, int priority, String labels, 
			String parentId, long owner);
	
	/*
	 * Delete a Task
	 */
	public boolean deleteTask(long userId, String taskId);
}
