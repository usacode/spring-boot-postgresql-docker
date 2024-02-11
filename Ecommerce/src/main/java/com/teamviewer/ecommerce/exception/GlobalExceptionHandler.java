package com.teamviewer.ecommerce.exception;



import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 
 * @author Paul Ngouabeu
 * This class advice the controllers for triggering the exceptions
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * This handleNotFoundException method handles the not found exception
	 * @param NotFoundException - NotFoundException
	 * @return It returns ResponseEntity 
	 */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(final NotFoundException ex) {
    	 final ErrorModel apiError = new ErrorModel(ex.getStatusCode(), ex.getMessage(), ex.getFieldName());
		return new ResponseEntity<>(apiError,ex.getStatusCode());
    }

    /**
	 * This handleBadRequestException method handles the bad request exception
	 * @param BadRequestException - BadRequestException
	 * @return It returns ResponseEntity
	 */
   @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex) {
    	final  ErrorModel apiError = new ErrorModel(ex.getStatusCode(), ex.getMessage(), ex.getAllFields());
		return new ResponseEntity<>(apiError,ex.getStatusCode());
    }
   
   /**
 	 * This handleUnauthorizedAccessException method handles the unauthorized access exception.
 	 * @param UnauthorizedAccessException - UnauthorizedAccessException
 	 * @return It returns ResponseEntity
 	 */
    @ExceptionHandler(UnauthorizedAccessException.class)
     public ResponseEntity<Object> handleUnauthorizedAccessException(final UnauthorizedAccessException ex) {
     	final  ErrorModel apiError = new ErrorModel(ex.getStatusCode(), ex.getMessage(), ex.getFieldName());
 		return new ResponseEntity<>(apiError,ex.getStatusCode());
     }
    
}
