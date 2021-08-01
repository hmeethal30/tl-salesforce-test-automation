package com.salesforce.test.setup.classes;

import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utilities.classes.ReadExcel;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethods2 {

	public static WebDriver driver;
//	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testName, testDescription, testAuthor, testCategory;
//	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
	private static final ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();
//	private static final ThreadLocal<Properties> localDriverProp = new ThreadLocal<Properties>();
	private static final ThreadLocal<WebDriverWait> localDriverWait = new ThreadLocal<WebDriverWait>();
	
	@BeforeSuite
	public void startReport() {
		reporter = new ExtentHtmlReporter("./reports/result.html");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	public void setDriver(WebDriver driver) {
//		WebDriverManager.chromedriver().setup();
//		Map<String, Object> prefs = new
//				 HashMap<String, Object>();
//		prefs.put("profile.default_content_setting_values.notifications", 2);
//		ChromeOptions options = new ChromeOptions();
//		options.setExperimentalOption("prefs", prefs); 
//		driver = new ChromeDriver(options);
		localDriver.set(driver);
	}
	
	public WebDriver getDriver() {
		return localDriver.get();
		
	}
	
	public void setWebDriverWait(WebDriverWait wait) {
		localDriverWait.set(wait);
	}
	
	public WebDriverWait getWebDriverWait()
	{
		return localDriverWait.get();
	}

	
	@BeforeMethod
	public void startApp() {
		String strBrowser = Constants.Default_Brwoser;

		if (strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
			setDriver(new FirefoxDriver());
		} else if (strBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
		} else {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			setDriver(new ChromeDriver(options));
		}

//		WebDriverFactoryStaticThreadLocal.setLocalDriver(driver);
//		driver = WebDriverFactoryStaticThreadLocal.getLocalDriver();
//		
		getDriver().manage().window().maximize();
		getDriver().get(Constants.URL);
		getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		js = (JavascriptExecutor) getDriver(); // Create js executor instance
		setWebDriverWait(new WebDriverWait(getDriver(), Duration.ofSeconds(15)));
	}
	
	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}
	
	public void reportStep (String msg, String status) {
		if(status.equalsIgnoreCase("pass")) {
			test.pass(msg);
		}else if (status.equalsIgnoreCase("fail")) {
			test.fail(msg);
		}
	}
	
	@DataProvider(name = "fetchData")
	public String[][] sendData(Method m) throws IOException {
  	    String strDataFile = Constants.File_Path+Constants.File_Name;
  	    String sheetName = null;
  	    switch (m.getName()) {
		case "testEditGroup":
			sheetName = "Edit";
			break;
		case "testCreateGroup":
			sheetName = "Create";
			break;
		case "testDeleteGroup": 
			sheetName = "Delete";
			break;
		default:
			break;
		}
		
		return ReadExcel.readData(strDataFile, sheetName);
		}
	
	@AfterMethod
	public void closeBrowser() {
		getDriver().close();
	}
	
	@AfterSuite
	public void stopReport() {
		extent.flush();
	}
	

}
