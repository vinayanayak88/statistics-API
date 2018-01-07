package com.n26.aggregator.test.acceptance.step;

import org.json.JSONObject;
import org.junit.Assert;

import com.n26.aggregator.test.acceptance.util.AbstractSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author vinayanayak
 * @date 07-Jan-2018
 * TransactionSteps.java
 */
public class TransactionSteps extends AbstractSteps{
	
	
	@When("^the user creates an transaction$")
	public void theUserCallsSaveTransaction() throws Throwable {
		String paylod = createTransactionPaylod();
		createTranaction(paylod);
	}
	
	private void createTranaction(String payload) throws Exception {
		post("/transactions", payload);
	}
	
	@And("^the transaction is successfully created$")
	public void theTransactionIsSuccessfullyCreated() {
		 Assert.assertEquals(201, getLastPostResponse().getStatus());
	}
	
	@Then("^the user receives status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastStatusCode());
	}
	
	private String createTransactionPaylod() {
    	JSONObject obj = new JSONObject();
		obj.put("amount", "12.5");
		obj.put("timestamp", System.currentTimeMillis() - 10000);
		return obj.toString();
    }
}
