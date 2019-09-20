package com.conichi.currency.converter.exceptions;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since  09/20/2019
 */
public class InvalidResponseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4738423698647895073L;
	
	/**
	 * @param message
	 */
	public InvalidResponseException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public InvalidResponseException(String message, Throwable e) {
		super(message, e);
	}

}
