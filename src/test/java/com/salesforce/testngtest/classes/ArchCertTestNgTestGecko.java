package com.salesforce.testngtest.classes;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ArchCertTestNgTestGecko {
//	WebDriver driver;
//	ChromeDriver driver;
	FirefoxDriver driver;
	WebDriverWait wait;
	String strUsername = "makaia@testleaf.com";
	String strPassword = "India@123";
	String loginPageURL = "https://login.salesforce.com";
	
	@BeforeMethod
	public void setUp() {
		// set up
		WebDriverManager.firefoxdriver().setup();
		FirefoxDriver driver = new FirefoxDriver();
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}


	@Test
	public void testArchitectCertification1() {
		//1. Launch Salesforce application https://login.salesforce.com/
		driver.get(loginPageURL);
		
		//2. Login with username as "makaia@testleaf.com" and password as "India@123"
		WebElement txtUsername = driver.findElementById("username");
		txtUsername.sendKeys(strUsername);
		WebElement txtPassword = driver.findElementById("password");
		txtPassword.sendKeys(strPassword);
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();
			
		//3. Click on Learn More link in Mobile Publisher
		String linkLearnMoreXpath = "//span[text()='Learn More']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(linkLearnMoreXpath)));
		WebElement linkLearnMore = driver.findElementByXPath(linkLearnMoreXpath);
			
		//Capture current tab's window handle
		String firstTab=driver.getWindowHandle();
		System.out.println("First tab is:"+firstTab);
		
		linkLearnMore.click();
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));	//Wait for new tab to be loaded
				
		// 4. Navigate to the Salesforce Customer Service
		Set<String> allHandlesInstance1 =  driver.getWindowHandles();
		System.out.println("Total number of tabs: "+allHandlesInstance1.size());
		
		//iterate through all handles
		Iterator<String> i1=allHandlesInstance1.iterator();		
		while(i1.hasNext()) {
			String nextTab = i1.next();
			if(!firstTab.equalsIgnoreCase(nextTab)) {
				driver.switchTo().window(nextTab);		//Switch to second tab
			}
		}
		
		String secondTab = driver.getWindowHandle();
		System.out.println("Second tab is:"+secondTab);

		//To Do: Add code to click on certifications link
		//Mouse hover on Resources
		WebElement linkResources = driver.findElementByXPath("//button/span[text()='Resources']");
		Actions action = new Actions(driver);
		action.moveToElement(linkResources);
		//Locate and click on certification
		WebElement linkCertfn = driver.findElementByXPath("//span[text()='Salesforce Certification ']/..");
		action.moveToElement(linkCertfn);
		action.click().build().perform();
		
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		Set<String> allHandlesInstance2 = driver.getWindowHandles();
		System.out.println("Total number of tabs: "+allHandlesInstance1.size());

		//iterate through all handles again
		Iterator<String> i2=allHandlesInstance2.iterator();
		while(i2.hasNext()) {
			String nextTab = i2.next();
			if(!firstTab.equalsIgnoreCase(nextTab) && !secondTab.equalsIgnoreCase(nextTab)) {
				//Switch to child window
				driver.switchTo().window(nextTab);
				System.out.println("Next tab is:"+nextTab);
			}
		}
		String thirdTab = driver.getWindowHandle();
		System.out.println("Third tab is:"+thirdTab);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Salesforce Architect']/..")));
		driver.findElementByXPath("//div[text()='Salesforce Architect']/..").click();
		
		//	9. Verify the Page tile after selecting Architect
		if(driver.getTitle().contains("Certification - Architect Overview"))
		    System.out.println("Page title contains \"Certification - Architect Overview\" ");
		else
		    System.out.println("Page title doesn't contain \"Certification - Architect Overview\" ");
		
		//	10. Verify/print the Certifications listed for Architect
		List<WebElement> allCertifications = driver.findElementsByXPath("//div[text()='Certification']/following-sibling::div/a");
		System.out.println("The available certifications are:");
		for(WebElement cert : allCertifications) {
			System.out.println(cert.getText());			
		}		
	}


}
