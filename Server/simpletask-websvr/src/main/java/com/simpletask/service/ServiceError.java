package com.simpletask.service;

public enum ServiceError {
	
	NO_ERROR(0, ""),
	JSON_PROCESSING_ERROR(1, "Json processing error"),
	TASK_GROUP_NOT_EXIST(1000, "The task group is not exist."),
	TASK_GROUP_CREATATION_FAILED(1000, "The task group creation failed");
	
	private final int errorCode;
	private final String errorMsg;
	
	private ServiceError(int code, String msg) {
		this.errorCode = code;
		this.errorMsg = msg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	@Override
	  public String toString() {
	    return errorCode + ": " + errorCode;
	  }
}
