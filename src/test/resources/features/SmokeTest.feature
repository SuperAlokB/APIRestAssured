@Smoke_Test
Feature: BuildVerification_SmokeTest	

Background:
	Given get excel Data "SmokeTestData" with sheetName "TestData"
Scenario Outline: verify all tabs are open
	When user login into "QA" with "userName" and "userPassword"
    When user clicks on "tabName" and verify page details
    Then user loggs out of the system
   Examples:
   |No_Of_Users|tabName|tabNamePage|
   |5|tabName|tabNamePage|
  
