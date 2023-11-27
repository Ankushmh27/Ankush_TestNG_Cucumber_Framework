package com.utility;

import java.io.File;
import java.io.FileInputStream;



import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.base.BaseClass;



public class Utils extends BaseClass {
	static FileInputStream fis = null;
	static Properties prop;
	
	//takeScreenshot code
	public static String getScreenshot(String name) throws Exception
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/screenshot/"+name+".jpg";
		
		File dest = new File(path);
		
		FileUtils.copyFile(src, dest);
			
		return path;
		
	}
	
	public static String readProperties(String key)
	{
		String path = System.getProperty("user.dir")+"/src/main/resources/config.properties";
		Properties prop= new Properties();
		
		try {
			fis=new FileInputStream(path);
			
			prop.load(fis);
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
		
		return prop.getProperty(key);
	}

}
