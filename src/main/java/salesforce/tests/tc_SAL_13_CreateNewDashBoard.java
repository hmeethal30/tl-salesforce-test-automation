package salesforce.tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class tc_SAL_13_CreateNewDashBoard {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String strExpectedDshBrdName = "Salesforce Automation by Haseena";

		// set up
		WebDriverManager.chromedriver().setup();
		
		//Code to handle web alerts
		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();

		// Set the notification setting to overwrite the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);

		// Create object of ChromeOption class
		ChromeOptions options = new ChromeOptions();

		// Set the experimental option
		options.setExperimentalOption("prefs", prefs);
		
		//Create driver and js executor instances
		ChromeDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		
		JavascriptExecutor js= (JavascriptExecutor) driver;
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
//		START TEST CASE HERE
		//	1. Login to https://login.salesforce.com
		// 1.1 Launch URL
		driver.get("https://login.salesforce.com");
		
		// 1.2: Locate user id and enter value
		WebElement txtUsername = driver.findElementById("username");
		txtUsername.sendKeys("mercury.bootcamp@testleaf.com");

		// 1.3: Locate password field and enter value
		WebElement txtPassword = driver.findElementById("password");
		txtPassword.sendKeys("Bootcamp$123");

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
		
		//	4. Click on the New Dashboard option
		// 4.1 Locate and click on 'New Dashboard'
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New Dashboard']")));
		driver.findElementByXPath("//div[@title='New Dashboard']").click();
		// 4.2 Wait for 'dashboard' iframme to appear and then switch to it
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//iframe[@title='dashboard']")));

//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		driver.switchTo().frame(driver.findElementByXPath("//iframe[@title='dashboard']"));

		
		//	5. Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
		WebElement txtDshBrdName = driver.findElementById("dashboardNameInput");
		txtDshBrdName.click();
		txtDshBrdName.sendKeys(strExpectedDshBrdName); //strExpectedDshBrdName
		
		//	6.Click on Save and Verify Dashboard name.
		driver.findElementById("submitBtn").click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Add Component']")));
		
		WebElement eleDshBrdName = driver.findElementByXPath("//span[contains(@class,'slds-form-element__static slds-grid')]");		
		String [] arrDahboardText = eleDshBrdName.getText().split("Edit Dashboard name");
		String strActualDashBoardName = arrDahboardText[0].trim();
		System.out.println("actual value is: " + strActualDashBoardName);
		
//		Expected Result
//		The New Dashboard is created Successfully
		Assert.assertEquals(strExpectedDshBrdName , strActualDashBoardName);
	
		
	}

}
