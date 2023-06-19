package com.authmind.seleniumadapter;

import com.authmind.configprovider.ConfigProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverExecutables {

	
	private static String chromeDriverExe = System.getProperty("browser.chromeExe.path",ConfigProvider.getAsString("browser.chromeExe.path"));
	private static String iDriverExe = "./drivers/IEDriverServer_%sbit_3.12.exe";
	private static String chromeVersion = System.getProperty("chrome.version",ConfigProvider.getAsString("chrome.version"));
	private static String proxyURL = System.getProperty("proxyurl",ConfigProvider.getAsString("proxyurl"));
	
	
	
	protected static String getChromeDriverExe() {
		
		String chromeExePath = System.getProperty("user.dir");
		chromeExePath = chromeExePath + chromeDriverExe ;
		return chromeExePath;
	}
	
	
protected static void setChromeDriverExe(int version) {
		
		chromeDriverExe=String.format(chromeDriverExe, version);
	}
	
protected static String getIeExe() {
		
		return iDriverExe;
	}


protected static void setBrowserExe() {
	
	
	String jdkVersion = System.getProperty("sun.arch.data.model");
	String browserName = System.getProperty("browser",ConfigProvider.getAsString("browser"));
	if(browserName.equalsIgnoreCase("chrome")) {
		
		if(!chromeVersion.isEmpty()) {
			
			WebDriverManager.chromedriver().version(chromeVersion).proxy(proxyURL).setup();			
			//WebDriverManager.chromedriver().avoidOutputTree();
			
		}else {
			
			WebDriverManager.chromedriver().proxy(proxyURL).setup();
		}
	}
	
	
}
	
}
