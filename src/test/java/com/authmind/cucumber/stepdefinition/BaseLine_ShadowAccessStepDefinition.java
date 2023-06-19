package com.authmind.cucumber.stepdefinition;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import com.authmind.excel.GetdatafromExcel;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//netstat -ano | findstr :9999
//taskkill /F /pid 16724


public class BaseLine_ShadowAccessStepDefinition extends AbstractSteps{
	
	int scanarioExecutionCount = 0;
	
	
	@Given("get excel Data {string} with sheetName {string} to verify baseline")
	public void get_excel_Data_with_sheetName_to_verify_Baseline(String excelFileName, String dataSheetName) {
		
		readExcelFile(excelFileName, dataSheetName);
				
		try {
			
		String playBookName = 	GetdatafromExcel.get(scenario.getName(), "playBookName");
		System.out.print( " : " + playBookName);
		System.out.print("*********** Login into QA ");
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

	
	@When("user login into {string} with {string} and {string}")
	public void user_login_into_with_and(String env, String userName, String userPassword) {
		
			
		    pageObjectManager.getAdminPage().clickOnTab("Admin");
		    
			System.out.print("Logged in to Dashboard ");
			
	
	}
	
	
	@When("user creats playbook {string} shadow access from excel")
	public void user_creats_for_shadow_access_from_excel(String playBookName) {
	    // Write code here that turns the phrase above into concrete actions
		try {
			String shadowAccessPBName = 	data.get(scenario.getName(), "PlayBookName");
			String ruleTpe = 	data.get(scenario.getName(), "RuleType");
			
			//Create Shadow Access Playbook 
		    pageObjectManager.getPlaybookPage().createPlayBook(shadowAccessPBName,ruleTpe);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("user do the RDP to {string} with {string} and {string}")
	public void user_do_the_RDP_to_with_and(String string, String string2, String string3) {
		String remoteMachineIp = "10.59.37.84";
		String userName = "authqa\\alokc";
		String password = "Abcd123";
	    pageObjectManager.getPlaybookPage().loginToremoteMachine(remoteMachineIp,userName,password);
	}

	@Then("user should get caught under playbook {string}")
	public void user_should_get_caught_under_playbook(String string) {
	    // Write code here that turns the phrase above into concrete actions
	   // throw new cucumber.api.PendingException();
	}

}
