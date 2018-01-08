/**
 * 
 */
package com.n26.aggregator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.aggregator.constant.Constant;
import com.n26.aggregator.exception.StatisticsNotFoundException;
import com.n26.aggregator.model.StatisticsSummary;
import com.n26.aggregator.service.StatisticsService;

/**
 * @author vinayanayak
 * @date 04-Jan-2018
 * StatisticsController.java
 */
@RestController
@RequestMapping(value = "/statistics", produces = "application/json")
public class StatisticsController {
	
	 private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);
	
	@Autowired
    private StatisticsService statisticsService;

	@RequestMapping(method = RequestMethod.GET)
    public StatisticsSummary getStatistics(){
		logger.info(StatisticsController.class + " : Received request for get statistics ");
		StatisticsSummary summary = statisticsService.getStatistics();
		if(summary.getCount() == 0) {
			throw new StatisticsNotFoundException(Constant.NO_STATISTICS_FOUND);
		}
        return statisticsService.getStatistics();
    }

}
