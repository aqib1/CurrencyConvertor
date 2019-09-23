package com.conichi.currency.converter.exceptions;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 9/20/2019
 */
public class CacheRefresherException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -734025269428477386L;

	public CacheRefresherException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public CacheRefresherException(String message, Throwable e) {
		super(message, e);
	}
}
