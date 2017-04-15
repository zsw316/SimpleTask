package com.simpletask.service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceResult {

	/*
	 * ResultCode indicates SUCCESS or FAILED
	 */
	private ResultCode code;
	
	/*
	 * Represent the error information
	 */
	private ServiceError error;
	
	/*
	 * The user data return to the client
	 */
	private String userData;
	
	public ResultCode getCode() {
		return code;
	}
	public void setCode(ResultCode code) {
		this.code = code;
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
	
	
}
