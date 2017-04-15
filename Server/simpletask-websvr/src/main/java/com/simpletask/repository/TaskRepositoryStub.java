package com.simpletask.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.simpletask.model.Task;

public class TaskRepositoryStub implements TaskRepository {

	private List<Task> tasks;
	
	private static TaskRepositoryStub uniqueInstance = null;
	
	private Logger logger = Logger.getLogger(TaskRepositoryStub.class);
	
	private TaskRepositoryStub() {
		tasks = new ArrayList<Task>();
		
		Task task1 = new Task();
		long userId = 10000;
		Date createdDate = new Date();
		String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
		task1.setTaskId(String.format("%d-task-%s", userId, createdDateStr));
		task1.setCreatedBy(userId);
		task1.setCreatedDate(createdDate);
		task1.setDescription("my first ToDo");
		task1.setDueTime(createdDate);
		task1.setGroupId("");
		task1.setOwner(userId);
		task1.setParentId("");
		task1.setPriority(0);
		
		tasks.add(task1);
		
		Task task2 = new Task();
		userId = 10001;
		createdDate = new Date();
		createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
		task2.setTaskId(String.format("%d-task-%s", userId, createdDateStr));
		task2.setCreatedBy(userId);
		task2.setCreatedDate(createdDate);
		task2.setDescription("This is a ToDo");
		task2.setDueTime(createdDate);
		task2.setGroupId("");
		task2.setOwner(userId);
		task2.setParentId("");
		task2.setPriority(0);
		
		tasks.add(task2);
	}
	
	
	public static TaskRepositoryStub getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TaskRepositoryStub();
		}
		
		return uniqueInstance;
	}
	@Override
	public List<Task> getAllTasksForUser(long userId) {
		
		List<Task> taskList = new ArrayList<Task>();
		for (Task task : tasks) {
			if (task.getCreatedBy() == userId || task.getOwner() == userId) {
				taskList.add(task);
			}
		}
		
		return taskList;
	}

	@Override
	public List<Task> getAllTasksForUserAndGroup(long userId, String groupId) {
		List<Task> taskList = new ArrayList<Task>();
		for (Task task : tasks) {
			if ((task.getCreatedBy() == userId || task.getOwner() == userId) && task.getGroupId().equalsIgnoreCase(groupId)) {
				taskList.add(task);
			}
		}
		
		return taskList;
	}

	@Override
	public Task createTask(long userId, String description, String groupId, String dueTimeStr, int priority, String labels, 
			String parentId, long owner) {
		
		Task task = new Task();
		Date createdDate = new Date();
		String createdDateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(createdDate);
		task.setTaskId(String.format("%d-task-%s", userId, createdDateStr));
		task.setCreatedBy(userId);
		task.setCreatedDate(createdDate);
		task.setDescription(description);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			Date dueDate=format.parse(dueTimeStr);
			task.setDueTime(createdDate);
		} catch (ParseException e) {
			logger.error(String.format("failed to parse dueTimeStr: %s", dueTimeStr));
		}
		task.setGroupId(groupId);
		task.setOwner(owner);
		task.setParentId(parentId);
		task.setPriority(priority);
		tasks.add(task);
		
		return task;
	}


	@Override
	public boolean deleteTask(long userId, String taskId) {
		// TODO Auto-generated method stub
		return false;
	}

}
