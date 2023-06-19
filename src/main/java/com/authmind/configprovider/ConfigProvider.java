package com.authmind.configprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigProvider {
	
	private static Properties properties;
	

	public static String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static String getAsString(String key) {
		return getInstance().getProperty(key);
	}

	private static Properties getInstance() {
		if(properties == null) {
		properties = loadProperties();
		return properties;
	}else 
	{
		
		return properties;
	}
	
	}
	private static Properties loadProperties() {
		Properties props = new Properties();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			InputStream inputStream = loader.getClass().getResourceAsStream("/properties/Automation.properties");
			if (inputStream != null) {
				  properties.load(inputStream);
				}else {	
			props.load(new FileInputStream("C:\\workspace\\BDD\\AuthMind\\Cucumber-Selenium-Adaptor\\src\\test\\resources\\properties\\Automation.properties"));
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return props;	
		
	}

	public static long getAsInt(String key) {
		return Integer.parseInt(getInstance().getProperty(key));
	}

}
