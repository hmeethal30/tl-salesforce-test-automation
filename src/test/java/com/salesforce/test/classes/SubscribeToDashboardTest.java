package com.salesforce.test.classes;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SubscribeToDashboardTest {

	public static void main(String[] args) throws InterruptedException {
		// set up
		WebDriverManager.chromedriver().setup();
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setExperimentalOption("prefs", prefs);
		ChromeDriver driver= new ChromeDriver(browserOptions);
		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		
//		START TEST CASE HERE
		//	1. Login to https://login.salesforce.com
		driver.get("https://login.salesforce.com");
		WebElement txtUsername = driver.findElementById("username");
		txtUsername.sendKeys("mercury.bootcamp@testleaf.com");
		WebElement txtPassword = driver.findElementById("password");
		txtPassword.sendKeys("Bootcamp@123");
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();

		//	2. Click on the toggle menu button from the left corner
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElementByClassName("slds-icon-waffle");
		iconToggle.click();
		
		//	3. Click View All and click Dashboards from App Launcher
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Dashboards']")));
		WebElement linkDashboards = driver.findElementByXPath("//p[text()='Dashboards']");
		js.executeScript("arguments[0].scrollIntoView();", linkDashboards);
		linkDashboards.click();
		
		//Search for the dashboard to be edited
		String xpathForSearch = "//label[text()='Search recent dashboards...']/../div/input";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForSearch)));
		WebElement searchBox = driver.findElementByXPath(xpathForSearch);
		searchBox.click();
		searchBox.sendKeys("by Haseena");
		Thread.sleep(2000);
		//a[@title='Salesforce Automation by Haseena']/ancestor::table/tbody/tr[1]/td[last()]/
		String locator_editLinkArrow = "(//a[@title='Salesforce Automation by Haseena']/ancestor::th/following-sibling::td[last()]/following::button)[1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_editLinkArrow)));
		driver.findElementByXPath(locator_editLinkArrow).click();
		
		//Wait for subscribe button in the actions drop down
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Subscribe']")));
		driver.findElementByXPath("//span[text()='Subscribe']").click();
		Thread.sleep(3000);
		
		
 	   driver.findElementByXPath("//span[text()='Daily']").click();
 	   driver.findElementByXPath("//span[text()='Save']").click();
 	   Thread.sleep(2000);
 	  if(driver.findElementByXPath("//span[text()='You started a dashboard subscription.']").isDisplayed()){
 		   System.out.println("Subscription successful");
 	   }
 	  else if(driver.findElementByXPath("//span[text()='Your subscription is all set.']").isDisplayed()){
 		 System.out.println("Subscription successful");
 	  }  
 	  else {
 		 System.out.println("Subscription unsuccessful");
 	  }

	}

}
