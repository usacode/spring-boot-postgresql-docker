package com.teamviewer.ecommerce.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author Paul Ngouabeu
 * This class serves as model for the exception response.
 *
 */
@Data
@EqualsAndHashCode
public class ErrorModel {
	/**
	 * The property for the error status
	 */
	private HttpStatus status;
	
	/**
	 * The property for the error message
	 */
	private String message;
	
	/**
	 * The property for the error fields
	 */
	private Object errors;

	/**
	 * The class constructor for ErrorModel
	 * @param status - status
	 * @param message - message
	 * @param errors - errors
	 */
	public ErrorModel(final HttpStatus status, final String message, final Object errors) {
		super();
		this.status=status;
		this.message=message;
		this.errors=errors;
	}

	
}
