package com.simpletask.service;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResultCode {
	
	SUCCESS(0, "success"), 
	FAIL(1, "fail");
	
	private final int code;
	private final String description;
	
	private ResultCode(int code, String desc) {
		this.code = code;
		this.description = desc;
	}
	
	@JsonValue
	public int getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	  public String toString() {
	    return code + ": " + description;
	  }
}
