package com.authmind.utilities;

import org.openqa.selenium.WebDriver;

public class AssertionLibrary {
	
	private static WebDriver driver;
	
	public enum Screenshot{
		
		REQUIRED , NOT_REQUIRED;
	}
	
	public AssertionLibrary(WebDriver driver) {
		
		AssertionLibrary.driver = driver;
	}

	public static void assertEquals(Object actual,Object expected,String message) {
		
		assertEquals(actual,expected,message,Screenshot.REQUIRED);
	}

	private static void assertEquals(Object actual, Object expected, String message, Screenshot required) {
		// TODO Auto-generated method stub
		
	}
}
