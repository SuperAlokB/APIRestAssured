package com.authmind.cucumber.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class CommonStepDefinition extends AbstractSteps{
	
	
	@Before
	public void before(Scenario scenario) {
		//Read Excel File - 
		setScenario(scenario);			
		startDriver();
		//this.scenario = scenario;
		System.out.print(scenario.getName());
		// pageObjectManager.getHomePage().navigateToHomePage();
		   String userName = "qa@authmind.com";
		   String userPwd = "Authmind@2022";
		   String  email = "qa@authmind.com";
		   System.out.print(userName);
		   pageObjectManager.getAdminPage().navigateToAdminPage();	
		   pageObjectManager.getAdminPage().enterLoginDetails(email,userPwd);
		   
	}	
}
