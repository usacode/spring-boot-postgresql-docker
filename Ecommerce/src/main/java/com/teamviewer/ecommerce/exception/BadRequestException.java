package com.teamviewer.ecommerce.exception;



import org.springframework.http.HttpStatus;

/**
 * 
 * @author Paul Ngouabeu
 * This class holds the implementation of BadRequestException
 *
 */
public class BadRequestException extends EcommerceException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Object allFields;
	private  HttpStatus statusCode;

	public BadRequestException(String message,HttpStatus statusCode,Object allFields) {
        super(message);
        this.allFields=allFields;
        this.statusCode=statusCode;
    }

	public Object getAllFields() {
		return allFields;
	}

	public void setAllFields(Object allFields) {
		this.allFields = allFields;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}


}
