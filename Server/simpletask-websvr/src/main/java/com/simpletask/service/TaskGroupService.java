package com.simpletask.service;

public interface TaskGroupService {

	/*
	 * Return all groups for specific user
	 */
	public ServiceResult getAllTaskGroupsForUser(long userId);
	
	/*
	 * Create a new task group
	 */
	public ServiceResult createTaskGroup(long userId, String title, int importance, String labels);
	
	/*
	 * Delete a specific task group
	 */
	public ServiceResult deleteTaskGroup(long userId, String groupId);
}
