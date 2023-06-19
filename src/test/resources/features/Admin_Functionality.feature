@UI_success
Feature: Admin_FunctionalityVerification	

Background:
	Given get excel Data <"ExcelTest"> with sheetName <"AdminUser"> to craete users 	
Scenario Outline: Create new users in application
    When user gets data from excel and add new user "userName" "Email" "UserPassword" and "Role"
    Then user creats more users with excel row "No_Of_Users" dataset
    Then user "No_Of_Users" should be created in system.
    Then user loggs out of the system
   Examples:
   |No_Of_Users|
   |5|
   
Scenario Outline: Add new known Applications
    When user clicks on Add Know Application
    Then user provids new application details from excel row "No_Of_Applications" dataset
    Then Verify "No_Of_Applications" application should be created in system.
   Examples:
   |No_Of_Applications|
   |20|
   
Scenario Outline: Add new known Directory
    When user clicks on Add Know Application
    Then user provids new application details from excel row "No_Of_Applications" dataset
    Then Verify "No_Of_Applications" application should be created in system.
   Examples:
   |No_Of_Applications|
   |20|
   