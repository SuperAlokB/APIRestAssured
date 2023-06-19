package com.authmind.listenrs;

import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.authmind.extendReports.ExtendConfiguration;
import com.authmind.extendReports.ExtendTestManager;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	
	
private static Logger logger = Logger.getLogger(TestListener.class);

private Map<String,String> allParameters = new HashMap<String, String>();
private Map<String,String> suiteParameters = new HashMap<String, String>();
private Map<String,String> localParameters = new HashMap<String, String>();
	


private static String getTestMethodName(ITestResult iTestResult) {
	return iTestResult.getMethod().getConstructorOrMethod().getName();
	
}
	
	@Override
    public void onTestStart(ITestResult iTestResult) {
		ExtendTestManager.startTest(iTestResult.getParameters()[0].toString().replaceAll("\"", ""), iTestResult.getParameters()[1].toString().replaceAll("\"", ""));
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info(iTestResult.getName() + " Passed Succeddfully !");
		
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.warn(iTestResult.getName() + " Test Step Failed !");
		if(ExtendTestManager.getTest() != null) {
			ExtendTestManager.getTest().log(Status.FAIL, "Test step Failed :" + iTestResult.getThrowable());
		}
		
	}
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		if(ExtendTestManager.getTest() != null) {
			ExtendTestManager.getTest().log(Status.SKIP, "Test step got skipped :" + iTestResult.getName());
		}
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.info("");
		
	}

	@Override
	public void onStart(ITestContext iTestcontext) {
		allParameters = iTestcontext.getSuite().getXmlSuite().getAllParameters();
		suiteParameters = iTestcontext.getSuite().getXmlSuite().getParameters();
		localParameters = iTestcontext.getCurrentXmlTest().getLocalParameters();
		
	}

	@Override
	public void onFinish(ITestContext iTestcontext) {
		
		
		ExtendConfiguration.getInstance().flush();
		ExtendTestManager.endTest();
				
	}
	
	

}
