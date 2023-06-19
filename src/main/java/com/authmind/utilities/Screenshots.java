package com.authmind.utilities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.log4testng.Logger;

import com.authmind.extendReports.ExtendTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class Screenshots {
	
	private static final Logger logger = Logger.getLogger(Screenshots.class);
	private static final String SCREENSHOTS_FOLDER = "\\AutomationReports\\screenshots\\";
	private static String screenshotsFolderPath;
	private Screenshots() {
		
	}
	static {
			
			createDirectory();
			
	}
	
	private static void createDirectory() {
		final String WORKING_DIR = System.getProperty("user.dir");
		screenshotsFolderPath = WORKING_DIR + SCREENSHOTS_FOLDER;
		
		File file = new File(screenshotsFolderPath);
		if(!file.exists() && !file.mkdir()) {
			
			logger.warn("Failed to create screenshots directory");
		}
				
	}
	
	public static void stepWithScreenShots(WebDriver driver , String message) {
		ExtentTest extendTest = ExtendTestManager.getTest();
		if(extendTest != null) {
			String path = Screenshots.captureScreenShots(driver,"screenshots");
			try {
				extendTest.pass(message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}	
	}
	
	public static void stepFailWithScreenShots(WebDriver driver , String message) {
		ExtentTest extendTest = ExtendTestManager.getTest();
		if(extendTest != null) {
			extendTest.fail(message);			
			String path = Screenshots.captureScreenShots(driver,"screenshots");
			logger.warn("Failed");
			
			try {
				extendTest.pass(message,MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}	
	}
	
	
	public static void addStepInReport(String message) {
		
		ExtentTest extendTest = ExtendTestManager.getTest();
		extendTest.pass(message);
		
	}

	private static String captureScreenShots(WebDriver driver, String screenShotName) {
		
		String randomNumber = RandomStringUtils.randomNumeric(5);
		String destinationPath = screenshotsFolderPath + screenShotName + randomNumber + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
	try {
		BufferedImage image = ImageIO.read(srcFile);		
		SimpleDateFormat formatter = new SimpleDateFormat();
		Graphics g = image.getGraphics();
		g.setFont(g.getFont().deriveFont(18f));
		
		Date date = new Date(System.currentTimeMillis());
		g.drawString(formatter.format(date), image.getWidth()-250, image.getHeight()-20);
		g.dispose();
		ImageIO.write(image, "png", new File(destinationPath));
		
	}catch(Exception ex) {
		
		System.out.print(ex.getMessage());
	}
		
		return destinationPath.substring(destinationPath.indexOf("/")+ 1);		
	}
	
	

}
