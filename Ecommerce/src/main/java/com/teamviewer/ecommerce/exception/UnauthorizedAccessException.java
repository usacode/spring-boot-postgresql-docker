package com.teamviewer.ecommerce.exception;

import org.springframework.http.HttpStatus;


/**
 * 
 * @author Paul Ngouabeu
 * This class holds the implementation of UnauthorizedAccessException
 *
 */
public class UnauthorizedAccessException extends EcommerceException{

	
	private static final long serialVersionUID = 1L;
	private HttpStatus statusCode;
	private Object fieldName;
		
	public UnauthorizedAccessException(String message,HttpStatus statusCode,Object fieldName) {
        super(message);
        this.statusCode=statusCode;
        this.fieldName=fieldName;
    }

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public Object getFieldName() {
		return fieldName;
	}

	public void setFieldName(Object fieldName) {
		this.fieldName = fieldName;
	}
	

}
