package com.salesforce.test.setup.classes;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.salesforce.page.classes.LoginPage;
import com.salesforce.utilities.classes.ExcelUtility;
import com.salesforce.utilities.classes.ReadExcel;

import io.github.bonigarcia.wdm.WebDriverManager;;

public class TestNG_BaseClass {
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	String strBrowser = Constants.Default_Brwoser ;
	String strUsername = Constants.User_Name;
	String strPassword = Constants.Password;
	LoginPage loginPage;
	Object[][] testData;
	
	@BeforeMethod ()
	public void setUps() {
		Reporter.log("===========Browser session started==============", true);
		if(strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(strBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		}
		driver.get(Constants.URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		System.out.println(driver.getWindowHandle());
		js = (JavascriptExecutor) driver;		//Create js executor instance
		wait = new WebDriverWait(driver, Duration.ofSeconds(12));
		
//		loginPage = new LoginPage(driver);		//Create login page instance
//		Reporter.log("===========Browser session started==============", true);
//
//		loginPage.enterUsername(strUsername);	// 1.2: Locate user id and enter value
//		loginPage.enterPassword(strPassword);	// 1.3: Locate password field and enter value
//		loginPage.clickLoginButton();			// 1.4: Locate Login button and click on it
	}
	
//	@DataProvider(name = "setupData")
//	public Object[][] dataProvider() throws IOException{
//		testData = ExcelUtility.getTestData("setup");
//		return null;
//		
//	}
//	
	
	@DataProvider(name = "fetchData")
	public String[][] sendData() throws IOException {
  	    String strFile = Constants.File_Path+Constants.File_Name;
		String sheetName = "Create";
		return ReadExcel.readData(strFile, sheetName);}
	
//	@AfterMethod public void cleanUp() { 
//		driver.close(); 
//		}



}
