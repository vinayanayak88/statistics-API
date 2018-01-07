/**
 * 
 */
package com.n26.aggregator.exception;

/**
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
