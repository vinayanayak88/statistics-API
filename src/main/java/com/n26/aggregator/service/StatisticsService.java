
package com.n26.aggregator.service;

import org.springframework.stereotype.Service;

import com.n26.aggregator.model.StatisticsSummary;
import com.n26.aggregator.model.Transaction;

/**
 * @author vinayanayak
 * @date 04-Jan-2018
 * SatisticsService.java
 */
@Service
public interface StatisticsService {
	
	public void computeStatistics(Transaction transaction);

    public StatisticsSummary getStatistics();

}
