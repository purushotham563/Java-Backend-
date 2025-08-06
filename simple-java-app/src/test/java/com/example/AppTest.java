package com.example;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class AppTest {

	@Test
	public void testGetMessage() {
		App app=new App();
		String message=app.getMessage();
		assertEquals("Hello from simple Java App!",message);

	}
	@Test
	public void testMessageNotNull(){
		App app=new App();
		assertNotNull(app.getMessage());
	}

}
