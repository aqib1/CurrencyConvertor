package com.conichi.currency.converter.exceptions;

/**
 * @author Aqib_Javed
 *
 */
public class InvalidParamException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842931377050485340L;

	/**
	 * @param message
	 */
	public InvalidParamException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public InvalidParamException(String message, Throwable e) {
		super(message, e);
	}

}
