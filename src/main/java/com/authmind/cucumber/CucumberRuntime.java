package com.authmind.cucumber;

import org.openqa.selenium.WebDriver;

public class CucumberRuntime {

	private WebDriver driver;

	public CucumberRuntime() {

	}

	public CucumberRuntime(WebDriver driver) {
		this.driver = driver;

	}

	private static ThreadLocal<CucumberRuntime> instance = new ThreadLocal<>();

	public static synchronized CucumberRuntime get() {
		return instance.get();
	}

	public static synchronized void set(WebDriver driver) {
		instance.set(new CucumberRuntime(driver));
	}

	public WebDriver getDriver() {

		return driver;
	}

	public void setDriver(WebDriver driver) {

		this.driver = driver;
	}

}
