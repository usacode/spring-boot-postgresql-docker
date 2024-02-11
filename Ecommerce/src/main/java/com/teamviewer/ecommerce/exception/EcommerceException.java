package com.teamviewer.ecommerce.exception;


/**
 * 
 * @author Paul Ngouabeu
 * This class is the custom exception class for the service
 *
 */
public class EcommerceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for EcommerceException with one parameter
	 * @param message
	 */
	public EcommerceException(final String message) {
        super(message);
    }

	/**
	 * Constructor for EcommerceException with two parameters
	 * @param message
	 * @param cause
	 */
    public EcommerceException(final String message, final Throwable cause) {
        super(message, cause);
    }
}