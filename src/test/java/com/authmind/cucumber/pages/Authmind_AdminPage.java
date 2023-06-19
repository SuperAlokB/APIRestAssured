package com.authmind.cucumber.pages;

import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.authmind.configprovider.ConfigProvider;
import com.authmind.utilities.BasePageObject;
import com.authmind.utilities.Screenshots;

public class Authmind_AdminPage extends BasePageObject {

	private WebDriver driver;
	WebDriverWait wait;
	String userNameXpath = "//input[@placeholder='Enter your Username/Email']";
	String passwordXpath = "//input[@placeholder='Enter your password']";
	String loginButtonXpath = "//button[contains(text(),'Login')]";
	String nextButtonXPath = "//button[contains(text(),'Next')]";	
	// Create New User	
	String TabAdmin = "//*[@id=\"root\"]/div[1]/div/div[2]/ul/li[6]/a";
	String addUser = "//button[contains(text(),'Add User')]";
	String newUserName = "//input[@placeholder='Enter your name']";
	String newUserEmail = "//input[@placeholder='Enter your email']";
	String newUserPassword = "//input[@placeholder='Enter password']";
	String newUserConfirmPassword="//input[@placeholder='Confirm your password']";
	String newUserRole = "//select[@class='form_drodown']";
	String newUserSave = "//button[contains(text(),'Save')]";	
			
	
	//Add new Application 
	String addAplicataionButton = "//button[contains(text(),'Add Known Application')]";
	String applicationNameTxt = "//input[@placeholder='Enter known application Name']";;
	String ipAddressTxt = "//input[@placeholder='Add IP address, etc.']";;
	String addNewAppSaveButton = "//button[contains(text(),'Save')]";
	
	
	

	// If chrome version is changed - download it from 
	//https://chromedriver.chromium.org/downloads
	
	public Authmind_AdminPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 90);

	}

	public void navigateToAdminPage() {

		driver.get(System.getProperty("url", ConfigProvider.getAsString("applicationUrl")));
		// Screenshots.addStepInReport(" Application opened ");
		Screenshots.stepWithScreenShots(driver, "My Second step with screen shots");
	}
	public void enterLoginDetails(String userName , String userPassword) {
		WebElement userNameElement = driver.findElement(By.xpath(userNameXpath));
		
		sendKeys(driver, userNameElement, 10, userName);
		clickElement(nextButtonXPath, "Next Button clicked ");
		try {
			Thread.sleep(500);
		
				
		WebElement passwordelement = driver.findElement(By.xpath(passwordXpath));
		sendKeys(driver, passwordelement, 10, userPassword);
		clickElement(loginButtonXpath, "Login  Button clicked ");	
		Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//waitTillLoggedUserTosterDisapper();
		
	}
	
	public void createNewUser(String user,String UserEmail,String Password,String Role) {
		
	   String tabName = "Admin";
		wait = new WebDriverWait(driver, 90);
		try {
			clickOnTab(tabName,20);
			driver.findElement(By.xpath(addUser)).click();
			WebElement newUserElement = driver.findElement(By.xpath(newUserName));
			sendKeys(driver, newUserElement, 10, user);
			WebElement userEmailElement = driver.findElement(By.xpath(newUserEmail));
			sendKeys(driver, userEmailElement, 10, UserEmail);
			
			
			WebElement passwordelement = driver.findElement(By.xpath(newUserPassword));
			sendKeys(driver, passwordelement, 10, Password);
			
			passwordelement = driver.findElement(By.xpath(newUserConfirmPassword));
			sendKeys(driver, passwordelement, 10, Password);
			
			Select role =  new Select(driver.findElement(By.xpath(newUserRole)));
			role.selectByVisibleText(Role);
			
			clickElement(newUserSave, "Save  Button clicked ");	
			waitTillLoggedUserTosterDisapper();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void verifyUser(String userEmailName) {
		
		String tabName = "Admin";
		String sidePanelObjectPath = "//a[@href='/users']";
		//clickOnAdminSidePanelOptions(sidePanelObjectPath);
		
		String xpathUserEmail = "//*[text()='" + userEmailName + "']";
		wait = new WebDriverWait(driver, 90);
		try {
			clickOnTab(tabName,20);
			if(driver.findElement(By.xpath(xpathUserEmail)).isDisplayed()) {
				Screenshots.addStepInReport(userEmailName + " : User created in System");
			}else {
				Screenshots.addStepInReport(userEmailName + " : User not found ");
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}
	
	public void logoutOfApplication() {
		String xPathlogoutOption =  "//a[@href='/logout']";
		String  mouseHoverXPath = "//*[@class='profile_name']";
		Actions actions = new Actions(driver);
		
		WebElement menuOption = driver.findElement(By.xpath(mouseHoverXPath));
		actions.moveToElement(menuOption).perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickElement(xPathlogoutOption, "Select Logout option ");
		Screenshots.addStepInReport(" : User logged out ");
		
		
	}
	
	public void addNewApplication() {
		
		
		 String tabName = "Admin";
		 String xPathlogoutOption =  "//a[@href='/known_applications']";		 
				 
		 wait = new WebDriverWait(driver, 90);
			try {
				clickOnTab(tabName,20);
				clickElement(xPathlogoutOption, "Select Add New Known Application ");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
			
			
			public void createNewKnowApplication(String applicationName,String ip_hostName) {
				 try {
						
			clickElement(addAplicataionButton, "Add Know Applicstion Button clicked ");
			WebElement applicationNameelement = null;
			try {
				applicationNameelement = driver.findElement(By.xpath(applicationNameTxt));
				if(applicationNameelement.isDisplayed()) {
					sendKeys(driver, applicationNameelement, 10, applicationName);	
					Screenshots.addStepInReport("Entered Applicaiton name ");
					
				}else {
					
						Screenshots.stepFailWithScreenShots(driver, "Not able to get know applicataion form");	
					
				}
			}catch(Exception ex) {
				
				
			}
			
			
			WebElement ipAddressHostNameelement = driver.findElement(By.xpath(ipAddressTxt));
			
			sendKeys(driver, ipAddressHostNameelement, 10, ip_hostName);
			
			 Actions act = new Actions(driver);
			 act.sendKeys(Keys.TAB).build().perform();
			 act.sendKeys(Keys.RETURN).build().perform();			
			clickButton(addNewAppSaveButton, "New application saved ");		
			waitTillLoggedUserTosterDisapper();	
		
			 } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
			}
			
	public void verifyKnowApplicationName(String applicationName) {
		
		String tabName = "Admin";
		String sidePanelObjectPath = "//a[@href='/users']";
		//clickOnAdminSidePanelOptions(sidePanelObjectPath);
		driver.navigate().refresh();
		String xPathlogoutOption =  "//a[@href='/known_applications']";
		
		String xpathUserEmail = "//*[text()='" + applicationName + "']";
		wait = new WebDriverWait(driver, 90);
		try {
			clickOnTab(tabName,20);
			clickElement(xPathlogoutOption, "Select Add New Known Application ");
			if(driver.findElement(By.xpath(xpathUserEmail)).isDisplayed()) {
				Screenshots.addStepInReport(applicationName + " : User created in System");
			}else {
				Screenshots.addStepInReport(applicationName + " : User not found ");
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void clickOnTab(String tabName) {
		
		try {
			clickOnTab(tabName,20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}