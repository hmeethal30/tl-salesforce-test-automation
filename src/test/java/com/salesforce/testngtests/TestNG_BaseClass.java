package com.salesforce.testngtests;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_BaseClass {
	public WebDriver driver;
	public JavascriptExecutor js;
	public WebDriverWait wait;

	@Parameters({"url", "username", "password", "browser"})
	@BeforeMethod
	public void setUp(String strLoginPageUrl, String strUsername, String strPassword, String strBrowser) {
		// Pre set up:
		if(strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		}
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		js = (JavascriptExecutor) driver;

		// Step1: Login to https://login.salesforce.com
		driver.get(strLoginPageUrl);
		driver.manage().window().maximize();
		
		// 1.2: Locate user id and enter value
		
		WebElement txtUsername = driver.findElement(By.id("username"));;
		txtUsername.sendKeys(strUsername);

		// 1.3: Locate password field and enter value
		WebElement txtPassword = driver.findElement(By.id("password"));
		txtPassword.sendKeys(strPassword);

		// 1.4: Locate Login button and click on it
		WebElement btnLogin = driver.findElement(By.id("Login"));
		btnLogin.click();
	}
	
//	@AfterMethod public void cleanUp() { 
//		driver.close(); 
//		}



}
