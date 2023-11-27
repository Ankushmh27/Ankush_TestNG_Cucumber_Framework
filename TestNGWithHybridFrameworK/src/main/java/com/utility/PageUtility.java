package com.utility;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.BaseClass;




public class PageUtility  {
	
	
	
	public static WebDriver driver=BaseClass.driver;
	//explicit wait for find out the element maximum time is 
	public static WebDriverWait wait = new WebDriverWait(BaseClass.driver, 30);
	
	public PageUtility(WebDriver driver)
	{
		BaseClass.driver=driver;
		
	}
	
	public static void sendKeys(WebElement element, String value)
	{

		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}
	
	public static void sendKeys(By by, String value)// method overloading
	{
		
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	
	public static void click(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}
	
	public static void selectRadioButton(List<WebElement> element, String value)
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		for(WebElement ele:element)
		{
			if(ele.getAttribute("value").equalsIgnoreCase(value))
			{
				ele.click();
				break;
			}
		}
		
		
	}
	
	public static void selectOptionFromDropDown(WebElement dropdown, String option)
	{
		wait.until(ExpectedConditions.visibilityOf(dropdown));
		Select select= new Select(dropdown);
		select.selectByVisibleText(option);
		
	}
	
	public static void switchToWindow(WebDriver driver, String parentWin, Set<String> allWin, String title )
	{

		for(String windows:allWin)
		{
			if(!windows.equals(parentWin))
			{
	             driver.switchTo().window(windows);
	             if(driver.getTitle().equalsIgnoreCase(title))
	            	BaseClass.log.info("Actual Title of Child Window is :- "+driver.getTitle()); 
	            	System.out.println("Title:- "+driver.getTitle());
				    System.out.println("Title:- "+driver.getCurrentUrl());
				    System.out.println("Title:- "+driver.getWindowHandle());
				    driver.close();
				}
			
			driver.switchTo().window(parentWin);
			}
		}
	
	public static void alertAccept(WebDriver driver) throws InterruptedException
	{
		Alert al=driver.switchTo().alert();
		BaseClass.log.info("Alert message :- "+al.getText());
		Thread.sleep(2000);
		al.accept();
	}
	
	}
			


