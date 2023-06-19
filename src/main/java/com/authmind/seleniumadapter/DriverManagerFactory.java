package com.authmind.seleniumadapter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManagerFactory {
	
	private static final String DRIVER_DIR = "src/test/resources/";
	
	
	public static DriverManager getManager(String browserName) {
		
		DriverManager driverManager = null;
		
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
			driverManager  = new ChromeDriverManager();
			
		}
		
		return driverManager;
		
	}	
	

    public static WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return getChromeDriver();
            case "firefox":
                return getFirefoxDriver();
            case "internet explorer":
                return getInternetExplorerDriver();
            default:
                throw new IllegalArgumentException("Match case not found for browser: " 
                        + browserName);
        }
    }

    private static WebDriver getChromeDriver() {
//      System.setProperty("webdriver.chrome.driver", DRIVER_DIR + "chromedriver.exe");
    	 ChromeOptions options = new ChromeOptions();
         options.addArguments("--start-maximized");
         WebDriverManager.chromedriver().setup();
         return new ChromeDriver(options);
  }

  private static WebDriver getFirefoxDriver() {
//      System.setProperty("webdriver.gecko.driver", DRIVER_DIR + "geckodriver.exe");
      WebDriverManager.firefoxdriver().setup();
      return new FirefoxDriver();
  }

  private static WebDriver getInternetExplorerDriver() {
//      System.setProperty("webdriver.ie.driver", DRIVER_DIR + "IEDriverServer.exe");
      WebDriverManager.iedriver().setup();
      return new InternetExplorerDriver();
  }
}


