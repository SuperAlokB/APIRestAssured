package com.authmind.extendReports;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtendTestManager {

	private ExtendTestManager() {
	}

	private static int counter = 0;
	private static Map<Integer, ExtentTest> extendTestMap = new HashMap<>();
	private static ExtentReports extend;

	public static synchronized ExtentTest getTest() {

		return extendTestMap.get(getCurrentThread());

	}

	public static synchronized void endTest() {

		if (!extendTestMap.isEmpty()) {

			extend.removeTest(extendTestMap.get(getCurrentThread()));
		}
	}

	public static synchronized ExtentTest startTest(String testName, final String desc) {

		extend = ExtendConfiguration.getInstance();
		ExtentTest test = extend.createTest(testName + "_" + counter, desc);
		extendTestMap.put(getCurrentThread(), test);
		return test;

	}

	private static synchronized int getCurrentThread() {
		return (int) (Thread.currentThread().getId());
	}

}
