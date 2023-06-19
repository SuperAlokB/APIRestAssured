package com.authmind.cucumber.stepdefinition;

import java.io.IOException;

import com.authmind.excel.GetdatafromExcel;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SmokeTest_StepDefinition extends AbstractSteps{
	
	int scanarioExecutionCount = 0;
	
	
	@Given("get excel Data {string} with sheetName {string}")
	public void get_excel_Data_with_sheetName(String excelFileName, String dataSheetName) {
		
		readExcelFile(excelFileName, dataSheetName);
				
		try {
			
		String playBookName = 	GetdatafromExcel.get(scenario.getName(), "UserName");
		System.out.print( " : " + playBookName);
	    System.out.print("*****Got Excel Data **************** ");
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

	@When("user clicks on {string} and verify page details")
	public void user_cliks_on(String tabName) {
	    //User clicks on tab
		  System.out.print("*****Tab Clicked **************** ");		
		//Verify Tab clicked		  
		  System.out.print("*****Tab Clicked verified**************** ");		
		//Verify pages 		
		  System.out.print("*****Page verified on Tab**************** ");
	}
}



