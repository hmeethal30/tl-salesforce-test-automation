package com.salesforce;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactForCampaignTest {

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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
		
		//	3. Click view All and click Sales from App Launcher
		// 3.1: Locate ad click view all
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElementByXPath("//button[text()='View All']");
		btnViewAll.click();
		// 3.2: Locate ad click sales
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Sales']")));
		driver.findElementByXPath("//p[text()='Sales']").click();
		Thread.sleep(10);

		//	4. Click on Campaigns tab 
		WebElement elemCampaings = driver.findElementByXPath("//span[text()='Campaigns']");
		jsExecutor.executeScript("arguments[0].click();", elemCampaings);
//
//		String windowBefore=driver.getWindowHandle();
//		System.out.println("Window handle before clicking bootcamp is: " + windowBefore);

		// 5. Click Bootcamp link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@Title=\"Bootcamp\"])[1]")));
		driver.findElementByXPath("(//a[@Title=\"Bootcamp\"])[1]").click();
//		Thread.sleep(5000);
		
		
		//	6. Click Add Contact
		Actions scrollDown= new Actions(driver);
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();		
		WebElement elemAddContacts = driver.findElementByXPath("//*[text()='Add Contacts']");
		elemAddContacts.click();
		
		//	7. Click on New Contact under Search field
//		driver.findElementByXPath("//input[@title='Search Contacts']").click();
		String xpathNewContact = "//span[text()='New Contact']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathNewContact)));
		driver.findElementByXPath(xpathNewContact).click();

		//	8. Pick Salutation as 'Mr.'
		driver.findElementByXPath("//span[text()='Salutation']/following::*[text()='--None--'][1]").click();
		driver.findElementByXPath("//a[@title='Mr.']").click();
		
		//	9. Enter first name as <your First Name>
		WebElement inptFirstName = driver.findElementByXPath("//span[text()='First Name']/parent::label/following-sibling::input");
		inptFirstName.click();
		String strFirstName = "H6";
		inptFirstName.sendKeys(strFirstName);

		//	10. Enter last name as <your last name>
		String strLastName = "M";
		WebElement inptLastName = driver.findElementByXPath("//span[text()='Last Name']/parent::label/following-sibling::input");
		inptLastName.click();
		inptLastName.sendKeys(strLastName);

		//	12. Click Save
		driver.findElementByXPath("//button[@title='Save']").click();
		
		//	13. Click Next
		String xpathBtnNext = "(//span[text()='Next'])/parent::button";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBtnNext)));
		jsExecutor.executeScript("arguments[0].click();", driver.findElementByXPath(xpathBtnNext));

//		driver.findElementByXPath(xpathBtnNext).click();

		//	14. Click Submit on the Add to Campaign pop up
		String xpathBtnSubmit = "(//span[text()='Submit'])/parent::button";
		WebElement btnSubmit = driver.findElementByXPath(xpathBtnSubmit);
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();

		
		//	15. verify the created Contact under Campaign
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();		
	    driver.findElementByXPath("//span[text()='Campaign Members']/parent::a").click();
		String xpathMyContact = "//a[text()='" + strFirstName + " " + strLastName + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Campaign Members']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMyContact)));
	    Boolean boolMyContact = driver.findElementByXPath(xpathMyContact).isDisplayed();
	    if (boolMyContact) {
	    	System.out.println("New contact displayed under Campaigns");
	    } else {
	    	System.out.println("New contact is not displayed under Campaigns");
	    }
	    
		//	16. Navigate to Contacts tab
		WebElement elemContacts = driver.findElementByXPath("//span[text()='Contacts']");
		jsExecutor.executeScript("arguments[0].click();", elemContacts);

		//	17. Search for Cantact with your Name"
		WebElement inptSearchContact = driver.findElementByXPath("//input[@name='Contact-search-input']");
		inptSearchContact.click();
		inptSearchContact.sendKeys(strFirstName + " " + strLastName);
		inptSearchContact.sendKeys(Keys.ENTER);
		
		//Steps
		Boolean boolMyContactInContacts = driver.findElementByXPath(xpathMyContact).isDisplayed();
		if (boolMyContactInContacts) {
	    	System.out.println("New contact displayed under Contacts");
	    } else {
	    	System.out.println("New contact is not displayed under Contacts");
	    }
	}

}
