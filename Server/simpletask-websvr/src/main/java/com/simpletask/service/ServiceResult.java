package com.simpletask.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceResult {

	/*
	 * ResultCode indicates SUCCESS or FAILED
	 */
	private ResultCode result;
	
	/*
	 * Represent the error information
	 */
	private ServiceError error;
	
	/*
	 * The user data return to the client
	 */
	private String userData;
	
	public ResultCode getResult() {
		return result;
	}
	
	public void setResult(ResultCode code) {
		this.result = code;
	}
	
	public ServiceError getError() {
		return error;
	}
	public void setError(ServiceError error) {
		this.error = error;
	}
	
	public String getUserData() {
		return userData;
	}
	public void setUserData(String userData) {
		this.userData = userData;
	}
	
	public void buildResult(ResultCode code, ServiceError error, String userData) {
		setResult(code);
		setError(error);
		setUserData(userData);
	}
	
}
