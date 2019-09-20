package com.conichi.currency.converter.exceptions;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 9/20/2019
 */
public class BadInternalServerException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -859512830786799974L;
	
	/**
	 * @param message
	 */
	public BadInternalServerException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public BadInternalServerException(String message, Throwable e) {
		super(message, e);
	}

}
