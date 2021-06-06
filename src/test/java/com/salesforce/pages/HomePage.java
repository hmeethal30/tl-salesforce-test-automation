package com.salesforce.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	JavascriptExecutor js= (JavascriptExecutor) driver;

	//Locators
	
	//toggle menu
	@FindBy(how=How.CLASS_NAME, using = "slds-icon-waffle")
	private WebElement elemToggleMenu;
	
	//View All link
	@FindBy(how=How.XPATH, using = "//button[text()='View All']")
	private WebElement lnkViewAll;
	
	//Dashboards link
	@FindBy(how=How.XPATH, using = "//p[text()='Dashboards']")
	private WebElement linkDashboards;
	
//	public HomePage(ChromeDriver driver2) {
//		// TODO Auto-generated constructor stub
//	}
	
	//Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		//Initialise elements
		PageFactory.initElements(driver, this);	
	}

	public void clickToggleMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(elemToggleMenu));
		elemToggleMenu.click();
	}

	public void clickLinkViewAll() {
		wait.until(ExpectedConditions.elementToBeClickable(lnkViewAll));
		lnkViewAll.click();
	}
	
	public void clickLinkDashboard() {
		wait.until(ExpectedConditions.elementToBeClickable(linkDashboards));
		js.executeScript("arguments[0].scrollIntoView();", linkDashboards);
		linkDashboards.click();
		
	}
}
