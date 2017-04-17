package com.simpletask.service;

import java.io.IOException;
import java.util.List;

import javax.json.stream.JsonGenerationException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpletask.model.TaskGroup;
import com.simpletask.repository.TaskGroupRepository;
import com.simpletask.repository.TaskGroupRepositoryImpl;


public class TaskGroupServiceImpl implements TaskGroupService {

	private Logger logger = Logger.getLogger(TaskGroupService.class);
	
	private TaskGroupRepository taskGroupRepository;
	
	public TaskGroupServiceImpl() {
		taskGroupRepository = new TaskGroupRepositoryImpl();
	}
	
	@Override
	public ServiceResult getAllTaskGroupsForUser(long userId) {
		
		ServiceResult result = new ServiceResult();
		List<TaskGroup> taskgroups = taskGroupRepository.getAllTaskGroupsForUser(userId);
		
		String jsonInString = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			jsonInString = mapper.writeValueAsString(taskgroups);
		} catch (JsonGenerationException e) {		
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		} catch (JsonMappingException e) {
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		} catch (IOException e) {
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		}
		
		result.buildResult(ResultCode.SUCCESS, ServiceError.NO_ERROR, jsonInString);
		return result;
	}

	@Override
	public ServiceResult createTaskGroup(long userId, String title, int importance, String labels) {
		
		ServiceResult result = new ServiceResult();
		TaskGroup group = taskGroupRepository.createTaskGroup(userId, title, importance, labels);
		if (group == null) {
			result.buildResult(ResultCode.FAIL, ServiceError.TASK_GROUP_CREATATION_FAILED, "");
			return result;
		}
		
		String jsonInString = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			jsonInString = mapper.writeValueAsString(group);
		} catch (JsonGenerationException e) {
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		} catch (JsonMappingException e) {
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		} catch (IOException e) {
			result.buildResult(ResultCode.FAIL, ServiceError.JSON_PROCESSING_ERROR, e.toString());
			return result;
		}
		
		result.buildResult(ResultCode.SUCCESS, ServiceError.NO_ERROR, jsonInString);
		return result;
	}

	@Override
	public ServiceResult deleteTaskGroup(long userId, String groupId) {
		ServiceResult result = new ServiceResult();
		boolean success = taskGroupRepository.deleteTaskGroup(userId, groupId);
		if (success) {
			result.buildResult(ResultCode.SUCCESS, ServiceError.NO_ERROR, "");
		}
		else {
			result.buildResult(ResultCode.FAIL, ServiceError.TASK_GROUP_DELETION_FAILED, "");
		}
		
		return result;
	}

}
