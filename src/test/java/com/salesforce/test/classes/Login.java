package com.salesforce.test.classes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	public static void main(String[] args) throws InterruptedException, IOException {
		String strUsername = "mercury.bootcamp@testleaf.com";
		String strPassword = "Bootcamp@123";
		String LOGIN_PAGE_URL = "https://login.salesforce.com";
		String nodeURL = "https://oauth-hmeethal30-fdc9e:dbb97fef-c393-4796-81ff-0e3cd0f563fa@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

		
		//desired capabilities
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setPlatform(Platform.WINDOWS);
		dc.setBrowserName("chrome");
		
		// set up
//		WebDriverManager.chromedriver().setup();
		WebDriver driver = new RemoteWebDriver(new URL(nodeURL), dc);
		
		// Create object of HashMap Class
		Map<String, Object> prefs = new HashMap<String, Object>();
	
		// Set the notification setting to overwrite the default setting
		prefs.put("profile.default_content_setting_values.notifications", 2);
	
		// Create object of ChromeOption class
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs);
//		ChromeDriver driver= new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS) ;

		
		//Create driver and js executor instances
		driver.manage().window().maximize();
	
		JavascriptExecutor jsExecutor= (JavascriptExecutor) driver;
		
//		START TEST CASE HERE
		//	1. log in to https://login.salesforce.com
		driver.get(LOGIN_PAGE_URL);
		WebElement inptUsername = driver.findElement(By.id("username"));
		inptUsername.sendKeys(strUsername);
		WebElement inptPassword = driver.findElement(By.id("password"));
		inptPassword.sendKeys(strPassword);
		WebElement btnLogin = driver.findElement(By.id("Login"));
		btnLogin.click();
		
		//Take snapshot
		WebDriver augmentDriver = new Augmenter().augment(driver);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\HLearning\\FSAutomation\\WorkSpaces\\Maven\\tLSalesForceTestAutomation\\screenshots\\Login.jpg"));
//		FileUtils.copyFile(src, new File("C:/HLearning/FSAutomation/WorkSpaces/Maven/tLSalesForceTestAutomation/screenshots/Login.jpg"));
		
		driver.quit();
	}
}
