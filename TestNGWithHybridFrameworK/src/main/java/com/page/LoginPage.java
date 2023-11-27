package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.base.BaseClass;
import com.pageObjectRepo.LoginRepo;
import com.utility.PageUtility;



public class LoginPage extends LoginRepo {
	
    WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean loginWithValidCredential() throws Exception
	{
		setTxtEmail(getTxtEmail());
		PageUtility.sendKeys(getTxtEmail(), "admin@yourstore.com");
		
		setTxtPassword(getTxtPassword());
		PageUtility.sendKeys(getTxtPassword(), "admin");
		
		PageUtility.click(btnLogin);
		
		String actTitle=driver.getTitle();
		BaseClass.log.info("Actual Title of Page :- "+actTitle);
		
		String expTitle="Dashboard / nopCommerce administration";
		BaseClass.log.info("Expected Title of Page :- "+expTitle);
		
		if(actTitle.equals(expTitle))
		{
			BaseClass.log.info("Actual Title is Match with Expected Title");
		return true;
		}
		
		else
		{
			BaseClass.log.info("Actual Title is not Match with Expected Title there fore our Test Case is fail");
		return false;
		}
	}
	
	@Test(dataProvider  = "getData")
	public void loginByTestData(String user, String pass, String expTitle )
	{
		
		setTxtEmail(getTxtEmail());
		PageUtility.sendKeys(getTxtEmail(), user);
		
		setTxtPassword(getTxtPassword());
		PageUtility.sendKeys(getTxtPassword(), pass);
		
		PageUtility.click(btnLogin);
		
		String actTitle=driver.getTitle();
		BaseClass.log.info("Actual Title of Page :- "+actTitle);
		
		
		BaseClass.log.info("Expected Title of Page from Excel :- "+expTitle);
		
		if(actTitle.equals(expTitle))
		{
			BaseClass.log.info("Actual Title is Match with Expected Title");
		 Assert.assertTrue(true);
		}
		
		else
		{
			BaseClass.log.info("Actual Title is not Match with Expected Title there fore our Test Case is fail");
			 Assert.assertTrue(false);
		}
		
		 
	}
	
	public void logOutApplication()
	{
		PageUtility.click(btnLogout);
		String title=driver.getTitle();
		BaseClass.log.info("Title of the page after Log Out :- "+title);
	}
	
	
	
	
}
