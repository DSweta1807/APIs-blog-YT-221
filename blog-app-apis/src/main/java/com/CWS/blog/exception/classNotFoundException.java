package com.CWS.blog.exception;

public class classNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValue;
	public classNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %l",resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
