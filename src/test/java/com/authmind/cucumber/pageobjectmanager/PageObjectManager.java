package com.authmind.cucumber.pageobjectmanager;

import org.openqa.selenium.WebDriver;

import com.authmind.cucumber.pages.Authmind_AdminPage;
import com.authmind.cucumber.pages.PlayBooksPage;


public class PageObjectManager {

	
	private WebDriver driver;
	private Authmind_AdminPage adminPage;	
	private PlayBooksPage playbookPage;
	
	public PageObjectManager(WebDriver driver) {
		
		this.driver = driver;
	}
	

	public Authmind_AdminPage getAdminPage() {
		
		return (adminPage ==null)?adminPage = new Authmind_AdminPage(driver):adminPage;
	}
	
	public PlayBooksPage getPlaybookPage() {
		
	
		return (playbookPage ==null)?playbookPage = new PlayBooksPage(driver):playbookPage;
	}
	
	
}
