package com.authmind.cucumber.pages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.authmind.utilities.BasePageObject;

import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import java.awt.Robot;
import java.awt.event.*;    

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class PlayBooksPage extends BasePageObject {
	private WebDriver driver;
	WebDriverWait wait;
	String playBookTabName = "Playbooks";

	// Create New PlayBook
	// Add new Application
	String addPlaybookButton = "//*[@id=\"new_playbook\"]";
	String ruleTypexPath = "//*[@id=\"rule_type\"]";
	String nextButton = "//*[@id=\"new_playbook_tab2\"]";

	String playbookNamexPath = "//*[@id=\"fname\"]";
	String actionsDashboardVisibilityXPath = "//table//span[contains(text(),'Dashboard Visibility')]/..//input";
	//// table//span[contains(text(),'Dashboard Visibility')]/..//input

	String saveButton = "//div[contains(text(),'Save')]";
	String doneButton = "//div[contains(text(),'Done')]";

	String winRDPUserNameXpath = "User name:";

	public PlayBooksPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, 90);
	}

	public void createPlayBook(String playBookName, String ruleTypeName) {

		try {
			clickOnTab(playBookTabName, 60);
			clickElement(addPlaybookButton, "Add Playbook clicked");
			WebElement ruleTypeElement = driver.findElement(By.xpath(ruleTypexPath));

			// select from Rule Type
			Select ruleTypeOptions = new Select(ruleTypeElement);
			ruleTypeOptions.selectByVisibleText(ruleTypeName);

			// Click on Next
			clickElement(nextButton, "Clicking on next button - New Playbook");

			// Enter Playbook details
			WebElement playbookNameElement = driver.findElement(By.xpath(playbookNamexPath));
			sendKeys(driver, playbookNameElement, 20, playBookName);

			// select Action
			clickElement(actionsDashboardVisibilityXPath, "From Actions - selected options Dashboard Visibilty ");

			// Click on Save
			clickElement(saveButton, "New playbook saved ");

			// Click on done Element -
			try {
				WebElement doneElement = driver.findElement(By.xpath(doneButton));
				if (doneElement != null) {
					clickElement(doneButton, "Clicked on done element");
				}
			} catch (Exception ex) {

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loginToremoteMachine(String machineIp, String userName, String password) {

		DesktopOptions winOption = new DesktopOptions();
		winOption.setApplicationPath("C:\\Windows\\System32\\mstsc.exe -v \"10.59.37.59\"");
		WiniumDriver winDriver;
		WiniumDriverService service;
		int portNumber = 1234;
		try {

			String localPath = System.getProperty("user.dir");
			localPath = localPath + "\\src\\test\\resources\\drivers\\Winium.Desktop.Driver.exe";
			// File driverPath = new File("C:\\WiniumDriver\\Winium.Desktop.Driver.exe");
			File driverPath = new File(localPath);
			Process winDriverProcess = getExecutableOnPort("1234");
			service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999)
					.withVerbose(true).withSilent(false).buildDesktopService();
			// WiniumDriverService service = new
			// WiniumDriverService.Builder().usingDriverExecutable(driverPath).withVerbose(true).withSilent(false).buildDesktopService();
			DesktopOptions options = new DesktopOptions();
			options.setApplicationPath("C:\\Windows\\System32\\mstsc.exe");
			// options.setApplicationPath("C:\\Windows\\System32\\mstsc.exe");
			//

			//

			try {
				// winDriver = new WiniumDriver(new URL("http://localhost:1234"), options);
				service.start();
				winDriver = new WiniumDriver(service, options);
				winDriver.findElement(By.name("Show Options ")).click();
				Thread.sleep(300);

				// Get the window handle for username anc connections

				WebElement mainRemoteParentObject = winDriver.findElement(By.name("Remote Desktop Connection"));
				List<WebElement> generalListP = mainRemoteParentObject.findElements(By.name("General"));
				System.out.print("*********** General Tree count:  " + generalListP.size());
				generalListP.get(0).findElement(By.name("Computer:")).sendKeys(machineIp);
				generalListP.get(0).sendKeys(userName);
				Thread.sleep(600);
				
				 try{
				        Robot robot=new Robot();
				        robot.keyPress(KeyEvent.VK_ALT);
				        Thread.sleep(1000);
				        robot.keyPress(KeyEvent.VK_N);
				        robot.keyRelease(KeyEvent.VK_ALT);
				        robot.keyRelease(KeyEvent.VK_N);        
				        
				        Thread.sleep(600);
				        
				        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				        
				        StringSelection stringSelection = new StringSelection(password);
				        clipboard.setContents(stringSelection, null);
				        
				        robot.keyPress(KeyEvent.VK_CONTROL);
				        robot.keyPress(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_V);
				        robot.keyRelease(KeyEvent.VK_CONTROL);
				      //Simulate Enter key event
				      robot.keyPress(KeyEvent.VK_ENTER);
				      robot.keyRelease(KeyEvent.VK_ENTER);
						
				       Thread.sleep(1000); 
				      
				      
				        robot.keyPress(KeyEvent.VK_ALT);
				        Thread.sleep(1000);
				        robot.keyPress(KeyEvent.VK_Y);
				        robot.keyRelease(KeyEvent.VK_ALT);
				        robot.keyRelease(KeyEvent.VK_Y);      
				        
				        Thread.sleep(5000);
				        robot.keyPress(KeyEvent.VK_WINDOWS);
				        robot.keyRelease(KeyEvent.VK_D);		        
						
						   
						 }
				    catch(Exception ex){
				        System.out.println(ex.getMessage());
				    }				 
				 
				
							
				winDriverProcess.destroyForcibly();
				service.stop();
				// System.out.print(winDriverProcess.isAlive());
			} catch (IOException e) {
				System.out.println("Exception while starting WINIUM service");
				e.printStackTrace();
			}
			// winDriver = new WiniumDriver(service,winOption);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Process getExecutableOnPort(String port) throws IOException {
		String localPath = System.getProperty("user.dir");
		localPath = localPath + "\\src\\test\\resources\\drivers\\Winium.Desktop.Driver.exe";
		return Runtime.getRuntime().exec(localPath + " --port " + port);
	}
}
