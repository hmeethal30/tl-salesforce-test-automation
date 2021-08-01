package com.salesforce.test.setup.classes;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactoryStaticThreadLocal {
	private static ThreadLocal<WebDriver> localDriver = new ThreadLocal<WebDriver>();

	public static void setLocalDriver(WebDriver driver) {
		String strBrowser = Constants.Default_Brwoser;
		if (strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			localDriver.set(new FirefoxDriver());
		} else if (strBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			localDriver.set(new EdgeDriver());
		} else {
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			localDriver.set(new ChromeDriver(options));
		}
	}
	
	public static WebDriver getLocalDriver() {
		return localDriver.get();
	}
	
	public static void closeBrowser() {
		localDriver.get().close();
		localDriver.remove();
	}
}
