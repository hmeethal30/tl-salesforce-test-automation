package com.salesforce.testngtest.classes;

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
import org.testng.annotations.Test;

import com.salesforce.test.setup.classes.TestNG_BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_CreateContactForCampaignTest extends TestNG_BaseClass {
//	static String strLoginPageUrl = "https://login.salesforce.com";
//	static String strUsername = "mercury.bootcamp@testleaf.com";
//	static String strPassword = "Bootcamp@123";
	
	@Test
	public void testCreateContactForCampaign() throws InterruptedException {		

		//	2. Click on the toggle menu button from the left corner
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElement(By.className("slds-icon-waffle"));
		iconToggle.click();
		
		//	3. Click view All and click Sales from App Launcher
		// 3.1: Locate ad click view all
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View All']")));
		WebElement btnViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		btnViewAll.click();
		// 3.2: Locate ad click sales
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='Sales']")));
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(10);

		//	4. Click on Campaigns tab 
		WebElement elemCampaings = driver.findElement(By.xpath("//span[text()='Campaigns']"));
		js.executeScript("arguments[0].click();", elemCampaings);
		Thread.sleep(1000);

		// 5. Click Bootcamp link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@Title=\"Bootcamp\"])[1]")));
		driver.findElement(By.xpath("(//a[@Title=\"Bootcamp\"])[1]")).click();
		Thread.sleep(1000);
		
		
		//	6. Click Add Contact
		Actions scrollDown= new Actions(driver);
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();
	    Thread.sleep(1000);
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();
		WebElement elemAddContacts = driver.findElement(By.xpath("//*[text()='Add Contacts']"));
		elemAddContacts.click();
		
		//	7. Click on New Contact under Search field
//		driver.findElementByXPath("//input[@title='Search Contacts']").click();
		String xpathNewContact = "//span[text()='New Contact']";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathNewContact)));
		driver.findElement(By.xpath(xpathNewContact)).click();

		//	8. Pick Salutation as 'Mr.'
		driver.findElement(By.xpath("//span[text()='Salutation']/following::*[text()='--None--'][1]")).click();
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		
		//	9. Enter first name as <your First Name>
		WebElement inptFirstName = driver.findElement(By.xpath("//span[text()='First Name']/parent::label/following-sibling::input"));
		inptFirstName.click();
		String strFirstName = "H6";
		inptFirstName.sendKeys(strFirstName);

		//	10. Enter last name as <your last name>
		String strLastName = "M";
		WebElement inptLastName = driver.findElement(By.xpath("//span[text()='Last Name']/parent::label/following-sibling::input"));
		inptLastName.click();
		inptLastName.sendKeys(strLastName);

		//	12. Click Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		//	13. Click Next
		String xpathBtnNext = "(//span[text()='Next'])/parent::button";
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathBtnNext)));
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathBtnNext)));

//		driver.findElementByXPath(xpathBtnNext).click();

		//	14. Click Submit on the Add to Campaign pop up
		String xpathBtnSubmit = "(//span[text()='Submit'])/parent::button";
		WebElement btnSubmit = driver.findElement(By.xpath(xpathBtnSubmit));
		wait.until(ExpectedConditions.elementToBeClickable(btnSubmit));
		btnSubmit.click();

		
		//	15. verify the created Contact under Campaign
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();		
	    driver.findElement(By.xpath("//span[text()='Campaign Members']/parent::a")).click();
		String xpathMyContact = "//a[text()='" + strFirstName + " " + strLastName + "']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Campaign Members']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMyContact)));
	    Boolean boolMyContact = driver.findElement(By.xpath(xpathMyContact)).isDisplayed();
	    if (boolMyContact) {
	    	System.out.println("New contact displayed under Campaigns");
	    } else {
	    	System.out.println("New contact is not displayed under Campaigns");
	    }
	    
		//	16. Navigate to Contacts tab
		WebElement elemContacts = driver.findElement(By.xpath("//span[text()='Contacts']"));
		js.executeScript("arguments[0].click();", elemContacts);

		//	17. Search for Cantact with your Name"
		WebElement inptSearchContact = driver.findElement(By.xpath("//input[@name='Contact-search-input']"));
		inptSearchContact.click();
		inptSearchContact.sendKeys(strFirstName + " " + strLastName);
		inptSearchContact.sendKeys(Keys.ENTER);
		
		//Steps
		Boolean boolMyContactInContacts = driver.findElement(By.xpath(xpathMyContact)).isDisplayed();
		if (boolMyContactInContacts) {
	    	System.out.println("New contact displayed under Contacts");
	    } else {
	    	System.out.println("New contact is not displayed under Contacts");
	    }
	}

}
