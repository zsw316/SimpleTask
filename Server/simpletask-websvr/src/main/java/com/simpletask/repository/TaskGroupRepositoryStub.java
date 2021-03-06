package com.simpletask.repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.simpletask.model.TaskGroup;
import com.simpletask.repository.TaskGroupRepository;

public class TaskGroupRepositoryStub implements TaskGroupRepository {

	private List<TaskGroup> taskGroups;
	
	private static TaskGroupRepositoryStub uniqueInstance = null;
	
	private TaskGroupRepositoryStub() {
		taskGroups = new ArrayList<TaskGroup>();
		
		TaskGroup group1 = new TaskGroup();
		long userId = 10000;
		Date createdDate = new Date();
    	String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
    	group1.setGroupId(String.format("%d-proj-%s", userId, createdDateStr));
    	group1.setTitle("Inbox");
    	group1.setCreatedBy(userId);
    	group1.setCreatedDate(createdDate);
    	group1.setImportance(0);	
    	group1.setLabels("");
    	taskGroups.add(group1);

    	TaskGroup group2 = new TaskGroup();
    	userId = 10001;
		createdDate = new Date();
    	createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
    	
    	group2.setGroupId(String.format("%d-proj-%s", userId, createdDateStr));
    	group2.setTitle("Work");
    	group2.setCreatedBy(userId);
    	group2.setCreatedDate(createdDate);
    	group2.setImportance(0);	
    	group2.setLabels("");
    	taskGroups.add(group2);
	}
	
	public static TaskGroupRepositoryStub getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TaskGroupRepositoryStub();
		}
		
		return uniqueInstance;
	}
	
	@Override
	public List<TaskGroup> getAllTaskGroupsForUser(long userId) {
		List<TaskGroup> groupList = new ArrayList<TaskGroup>();
		for (TaskGroup group:taskGroups) {
			if (group.getCreatedBy() == userId) {
				groupList.add(group);
			}
		}
		
		return groupList;
	}

	@Override
	public TaskGroup createTaskGroup(long userId, String title, int importance, String labels) {
		TaskGroup group = new TaskGroup();
		Date createdDate = new Date();
		String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
		
		group.setGroupId(String.format("%d-proj-%s", userId, createdDateStr));
		group.setTitle(title);
		group.setCreatedBy(userId);
		group.setCreatedDate(createdDate);
		group.setImportance(importance);
		group.setLabels(labels);
		taskGroups.add(group);
		return group;
	}

	@Override
	public boolean deleteTaskGroup(long userId, String groupId) {
		for (TaskGroup group:taskGroups) {
			if (group.getCreatedBy() == userId && group.getGroupId().equalsIgnoreCase(groupId)) {
				taskGroups.remove(group);
				return true;
			}
		}
		
		return false;
	}
}
