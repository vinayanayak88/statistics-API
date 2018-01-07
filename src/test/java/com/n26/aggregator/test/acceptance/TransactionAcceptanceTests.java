package com.n26.aggregator.test.acceptance;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.n26.aggregator.test.integration.controller.ControllerIntegrationTest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author vinayanayak
 * @date 07-Jan-2018
 * TransactionAcceptanceTests.java
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/acceptance/transaction.feature", glue = {"com.n26.aggregator.test.acceptance.step"}, format = {"pretty"})
@WebAppConfiguration
public class TransactionAcceptanceTests extends ControllerIntegrationTest{
	
}
