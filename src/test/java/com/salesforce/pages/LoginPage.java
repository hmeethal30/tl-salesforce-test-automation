package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	private String strUsername = "mercury.bootcamp@testleaf.com";
	private String strPassword = "Bootcamp@123";
	
	//Page URL
	private static String LOGIN_PAGE_URL = "https://login.salesforce.com";
	
	//Locators
	
	//USername input box
	@FindBy(how=How.ID,using = "username") private WebElement iptUsername;
	
	//Password input box
	@FindBy(how=How.ID,using = "password") private WebElement iptPassword;

	//Login button
	@FindBy(how=How.ID,using = "Login") private WebElement btnLogin;
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		driver.get(LOGIN_PAGE_URL);
		//Initialise elements
		PageFactory.initElements(driver, this);	
	}
	
	public void typeUsername() {
		iptUsername.click();
		iptUsername.sendKeys(strUsername);
	}
	
	public void typePassword() {
		iptPassword.click();
		iptPassword.sendKeys(strPassword);
	}
	
	public void clickLoginButton() {
		btnLogin.click();
	}

}
