package com.n26.aggregator.common;

/**
 * DTO class to be returned in case if exception is thrown
 *
 * @author vinayanayak
 * @date 06-Jan-2018
 * ErrorResponse.java
 */
public class ErrorResponse {
    private String errorMessage;
    
	public ErrorResponse(String errorMessage) {
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
