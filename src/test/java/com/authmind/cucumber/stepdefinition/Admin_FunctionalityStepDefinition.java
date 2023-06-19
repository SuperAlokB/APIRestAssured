package com.authmind.cucumber.stepdefinition;

import java.io.IOException;

import com.authmind.excel.GetdatafromExcel;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Admin_FunctionalityStepDefinition extends AbstractSteps{
	
	//Scenario scenario;	
	int scanarioExecutionCount = 0;	
	
	
	@Given("get excel Data <{string}> with sheetName <{string}> to craete users")
	public void get_excel_Data_with_sheetName_to_craete_users(String string, String string2) {
		//this.scenario = scenario;
		scanarioExecutionCount++;
		System.out.println("Scenario Count : " + scanarioExecutionCount);
				
		try {
			
		String scenarioName = 	data.get(scenario.getName(), "UserName");
		System.out.print( " : " + scenarioName);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}
	
	@When("user gets data from excel and add new user {string} {string} {string} and {string}")
	public void user_gets_data_from_excel_and_add_new_user_and(String string, String string2, String string3, String string4) {
		try {
	    // Write code here that turns the phrase above into concrete actions
			
			String user = data.get(scenario.getName(), "UserName");
			String userEmail = data.get(scenario.getName(), "Email");
			String password =data.get(scenario.getName(), "UserPassword") ;
			String role = data.get(scenario.getName(), "Role");
		    pageObjectManager.getAdminPage().createNewUser(user,userEmail,password,role);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

	@Then("user creats more users with excel row {int} dataset")
	public void user_creats_more_users_with_excel_row_dataset(Integer numberOfusers) {
	    System.out.println("Number of users :" + numberOfusers);
	    for(int i=1 ; i < numberOfusers ; i++ ) {
	    	
	    	String user;
			try {
			user = GetdatafromExcel.get(scenario.getName()+i, "UserName");
			
			String userEmail = data.get(scenario.getName()+i, "Email");
			String password =data.get(scenario.getName()+i, "UserPassword") ;
			String role = data.get(scenario.getName()+i, "Role");
		    pageObjectManager.getAdminPage().createNewUser(user,userEmail,password,role);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	}
	
	@Then("user {int} should be created in system.")
	public void user_should_be_created_in_system(Integer numberOfusers) {
		System.out.println("Number of users :" + numberOfusers);
	    for(int i=1 ; i < numberOfusers ; i++ ) {
	    	
	    	String user;
			try {
			user = GetdatafromExcel.get(scenario.getName()+i, "UserName");
			
			String userEmail = data.get(scenario.getName()+i, "Email");		
			pageObjectManager.getAdminPage().verifyUser(userEmail);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	@Then("user loggs out of the system")
	public void user_loggs_out_of_the_system(){
		
		pageObjectManager.getAdminPage().logoutOfApplication();
		
	}
	
	@When("user clicks on Add Know Application")
	public void user_clicks_on_Add_Know_Application() {
	    // Write code here that turns the phrase above into concrete actions
		pageObjectManager.getAdminPage().addNewApplication();
	}

	@Then("user provids new application details from excel row {int} dataset")
	public void user_provids_new_application_details_from_excel_row_dataset(Integer numberOfApplications) {
		   System.out.println("Number of Applications :" + numberOfApplications);
		    for(int i=1 ; i < numberOfApplications ; i++ ) {
		    	
		    	String appName;
				try {
				appName = GetdatafromExcel.get(scenario.getName()+i, "KnowApplicationName");
				String ipAddressHostName = data.get(scenario.getName()+i, "KnowApplicationIP");				
				
		        pageObjectManager.getAdminPage().createNewKnowApplication(appName,ipAddressHostName);
	   
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	}

	@Then("Verify {int} application should be created in system.")
	public void Verify_application_should_be_created_in_system(Integer applicationCount) {
		
		System.out.println("Number of users :" + applicationCount);
	    for(int i=1 ; i < applicationCount ; i++ ) {
	    	
	    	String user;
			try {
			user = GetdatafromExcel.get(scenario.getName()+i, "KnowApplicationName");
			
			String appName = data.get(scenario.getName()+i, "KnowApplicationName");		
			pageObjectManager.getAdminPage().verifyKnowApplicationName(appName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }

		
	}



}
