package com.n26.aggregator.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.aggregator.exception.InvalidTimestampException;
import com.n26.aggregator.model.StatisticsSummary;
import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.StatisticsService;
import com.n26.aggregator.service.TransactionService;
import com.n26.aggregator.service.handler.StatisticsServiceHandler;

/**
 * @author vinayanayak
 * @date 06-Jan-2018
 * TransactionServiceHandler.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceTests {

    @Autowired
    private StatisticsService statisticsService;
    
    @Autowired
    private TransactionService transactionService;
    
    @Before
	public void setUp() {
    	StatisticsServiceHandler.getStatisticsforlastmin().clear();
	}

    @Test(expected = InvalidTimestampException.class)
    public void whenInvalidTimestamp_exceptionThrown(){
        transactionService.addTransaction(new Transaction(10.5, System.currentTimeMillis() - 60000));
    }
    
    @Test
    public void whenValidTimestamp_computeStatistics(){
        statisticsService.computeStatistics(new Transaction(5.5, System.currentTimeMillis() - 10000));
        statisticsService.computeStatistics(new Transaction(15.5, System.currentTimeMillis() - 9000));
        statisticsService.computeStatistics(new Transaction(25.2, System.currentTimeMillis() - 8000));
        statisticsService.computeStatistics(new Transaction(65.5, System.currentTimeMillis() - 7000));
        statisticsService.computeStatistics(new Transaction(5.7, System.currentTimeMillis() - 6000));
        statisticsService.computeStatistics(new Transaction(5.8, System.currentTimeMillis() - 5000));
        statisticsService.computeStatistics(new Transaction(3.5, System.currentTimeMillis() - 4000));
        statisticsService.computeStatistics(new Transaction(2.8, System.currentTimeMillis() - 3000));
        statisticsService.computeStatistics(new Transaction(9.5, System.currentTimeMillis() - 2000));
        statisticsService.computeStatistics(new Transaction(12.3, System.currentTimeMillis() - 1000));

        StatisticsSummary summary = statisticsService.getStatistics();
        assertEquals(summary.getCount(), 10l);
        assertEquals(summary.getSum(), 151.3, 0.0);
        assertEquals(summary.getMax(), 65.5, 0.0);
        assertEquals(summary.getMin(), 2.8, 0.0);
        assertEquals(summary.getAvg(), 151.3 / 10, 0.0);
    }

}
