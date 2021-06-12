package com.salesforce;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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

public class DeleteAttachmentFromCampaignTest {

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

		//	4. Click on Campaigns tab 
		WebElement elemCampaings = driver.findElementByXPath("//span[text()='Campaigns']");
		jsExecutor.executeScript("arguments[0].click();", elemCampaings);
		
		// 5. Click Bootcamp link
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@Title=\"Bootcamp\"])[1]")));
		driver.findElementByXPath("(//a[@Title=\"Bootcamp\"])[1]").click();
		
		//	6. Click on View All link in the Attachments section
	    driver.findElementByXPath("//span[text()='Attachments']/parent::span[text()='View All']/../..").click();
		
		WebElement tblAttachmentsBefore = driver.findElementByXPath("//th[@title='Title']/../../..");
		List<WebElement> tableRowsBefore = tblAttachmentsBefore.findElements(By.tagName("tr"));
		int attachmentCountBefore = tableRowsBefore.size();
		System.out.println(attachmentCountBefore);
		
		
		//	7. Click the dropdown icon for the recently attached document
		driver.findElementByXPath("//th[@title='Title']/../../../tbody/tr[1]/td[last()]/span/div/a").click();
		
		//	8. Select Delete and Confirm the delete
		driver.findElementByXPath("//a[@title='Delete']").click();
		driver.findElementByXPath("//span[text()='Delete']/parent::button").click();
		Thread.sleep(2000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),' was deleted')]")));
//		driver.findElementByXPath("//button[@title='Close']").click();
//		
		//	9. Verify the file deleted from the Attachments
		WebElement tblAttachmentsAfter = driver.findElementByXPath("//th[@title='Title']/../../..");
		List<WebElement> tableRowsAfter = tblAttachmentsAfter.findElements(By.tagName("tr"));
		int attachmentCountAfter = tableRowsAfter.size();
		System.out.println(attachmentCountAfter);
		
		if (attachmentCountAfter == (attachmentCountBefore -1) ) {
			System.out.println("Passed: Attachment deleted successfully!");
		} else {
			System.out.println("Failed: Attachment NOT deleted successfully!");
		}
	    
	}

}
