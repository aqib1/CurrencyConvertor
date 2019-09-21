package com.conichi.currency.converter.exceptions;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 9/21/2019
 */
public class CachePresistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6400145587508655927L;

	public CachePresistException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public CachePresistException(String message, Throwable e) {
		super(message, e);
	}
}
