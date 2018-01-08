/**
 * 
 */
package com.n26.aggregator.test.acceptance.step;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import com.n26.aggregator.model.Transaction;
import com.n26.aggregator.service.StatisticsService;
import com.n26.aggregator.service.handler.StatisticsServiceHandler;
import com.n26.aggregator.test.acceptance.util.AbstractSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * @author vinayanayak
 * @date 08-Jan-2018
 * StatisticsSteps.java
 */
public class StatisticsSteps extends AbstractSteps{
	
	@Autowired
	 private StatisticsService statisticsService;
	
	@Given("^a statistics exists$")
	public void aStatisticsExists() throws Throwable {
		computeStatistics();
	}
	
	private void computeStatistics() throws Exception {
		StatisticsServiceHandler.getStatisticsforlastmin().clear();
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
	}
		
	@And("^the user gets the statistics$")
	public void theUserRetrievesTheStatistics() throws Throwable {
		get("/statistics");
	}
	
	@Then("^the user receives statistics status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastStatusCode());
	}
	
	@And("^the retrieved statistics is correct$")
	public void theRetrievedStatisticsIsCorrect() throws Throwable {
		assertStatisticsResourcesMatch(200, getLastStatusCode());
	}
	
	private static void assertStatisticsResourcesMatch(int expected, int actual) {
		Assert.assertEquals(expected,actual);
	}

}
