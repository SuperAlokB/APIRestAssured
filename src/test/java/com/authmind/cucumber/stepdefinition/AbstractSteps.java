package com.authmind.cucumber.stepdefinition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.authmind.configprovider.ConfigProvider;
import com.authmind.cucumber.CucumberRuntime;
import com.authmind.cucumber.pageobjectmanager.PageObjectManager;
import com.authmind.excel.GetdatafromExcel;
import com.authmind.seleniumadapter.DriverManager;
import com.authmind.seleniumadapter.DriverManagerFactory;
import cucumber.api.Scenario;

public abstract class AbstractSteps {
	
	private static DriverManager driverManager;
	protected WebDriver driver;
	protected static PageObjectManager pageObjectManager;
		
	protected static GetdatafromExcel data;
	protected static Scenario scenario;
	
	static {
		driverManager = DriverManagerFactory.getManager(ConfigProvider.getAsString("browser"));
		
	     		
	}
	
	public void startDriver() {
		driver = driverManager.getDriver();
		driver.manage().window().maximize();
		
		System.setProperty("webdriver.chrome.silentOutput", "true");
		pageObjectManager = new PageObjectManager(driver);
		CucumberRuntime.set(driver);
		
	}
	public WebDriver getDriver() {
		return CucumberRuntime.get().getDriver();		
	}
	
	public void readExcelFile(String excelFileName,String dataSheetName) {
		
		
		data = new GetdatafromExcel(excelFileName,dataSheetName);
		
		
	}
	public void setScenario(Scenario scenario) {
		AbstractSteps.scenario = scenario;
	}
	
}
