/**
 * 
 */
package com.codingvine.core.base.exception;

import lombok.Getter;

/**
 * @author debendra.dhinda
 */
@Getter
public class CodingVineHttpException extends RuntimeException {

	private static final long serialVersionUID = -3368655266237942363L;

	private int statusCode;

	public CodingVineHttpException(String message) {
		super(message);
	}

	public CodingVineHttpException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public CodingVineHttpException(Throwable cause) {
		super(cause);
	}

	public CodingVineHttpException(String message, Throwable cause) {
		super(message, cause);
	}

}