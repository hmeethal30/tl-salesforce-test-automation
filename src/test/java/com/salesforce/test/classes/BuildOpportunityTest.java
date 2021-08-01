package com.salesforce.test.classes;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BuildOpportunityTest {
	static String strUsername = "mercury.bootcamp@testleaf.com";
	static String strPassword = "Bootcamp@123";
	static String strLoginPageUrl = "https://login.salesforce.com";
	
	public static void main(String[] args) throws InterruptedException {
		// Pre set up for webdriver:
		WebDriverManager.chromedriver().setup();

		// Set the notification setting to overwrite the default setting
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		
		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(13, TimeUnit.SECONDS);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Step1: Login to https://login.salesforce.com
		driver.get(strLoginPageUrl);
		driver.manage().window().maximize();
		WebElement txtUsername = driver.findElementById("username");
		txtUsername.sendKeys(strUsername);
		WebElement txtPassword = driver.findElementById("password");
		txtPassword.sendKeys(strPassword);

		// 1.4: Locate Login button and click on it
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// 2. Click on toggle menu button from the left corner
		WebElement iconToggle = driver.findElementByClassName("slds-icon-waffle");
		iconToggle.click();

		// 3. Click view All and click Sales from App Launcher
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();
		WebElement linkSales = driver.findElementByXPath("//p[text()='Sales']");
		linkSales.click();

		// 4. Click on Content tab

		// 5. Click View All Key Deals in Key Deals
		WebElement linkViewAllDeals = driver.findElementByXPath("//span[text()='View All Key Deals']");
		js.executeScript("arguments[0].scrollIntoView();", linkViewAllDeals);
		linkViewAllDeals.click();

		// 6. Click the dropdown from Opportunities and select All Opportunities
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@title='Opportunities']/following::span[text()='Opportunities']")));
		driver.findElementByXPath("//ol//following::span[text()='Recently Viewed' and @data-aura-class='uiOutputText']")
				.click();
		driver.findElementByXPath("//span[text()='All Opportunities' and @class=' virtualAutocompleteOptionText']")
				.click();
		Thread.sleep(2000);

		// 7. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']/parent::a")));
		WebElement btnNew = driver.findElementByXPath("//div[@title='New']/parent::a");
		btnNew.click();
		Thread.sleep(1000);

		// 8. Give Opportunity Name as SRM Steels
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//label[text()='Opportunity Name']/following-sibling::div/input")));
		WebElement txtOpportunity = driver
				.findElementByXPath("//label[text()='Opportunity Name']/following-sibling::div/input");
		txtOpportunity.click();
		txtOpportunity.sendKeys("SRM Steels");

		// 9. Select Type as New Customer and Lead Source as Partner Referral
		// 9.1: Locate and select Type as New Customer
		WebElement dropdownType = driver.findElementByXPath("//label[text()='Type']/following::input");
		dropdownType.click();
		driver.findElementByXPath("//span[text()='New Customer']").click();
		

		// 9.2: Locate and select Lead Source as Partner Referral
		WebElement dropdownLeadSource = driver.findElementByXPath("//label[text() = 'Lead Source']/following::input");
		dropdownLeadSource.click();
		driver.findElementByXPath("//span[text()='Partner Referral']").click();

		// 10. Give Amount as 75000 and Select Close Date as Next month 20th day
		// 10.1: Locate and Give Amount as 75000
		WebElement txtAmount = driver.findElementByXPath("//label[text()='Amount']/following::input");
		txtAmount.click();
		txtAmount.sendKeys("75000");

		// 10.2: Locate and select Close Date as Next month 20th day
		WebElement txtCloseDate = driver.findElementByXPath("//label[text() = 'Close Date']/following::input");
		txtCloseDate.click();
		txtCloseDate.sendKeys("6/20/2021");

		// 11. Select Stage as Needs Analysis
		WebElement dropdownStage = driver.findElementByXPath("//label[text() = 'Stage']/following::input");
		dropdownStage.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Needs Analysis']")));
		driver.findElementByXPath("//span[@title='Needs Analysis']").click();

		// 12. Click in Primary Campaign Source and Select first option
		driver.findElementByXPath("//label[text()='Primary Campaign Source']/following::input").click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//h3[text()='Recent Campaigns']/ancestor::li//following-sibling::li")));
		driver.findElementByXPath("//h3[text()='Recent Campaigns']/ancestor::li//following-sibling::li").click();

		// 13. Click Save and Verify the SRM Steels opportunity is created
		driver.findElementByXPath("//button[text()='Save']").click();
	}
}
