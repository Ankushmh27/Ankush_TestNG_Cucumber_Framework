package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;
import com.utility.ReportUtils;
import com.utility.Utils;



public class MyListeners extends ReportUtils  implements ITestListener{

	public void onTestStart(ITestResult result) {
		
		test=report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		
		test.log(Status.PASS, "testcase passed with name :- "+result.getName());
	}

	public void onTestFailure(ITestResult result) {
		
       test.log(Status.FAIL, "testcase failed with name :- "+result.getName());
		
		//taking ss after test case fail
		try
		{
			String path=Utils.getScreenshot(result.getName());
			test.addScreenCaptureFromPath(path);
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "testcase skipped with name :- "+result.getName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
		
	}

	public void onStart(ITestContext context) {
		reportInit();//for extent report
		
	}

	public void onFinish(ITestContext context) {
		report.flush();//extent report save
		
	}

}
