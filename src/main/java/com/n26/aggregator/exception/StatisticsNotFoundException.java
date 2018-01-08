package com.n26.aggregator.exception;

/**
 * Unchecked exception which is supposed to be
 * thrown when there is no statistics summary for last 60 secs
 * 
 * @author vinayanayak
 * @date 05-Jan-2018
 * SummaryNotFoundException.java
 */
public class StatisticsNotFoundException extends RuntimeException{

	 private String errorMessage;

	public StatisticsNotFoundException(String errorMessage) {
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
