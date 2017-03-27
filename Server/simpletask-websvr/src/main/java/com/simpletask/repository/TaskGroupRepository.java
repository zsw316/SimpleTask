package com.simpletask.repository;

import java.util.List;

import com.simpletask.model.TaskGroup;

public interface TaskGroupRepository {
	/*
	 * Return all the Task Groups that the specified user created
	 */
	public List<TaskGroup> getAllTaskGroupsForUser(long userId);
	
	/*
	 * Create a new Task Group 
	 */
	public TaskGroup createTaskGroup(long userId, String title, int importance, String labels);
	
	/*
	 * Delete Task Group
	 */
	public boolean deleteTaskGroup(long userId, String groupId);
}
