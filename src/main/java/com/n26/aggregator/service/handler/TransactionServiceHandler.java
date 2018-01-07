package com.n26.aggregator.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.aggregator.constant.Constant;
import com.n26.aggregator.exception.InvalidTimestampException;
import com.n26.aggregator.exception.ValidationException;
import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.StatisticsService;
import com.n26.aggregator.service.TransactionService;

/**
 * @author vinayanayak
 * @date 04-Jan-2018 TransactionServiceHandler.java
 */
@Service
public class TransactionServiceHandler implements TransactionService {

	private static final Logger log = LoggerFactory.getLogger(TransactionServiceHandler.class);

	@Autowired
	StatisticsService service;

	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction == null)
			throw new ValidationException(Constant.VALIDATION_EMPTY_REQUEST_BODY);
		if (transaction.getAmount() == null && transaction.getTimestamp() == null)
			throw new ValidationException(Constant.VALIDATION_MISSING_AMOUNT_AND_TIMESTAMP);
		if (transaction.getTimestamp() == null || transaction.getTimestamp().toString().isEmpty())
			throw new ValidationException(Constant.VALIDATION_MISSING_TIMESTAMP);
		if (transaction.getAmount() == null)
			throw new ValidationException(Constant.VALIDATION_MISSING_AMOUNT);
		if((System.currentTimeMillis() - transaction.getTimestamp()) / 1000 >= 60 )
			throw new InvalidTimestampException(Constant.INVALID_TRANSACTION);
		service.computeStatistics(transaction);
	}

}
