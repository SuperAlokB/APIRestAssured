package com.authmind.utilities;

import java.sql.Time;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.Assertion;

import com.authmind.configprovider.ConfigProvider;

public class BasePageObject {

	protected WebDriver driver;
	private FluentWait<WebDriver> fluentWait;
	private Duration fluentWaitDuration = Duration.ofSeconds(ConfigProvider.getAsInt("FLUENT_WAIT"));
	private Duration pollingInternal = Duration.ofSeconds(ConfigProvider.getAsInt("POLLING_INTERVAL"));
	private final JavascriptExecutor javascriptExecutor;
	protected Actions action;
	private static final int DEFAULT_IMPLICIT_WAIT = 0;
	
	
	
	public BasePageObject(WebDriver driver) {
		
		this.driver = driver;
		fluentWait = new FluentWait<>(driver).withTimeout(fluentWaitDuration).pollingEvery(pollingInternal).ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class).ignoring(NoSuchElementException.class);
		javascriptExecutor = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		new AssertionLibrary(driver);
		
		
	}
	
	protected void setImplicitWait(int duration) {
		driver.manage().timeouts().implicitlyWait(duration, TimeUnit.SECONDS);
		
	}
	
	protected void clickElement(final String locator,String description) {
		getElement(locator).click();
		Screenshots.stepWithScreenShots(driver, description);
		
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
		
	}
	
	
	protected boolean isElementOnPage(final String locator) {
		
		setImplicitWait(DEFAULT_IMPLICIT_WAIT);
		boolean flag = !getElements(locator).isEmpty();		
		return flag;
		
	}
	
	
	protected void setInputValue(final String locator,final String value) {
		
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		Screenshots.stepWithScreenShots(driver, "Setting up the value : " + value);
		
		
	}
	
	
	protected WebElement getElement (final String locator) {	
		
		return fluentWait.until(new ExpectedCondition<WebElement>(){
			
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(locator));
			}
		});
	}
	
	
	protected List<WebElement> getElements (final By by) {	
		
		return fluentWait.until(new ExpectedCondition<List <WebElement>>(){
			
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(by);
			}
		});
	}
	
protected List<WebElement> getElements (final String locator) {	
		
		return fluentWait.until(new ExpectedCondition<List <WebElement>>(){
			
			@Override
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(By.xpath(locator));
			}
		});
	}
	

protected void sendKeys(WebDriver driver, WebElement element,
int timeout, String value){  
new WebDriverWait(driver,   
timeout).until(ExpectedConditions.visibilityOf(element));  
element.sendKeys(value);  
}  
protected void clickOn(WebDriver driver, WebElement element,   
int timeout)  
{  
new WebDriverWait(driver,   
timeout).until(ExpectedConditions.elementToBeClickable(element));  
element.click();  
}  

public boolean hasClass(WebElement element,String active,String className) {
    String classes = element.getAttribute("class");
    for (String c : classes.split(" ")) {
        if (c.equals(className) &&  element.getAttribute("class").contains(active)) {
            return true;
        }
    }
    
    return false;
}


public void clickOnTab(String TabName,int timeout) throws InterruptedException {
	try {
	
		String tosterSearch = "//div[contains(@class,'react-toast-notifications') and contains(text(),'successfully')]";
		WebElement toster = driver.findElement(By.xpath(tosterSearch));	
		if(toster != null) {
			
			Thread.sleep(7000);
		}
		
	}catch(Exception ex) {
	
		Thread.sleep(6000);
		
	}
	
	//String tabNameXpath = "//a[text()='" + TabName + "']";
	String tabNameXpath = "//a[contains(text(),'" + TabName + "')]";
	WebElement tabname = driver.findElement(By.xpath(tabNameXpath));	
	//new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(tabname));  
	tabname.click();  	
		
}

//Logged User Toster Disapper 
public void waitTillLoggedUserTosterDisapper() {
//<div class="react-toast-notifications__container css-19n335a-ToastContainer"></div>
	//You have logged in successfully.
	
	String tosterSearch = "//div[contains(@class,'react-toast-notifications') and contains(text(),'successfully')]";
	WebElement toster = driver.findElement(By.xpath(tosterSearch));	
	new WebDriverWait(driver,500).until(ExpectedConditions.invisibilityOf(toster)); 
	try {
		if(toster.isDisplayed())
		{
			Thread.sleep(6000);
		}
	
	} catch (StaleElementReferenceException | InterruptedException e) {
		// TODO Auto-generated catch block
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}




public void clickButton(String locator,String description) {
	
	
	WebElement button = driver.findElement(By.xpath(locator));
	
	if(button.isDisplayed()) {
		
		button.click();
		Screenshots.stepWithScreenShots(driver, description);
	}else {
		
		Screenshots.addStepInReport("Not able to click Button");
	}
	
}

public void clickOnAdminSidePanelOptions(String sidePanelObjectPath) {
	
	
	WebElement sidePanelObject = driver.findElement(By.xpath(sidePanelObjectPath));
	
	
if(sidePanelObject.isDisplayed()) {
		
	sidePanelObject.click();
		Screenshots.stepWithScreenShots(driver, "clicked on Side panel Option");
	}else {
		
		Screenshots.addStepInReport("Not able to click side panel Option");
	}
	
}
	
}
