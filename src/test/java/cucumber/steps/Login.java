package cucumber.steps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	public ChromeDriver driver;
	
	@Given("Chrome browser is launched")
	public void chrome_browser_is_launched() {
		WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Given("Application url is loaded")
	public void loadApplication() {
		driver.get("https://login.salesforce.com");
	}
	
	@Given("Username is entered as mercury.bootcamp@testleaf.com")
	public void enterUsername() {
		WebElement inptUsername = driver.findElementById("username");
		inptUsername.sendKeys("mercury.bootcamp@testleaf.com");
	}
	
	@Given("Password is entered as Bootcamp@123")
	public void enterPassword() {
		WebElement inptPassword = driver.findElementById("password");
		inptPassword.sendKeys("Bootcamp@123");
	}
	
	@When("Login button is clicked")
	public void clickLogin() {
		WebElement btnLogin = driver.findElementById("Login");
		btnLogin.click();
	}
	
	public void verifyLoginResult() {
		String actTitle = driver.getTitle();
		System.out.println(actTitle);
	}

	
}

