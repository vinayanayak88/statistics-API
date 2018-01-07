/**
 * 
 */
package com.n26.aggregator.model;

/**
 * @author vinayanayak
 * @date 04-Jan-2018
 * Transaction.java
 */
public class Transaction {
	
	private Double amount;
	private Long timestamp;
	
	public Transaction() {
	}
	public Transaction(Double amount, Long timestamp) {
		super();
		this.amount = amount;
		this.timestamp = timestamp;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	

	

}
