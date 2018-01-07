package com.n26.aggregator.exception;

/**
 * @author vinayanayak
 * @date 05-Jan-2018
 * TrnsactionExpiredException.java
 */
public class InvalidTimestampException extends RuntimeException{

	 private String errorMessage;
	 
	public InvalidTimestampException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	 
	 
}
