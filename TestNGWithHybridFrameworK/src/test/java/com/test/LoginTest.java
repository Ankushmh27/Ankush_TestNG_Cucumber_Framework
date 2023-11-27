package com.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.page.LoginPage;


public class LoginTest extends BaseClass {
	
	LoginPage lp ;
	
	@BeforeMethod
	public void beforeMethod()
	{
		
		log.info(" Initialization Browser");
		initialization();
		log.info("Load a LoginPage");
		lp = new LoginPage(driver);
		
		
	}
	
	@AfterMethod
	public void afterMethod() throws Exception
	{
		
		log.info("Closing the Browser");
		Thread.sleep(2000);
		tearDown();
		log.info("Browser Closed");
	}
	
	@Test(priority = 0)
	public void verifyTheLogingWithValidCredentials() throws Exception
	{
		log.info("verifyTheLogingWithValidCredentials");
		//Assert.assertTrue(lp.loginWithValidCredential());
		log.info("Logout To Application");
		lp.logOutApplication();
		
	}
	
	
	
	
	
	
	
	
	

}
