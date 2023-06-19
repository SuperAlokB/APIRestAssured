package com.authmind.seleniumadapter;

import java.io.File;
import java.util.logging.Level;

//import org.apache.logging.log4j.Level;
import org.openqa.selenium.logging.LogLevelMapping;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.log4testng.Logger;

import com.authmind.configprovider.ConfigProvider;

public class ChromeDriverManager extends DriverManager {

	private ChromeDriverService chService;
	private static Logger logger = Logger.getLogger(ChromeDriverManager.class);
	private static String chromeVersion = System.getProperty("chrome.version",ConfigProvider.getAsString("chrome.version"));
	
	@Override
	protected void startService() {
		String driverExePath = DriverExecutables.getChromeDriverExe();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			
			File file = new File(loader.getResource(driverExePath).getFile());
			chService = new ChromeDriverService.Builder().usingDriverExecutable(file).usingAnyFreePort().build();
		}catch(Exception ex) {
			
			logger.warn("Chrome Driver not found using default exe");
		}
try {
			
			chService.start();
			
		}catch(Exception ex) {
			
			logger.warn("Chrome service can not star,t ");
		}
		
	}

	@Override
	protected void createDriver() {		
		String remoteWebDriver =System.getProperty("remoteWebDriver",ConfigProvider.getAsString("remoteWebDriver"));
		ChromeOptions options = new ChromeOptions();
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.BROWSER, Level.OFF);
		logPrefs.enable(LogType.CLIENT, Level.OFF);
		logPrefs.enable(LogType.DRIVER, Level.OFF);
		logPrefs.enable(LogType.PERFORMANCE, Level.OFF);
		logPrefs.enable(LogType.PROFILER, Level.OFF);
		options.setCapability(CapabilityType.VERSION, chromeVersion);
		options.setCapability(CapabilityType.LOGGING_PREFS,logPrefs);
		options.setCapability("goog:loggingPrefs", logPrefs);	
		options.setCapability("-enable-logging", "--log-level=3");
		
		//java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel();
		
		if(remoteWebDriver.equalsIgnoreCase("remoteWebDriver")) {
		
		driver = new RemoteWebDriver(getServerURL(),options);
		}else {
			String driverExePath = DriverExecutables.getChromeDriverExe();
			System.setProperty("webdriver.chrome.driver", driverExePath);
		    File chromeDriver  = new File(driverExePath);
			  if (!chromeDriver.exists()) {
			    throw new RuntimeException("chromedriver could not be located!");
			  }

			  ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
			      .withVerbose(true)
			      .usingAnyFreePort()
			      .usingDriverExecutable(chromeDriver)					     
			      .build();

			  driver = new ChromeDriver(chromeDriverService);
			
		}
		
	}

}
