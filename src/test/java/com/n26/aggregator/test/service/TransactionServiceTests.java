package com.n26.aggregator.test.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.aggregator.exception.ValidationException;
import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.TransactionService;
import com.n26.aggregator.service.handler.StatisticsServiceHandler;
import com.n26.aggregator.service.handler.TransactionServiceHandler;

/**
 * @author vinayanayak
 * @date 06-Jan-2018
 * TransactionServiceTests.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTests {

    @Autowired
    private TransactionService transactionService;

    @Mock
    private StatisticsServiceHandler statisticsServiceMock;

    @InjectMocks
    private TransactionServiceHandler transactionServiceMock;


    @Test(expected = ValidationException.class)
    public void whenEmptyRequestBody_exceptionThrown(){
        transactionService.addTransaction(null);
    }

    @Test(expected = ValidationException.class)
    public void whenMissingTimestampField_exceptionThrown(){
        transactionService.addTransaction(new Transaction(12.5, null));
    }

    @Test(expected = ValidationException.class)
    public void whenMissingAmountField_exceptionThrown(){
        transactionService.addTransaction(new Transaction(null, System.currentTimeMillis()));
    }
    
    @Test(expected = ValidationException.class)
    public void whenMissingAmountFieldAndMissingTimestampField_exceptionThrown(){
        transactionService.addTransaction(new Transaction(null, null));
    }
    
    @Test
    public void whenValidTransaction_flowSucceeds(){
        doNothing().when(statisticsServiceMock).computeStatistics(any(Transaction.class));
        transactionServiceMock.addTransaction(new Transaction(12.5, System.currentTimeMillis()));

        verify(statisticsServiceMock, times(1)).computeStatistics(any(Transaction.class));
    }
}
