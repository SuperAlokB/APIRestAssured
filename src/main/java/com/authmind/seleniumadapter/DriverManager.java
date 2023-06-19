package com.authmind.seleniumadapter;

import org.openqa.selenium.WebDriver;

import com.authmind.configprovider.ConfigProvider;

import io.github.bonigarcia.wdm.ChromeDriverManager;

import java.net.URL;
import java.util.logging.Logger;

public abstract class DriverManager {

	protected WebDriver driver;
	private static Logger logger = Logger.getLogger(ChromeDriverManager.class.getName());
	protected abstract void startService();
	protected abstract void createDriver();
	
	public WebDriver getDriver() {		
		
		return DriverManagerFactory.getWebDriver("chrome");	
		
	}
	
	URL getServerURL() {
		
		
	URL url = null;
	String urlString ="";
	try {
		
		urlString = System.getProperty("hub.url",ConfigProvider.getAsString("hub.url".trim()));
		url = new URL(urlString);
	}catch(Exception ex) {
		logger.warning("facing issues creating driver - hub url may be wrong");
	}
		return url;		
		
	}
	
}
