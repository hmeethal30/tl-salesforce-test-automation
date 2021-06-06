package com.salesforce;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditDashBoardTest {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		
		// set up
		WebDriverManager.chromedriver().setup();
		
		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();
	
		// Set the notification setting to overwrite the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);
	
		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();
		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		//Create driver and js executor instances
		driver.manage().window().maximize();
	
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
				
//		START TEST CASE HERE
		//	1. Login to https://login.salesforce.com
		// 1.1 Launch URL
		driver.get("https://login.salesforce.com");
		
		// 1.2: Locate user id and enter value
		WebElement txtUsername = driver.findElementById("username");
		txtUsername.sendKeys("mercury.bootcamp@testleaf.com");

		// 1.3: Locate password field and enter value
		WebElement txtPassword = driver.findElementById("password");
		txtPassword.sendKeys("Bootcamp@123");

		// 1.4: Locate Login button and click on it
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();

		//	2. Click on the toggle menu button from the left corner
		// 2.1: Locate ad click on toggle menu
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElementByClassName("slds-icon-waffle");
		iconToggle.click();
		
		//	3. Click View All and click Dashboards from App Launcher
		// 3.1: Locate ad click view all
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();

		// 3.2: Locate ad click Dashboards
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
		String locator_editLinkArrow = "(//a[@title='Salesforce Automation by Haseena']/ancestor::th/following-sibling::td[last()]/following::button)[1]";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_editLinkArrow)));
		driver.findElementByXPath(locator_editLinkArrow).click();

		
		//Wait for Edit button in the actions drop down
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Edit'])[1]")));
		driver.findElementByXPath("(//span[text()='Edit'])[1]").click();
//				Thread.sleep(2000);
		
		// Switch to new iframe
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//iframe[@title='dashboard']")));
		driver.switchTo().frame(driver.findElementByXPath("//iframe[@title='dashboard']"));
		
		String locator_edtDshBrdName = "//span[text()='Edit Dashboard name']/parent::button";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator_edtDshBrdName)));
		driver.findElementByXPath(locator_edtDshBrdName).click();
		driver.findElementByXPath("//input[@id='edit-dashboard-title']").click();
		
		driver.findElementByXPath("//input[@id='edit-dashboard-title']").sendKeys(Keys.BACK_SPACE);
		driver.findElementByXPath("//input[@id='edit-dashboard-title']").sendKeys("Salesforce");
		driver.findElementByXPath("//button[text()='Save']").click();	

	}

}
