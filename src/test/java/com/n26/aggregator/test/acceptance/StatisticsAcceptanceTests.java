/**
 * 
 */
package com.n26.aggregator.test.acceptance;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author vinayanayak
 * @date 08-Jan-2018
 * StatisticsAcceptanceTests.java
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/acceptance/statistics.feature", glue = {"com.n26.aggregator.test.acceptance.step"}, format = {"pretty"})
@WebAppConfiguration
public class StatisticsAcceptanceTests {

}
