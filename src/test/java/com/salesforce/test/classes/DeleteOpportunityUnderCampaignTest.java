package com.salesforce.test.classes;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOpportunityUnderCampaignTest {

	public static void main(String[] args) {
		
		String strUsername = "mercury.bootcamp@testleaf.com";
		String strPassword = "Bootcamp@123";
		String LOGIN_PAGE_URL = "https://login.salesforce.com";
		
		
		// set up
		WebDriverManager.chromedriver().setup();
		
		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();
	
		// Set the notification setting to overwrite the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);
	
		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;

		
		//Create driver and js executor instances
		driver.manage().window().maximize();
	
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		
		//	1. log in to https://login.salesforce.com
		driver.get(LOGIN_PAGE_URL);
		WebElement inptUsername = driver.findElementById("username");
		inptUsername.sendKeys(strUsername);
		WebElement inptPassword = driver.findElementById("password");
		inptPassword.sendKeys(strPassword);
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();

		//	2. Click on the toggle menu button from the left corner
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElementByClassName("slds-icon-waffle");
		iconToggle.click();
		
		//	3. Click view All and click Sales from App Launcher
		// 3.1: Locate ad click view all
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();
		// 3.2: Locate ad click sales
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Sales']")));
		driver.findElementByXPath("//p[text()='Sales']").click();

		//	4. Click on Opportunities tab 
		WebElement elemOpportunities = driver.findElementByXPath("//span[text()='Opportunities']");
		jsExecutor.executeScript("arguments[0].click();", elemOpportunities);
		
		// 5. Click Bootcamp link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@Title=\"Bootcamp\"])[1]")));
		driver.findElementByXPath("(//a[@Title=\"Bootcamp\"])[1]").click();

		//	6. Click on the dropdown icon in the Opportunities
		
		
//		7. Select Delete and Confirm the delete
		driver.findElementByXPath("//span[text()='Delete']/parent::a").click();
		
//		8. Verify the Deleted Opportunity under selected campaign
//		9. Click on Opportunities Tab
//		10. Verify the deleted Opportunity "
//
//		Expected Result
//
//		1. Opportunity should not be available in Campaign as well as in Opportunities tab

	}

}
