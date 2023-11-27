package com.base;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import com.utility.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;



public class BaseClass {
	
	public static WebDriver driver = null;
	public static Logger log=Logger.getLogger(BaseClass.class);
    public final static int TIMEOUT = 50;
	
	public void launchApplication()
	{
		driver.get(Utils.readProperties("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TIMEOUT, TimeUnit.SECONDS);
		
		}
	
	public WebDriver initialization()
	{	
		if(Utils.readProperties("browser").equals("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			launchApplication();
			return driver;
		}
		
		else
		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver= new FirefoxDriver();
			launchApplication();
			return driver;
		}
	}

	public static void tearDown()
	{
		driver.close();
	    driver.quit();
	}
	
	
	
	
	
	
	
	

}
