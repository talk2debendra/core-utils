/**
 * 
 */
package com.codingvine.core.base.exception;

/**
 * 
 * @author debendra.dhinda
 */
public class CodingVineException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	public CodingVineException(String message) {
		super(message);
	}

	public CodingVineException(Throwable cause) {
		super(cause);
	}

	public CodingVineException(String message, Throwable cause) {
		super(message, cause);
	}

}