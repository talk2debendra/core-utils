/**
 * 
 */
package com.codingvine.core.base.exception;

import lombok.Getter;

/**
 * 
 * @author debendra.dhinda
 */
@Getter
public class CodingVineSecurityException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	private final int statusCode;

	public CodingVineSecurityException(String message) {
		super(message);
		this.statusCode = -1;
	}

	public CodingVineSecurityException(Throwable cause) {
		super(cause);
		this.statusCode = -1;
	}

	public CodingVineSecurityException(String message, Throwable cause) {
		super(message, cause);
		this.statusCode = -1;
	}

	public CodingVineSecurityException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

}