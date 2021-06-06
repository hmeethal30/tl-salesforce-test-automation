//package com.salesforce.setup;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.BeforeClass;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class SalesForceSetup {
//	private WebDriver driver;
//	DriverManagerType chrome = DriverManagerType.CHROME;
//	
//	
//	
////	WebDriverManager.chromedriver().setup();
//	
//	// Create object of HashMap Class
//	Map<String, Object> prefs = new HashMap<String, Object>();
//
//	// Set the notification setting to overwrite the default setting
//	prefs.put("profile.default_content_setting_values.notifications", 2);
//
//	// Create object of ChromeOption class
//	ChromeOptions options = new ChromeOptions();
//
//	// Set the experimental option
//	options.setExperimentalOption("prefs", prefs);
//	
//	ChromeDriver driver1= new ChromeDriver(options);
//	driver.manage().window().maximize();
//	
//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//}
