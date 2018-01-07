package com.n26.aggregator.test.integration.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;


/**
 * @author vinayanayak
 * @date 07-Jan-2018
 * TransactionControllerTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionControllerTest extends ControllerIntegrationTest{
	

 	private static final String TEST_INVALID_TIME_TRANSACTION = "{\"amount\": \"12.3\", \"timestamp\": \"1515087936000\"}";

	@Test
    public void testCreateNewTrasactionEnsureCorrectResponse() throws Exception {
		String paylod = createTransactionPaylod();
    	createTransaction(paylod)
		.andExpect(status().isCreated());
    }
	
    private ResultActions createTransaction(String payload) throws Exception {
    	return post("/transactions", payload);
    }

    private String createTransactionPaylod() {
    	JSONObject obj = new JSONObject();
		obj.put("amount", "12.5");
		obj.put("timestamp", System.currentTimeMillis() - 10000);
		return obj.toString();
    }
    
    @Test
    public void testInvalidTimeStampTrasactionEnsureCorrectResponse() throws Exception {
    	createTransaction(TEST_INVALID_TIME_TRANSACTION)
		.andExpect(status().isNoContent());
    }

}
