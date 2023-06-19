@Baseline
Feature: BaseLine_TestVerification

Background:
	Given get excel Data "AMTestData" with sheetName "Baseline" to verify baseline
Scenario Outline: Verify baseline shadow access
	When user login into "QA" with "userName" and "userPassword"
	When user creats playbook "playBookName" shadow access from excel
    When user do the RDP to "machineName" with "userName" and "userPassword"
    Then user should get caught under playbook "playBookName"    
   Examples:
   |No_Of_Users|QA|userName|userPassword|
   |5|QA|userName|userPassword|