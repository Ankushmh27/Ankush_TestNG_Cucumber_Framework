package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.listeners.MyListeners;
import com.page.LoginPage;
import com.utility.PageUtility;
import com.utility.XLUtility;

@Listeners(MyListeners.class)
public class UsingDataProvider extends BaseClass{
	
	LoginPage lp;
	
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
	
	@Test(dataProvider = "getData")
	public void loginByTestData(String user, String pass, String expTitle )
	{
		
		lp.setTxtEmail(lp.getTxtEmail());
		PageUtility.sendKeys(lp.getTxtEmail(), user);
		
		lp.setTxtPassword(lp.getTxtPassword());
		PageUtility.sendKeys(lp.getTxtPassword(), pass);
		
		PageUtility.click(lp.btnLogin);
		
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
		
		lp.logOutApplication();
		
		 
	}
	
	@DataProvider
	public Object [][] getData() throws Exception
	{
		String path="LoginData.xlsx";
		
		XLUtility xlutility= new XLUtility(path);
		
		int totalrows=xlutility.getRowCount("Sheet1");
		
		int totalcolumns=xlutility.getCellCount("Sheet1",0);
		
		String loginData [][]= new String [totalrows][totalcolumns];
		
		for(int i=0; i<=totalrows;i++)
			
			
		{
			for(int j=0; j<=totalcolumns;j++)
			{
				
				try
				{
					String data=xlutility.getCellData("Sheet1", i, j);
					loginData [i][j]=data;
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				System.out.print(loginData+ " ");
			}
			
			System.out.println();
			
			
		}
		return loginData;
	}
	
	

}
