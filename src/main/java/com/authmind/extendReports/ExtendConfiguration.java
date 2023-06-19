package com.authmind.extendReports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendConfiguration {

	
	private static ExtentReports extend;
	public static final String WORKING_DIR = System.getProperty("user.dir");
	public static final String TIME_STAMP = new SimpleDateFormat("dd.MM.yyyy.HH.mm").format(new Date());
	public static final String EXTEND_REPORTS_FOLDER = WORKING_DIR + "/AutomationReports";
	public static final String REPORT_NAME = "ExtendReport" + TIME_STAMP + ".html";
	public static final String EXTEND_REPORTS_PATH = EXTEND_REPORTS_FOLDER + File.separator + REPORT_NAME;
	private static Logger logger = Logger.getLogger(ExtendConfiguration.class.getName());
	
	private ExtendConfiguration() {}
	
public static ExtentReports getInstance() {
	
  if(extend == null ) {
	  createReportFolder();
	  attachReporters();
	   
  }
  return extend;
}

private static ExtentReports attachReporters() {
	// TODO Auto-generated method stub
	extend = new ExtentReports();
	extend.attachReporter(initHtmlReporter());
	return extend;
	
}

private static ExtentHtmlReporter initHtmlReporter() {
	
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(EXTEND_REPORTS_PATH);
	htmlReporter.config().setTheme(Theme.STANDARD);
	htmlReporter.config().setDocumentTitle(REPORT_NAME);
	htmlReporter.config().setEncoding("utf-8");
	htmlReporter.config().setReportName("AutomationExecution-Status");
	htmlReporter.config().setCSS("css-string");
	htmlReporter.config().setJS("js-String");
	htmlReporter.config().setProtocol(Protocol.HTTPS);
	htmlReporter.config().setTimeStampFormat("MM dd yyyy HH:mm:ss");
	return htmlReporter;
	
}

private static void createReportFolder() {
	File file = new File(EXTEND_REPORTS_FOLDER);
	if(!file.exists() && !file.mkdir()) {
		
		logger.warning("Failed To create Report Folder - Dir");
	}
	
	
}
}
