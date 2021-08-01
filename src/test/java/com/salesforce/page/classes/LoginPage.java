package com.salesforce.page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.test.setup.classes.BaseMethods;
import com.salesforce.test.setup.classes.Constants;

public class LoginPage extends BaseMethods{
//	private WebDriver driver;
	
//	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
//		this.driver = driver;
//		this.js = js;
//		this.wait = wait;
//	}
	
	public LoginPage enterUsername() {
//		String username = Constants.User_Name;
		String username = "mercury.bootcamp@testleaf.com";
		try {
			WebElement usernameTextBox = driver.findElement(By.id("username"));
//			WebElement usernameTextBox = getDriver().findElement(By.id("username"));
			usernameTextBox.click();
			usernameTextBox.clear();
			usernameTextBox.sendKeys(username);
			reportStep("Username entered successfully", "pass");
		} catch (Exception e) {
			reportStep("Username NOT entered successfully", "fail");
		}
		return this;	
	}
	
	public LoginPage enterPassword() {
//		String password = Constants.Password;
		String password = "Bootcamp@123";
		try {
			WebElement passwordTextBox = driver.findElement(By.id("password"));
//			WebElement passwordTextBox = getDriver().findElement(By.id("password"));
			passwordTextBox.click();
			passwordTextBox.clear();
			passwordTextBox.sendKeys(password);
			reportStep("Password entered successfully", "pass");
		} catch (Exception e) {
			reportStep("Password NOT entered successfully", "fail");
		}
		return this;	
	}
	
	public HomePage clickLoginButton() throws InterruptedException {
		WebElement loginButton = driver.findElement(By.id("Login"));
//		WebElement loginButton = getDriver().findElement(By.id("Login"));
		loginButton.click();
//		Thread.sleep(1000);
		return new HomePage();
	}
}
