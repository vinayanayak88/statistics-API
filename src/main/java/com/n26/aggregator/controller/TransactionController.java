/**
 * 
 */
package com.n26.aggregator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.TransactionService;


/**
 * @author vinayanayak
 * @date 04-Jan-2018
 * TransactionController.java
 */
@RestController
@RequestMapping(value = "/transactions", produces = "application/json")
public class TransactionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Void> saveTransaction(@RequestBody Transaction transaction) {
		logger.info(TransactionController.class + " : Received post request for transaction ");
		transactionService.addTransaction(transaction);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
