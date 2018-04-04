package com.mytaxi.exception;

/**
 * @author Mostafa El-Gazzar.
 */
public class BusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
}
