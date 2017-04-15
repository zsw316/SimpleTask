package com.simpletask.service;

import java.io.IOException;
import java.util.List;

import javax.json.stream.JsonGenerationException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpletask.model.TaskGroup;
import com.simpletask.repository.TaskGroupRepositoryImpl;


public class TaskGroupServiceImpl implements TaskGroupService {

	@Override
	public ServiceResult getAllTaskGroupsForUser(long userId) {
		
		ServiceResult result = new ServiceResult();
		TaskGroupRepositoryImpl repository = new TaskGroupRepositoryImpl();
		List<TaskGroup> taskgroups = repository.getAllTaskGroupsForUser(userId);
		String jsonInString = "";
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// Convert object to JSON string
			jsonInString = mapper.writeValueAsString(taskgroups);
		} catch (JsonGenerationException e) {
			result.setCode(ResultCode.FAILED);
			result.setError(ServiceError.JSON_PROCESSING_ERROR);
			result.setUserData(e.toString());
			return result;
		} catch (JsonMappingException e) {
			result.setCode(ResultCode.FAILED);
			result.setError(ServiceError.JSON_PROCESSING_ERROR);
			result.setUserData(e.toString());
			return result;
		} catch (IOException e) {
			result.setCode(ResultCode.FAILED);
			result.setError(ServiceError.JSON_PROCESSING_ERROR);
			result.setUserData(e.toString());
			return result;
		}
		
		result.setCode(ResultCode.SUCCESS);
		result.setError(ServiceError.NO_ERROR);
		result.setUserData(jsonInString);
		return result;
	}

}
