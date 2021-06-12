package com.salesforce.assignments;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment1 {

	public static void main(String[] args) throws InterruptedException {
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;

		
		//Create driver and js executor instances
		driver.manage().window().maximize();
	
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		
//		START TEST CASE HERE
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
		
		//	3. Click view All 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();
		
		// 4. Click Service Console from App Launcher
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Service Console']")));
		driver.findElementByXPath("//p[text()='Service Console']").click();
		
		//	5. Select Home from the DropDown
		WebElement elemNavMenu = driver.findElementByXPath("//button[@title='Show Navigation Menu']");
		elemNavMenu.click();
		driver.findElementByXPath("//span[text()='Home']").click();
		
		//	6. Add CLOSED + OPEN values and result should set as the GOAL (If the result is less than 10000 then set the goal as 10000)
		String xpathClosedValue = "//span[text()='Closed' and @class='metricLabel']//following-sibling::span";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathClosedValue)));
		Thread.sleep(2000);
		WebElement closedValue = driver.findElementByXPath(xpathClosedValue);
		int intClosedValue = Integer.parseInt(closedValue.getText().substring(1));
		System.out.println("Closed value is: " + intClosedValue);
		
		String xpathOpenValue = "//span[text()='Open (>70%)']/following-sibling::span";
		WebElement openValue = driver.findElementByXPath(xpathOpenValue);

		int intOpenValue = Integer.parseInt(openValue.getText().substring(1));
		System.out.println("Open value is: " + intOpenValue);
		int currentTotal = intClosedValue + intOpenValue;
		System.out.println("Current Total is: " + currentTotal);
		
		if (currentTotal< 10000) {
			driver.findElementByXPath("//button[@title='Edit Goal']").click();
			WebElement inptEditGoal = driver.findElementByXPath("//span[@id='currencyCode']/following-sibling::input");
			inptEditGoal.click();
			inptEditGoal.clear();
			inptEditGoal.sendKeys("10000");
			driver.findElementByXPath("//span[text()='Save']/parent::button").click();
//			if (driver.findElementByXPath("//span[text()='Close this window']/parent::button").isDisplayed()){
//				driver.findElementByXPath("//span[text()='Close this window']/parent::button").click();
//			}
		} else {
			System.out.println("Current goal is not less than 10000");
		}
		
		//	7. Select Dashboards from DropDown
		elemNavMenu.click();
		driver.findElementByXPath("//span[text()='Dashboards']").click();

		//		8. Click on New Dashboard
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New Dashboard']")));
		driver.findElementByXPath("//div[@title='New Dashboard']").click();
		//  Wait for 'dashboard' iframme to appear and then switch to it
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//iframe[@title='dashboard']")));

//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.switchTo().frame(driver.findElementByXPath("//iframe[@title='dashboard']"));

		
		//	9. Enter the Dashboard name as "YourName_Workout"
		String strExpectedDshBrdName = "Haseena_Workout";
		WebElement txtDshBrdName = driver.findElementByXPath("//input[@Id='dashboardNameInput']");
		txtDshBrdName.click();
		txtDshBrdName.sendKeys(strExpectedDshBrdName); //strExpectedDshBrdName
		
		//	10. Enter Description as Testing and Click on Create
		WebElement txtDshBrdDesc = driver.findElementByXPath("//input[@Id='dashboardDescriptionInput']");

		txtDshBrdDesc.click();
		txtDshBrdDesc.sendKeys("Testing");
		driver.findElementByXPath("//button[@Id='submitBtn']").click();
		
		//	11. Click on Done
		driver.findElementByXPath("//button[text()='Done']").click();
		
		//	12. Verify the Dashboard is Created
		String savedDashboardName = driver.findElementByXPath("//span[text()='Dashboard']/following-sibling::span").getText();
		if (strExpectedDshBrdName.equalsIgnoreCase(savedDashboardName)) {
			System.out.println("Dashboard created as expected");
		}	else {
			System.out.println("Dashboard creation failed");
		}
		
		//	13. Click on Subscribe
		driver.findElementByXPath("//button[text()='Subscribe']").click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Subscribe']")));
		driver.findElementByXPath("//span[text()='Subscribe']").click();
		Thread.sleep(3000);
		
		// 14. Select Frequency as "Daily"
 	   driver.findElementByXPath("//span[text()='Daily']").click();
 	   
 	   // 15. Time as 10:00 AM
 	   Select times = new Select(driver.findElementByXPath("//span[text()='Time']/../following-sibling::select"));
 	   times.selectByVisibleText("10:00 AM");
 	   
 	   //	16. Click on Save
 	   driver.findElementByXPath("//span[text()='Save']").click();

 	   // 17. Verify "You started Dashboard Subscription" message displayed or not
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
 	   
 	 //	18. Close the "YourName_Workout" tab
 	  driver.findElementByXPath("//button[@title='Close H_Workout']").click();
		
//		19. Click on Private Dashboards
//	    driver.switchTo().defaultContent();
//	    driver.findElementByXPath("")
// 	  	//Private Dashboards
//		20. Verify the newly created Dashboard available
//		21. Click on dropdown for the item
//		22. Select Delete
//		23. Confirm the Delete
//		24. Verify the item is not available under Private Dashboard folder

	}

}
