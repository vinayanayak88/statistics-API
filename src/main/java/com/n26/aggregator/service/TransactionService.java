/**
 * 
 */
package com.n26.aggregator.service;

import org.springframework.stereotype.Service;

import com.n26.aggregator.model.Transaction;

/**
 * @author vinayanayak
 * @date 04-Jan-2018
 * TransactionService.java
 */
@Service
public interface TransactionService {
	
	public void addTransaction(Transaction transaction);
	
}
