package com.salesforce.page.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.test.setup.classes.BaseMethods;

public class HomePage extends BaseMethods{
	
	By lctr_toggleMenuIcon = By.className("slds-icon-waffle");
	By lctr_viewAllLink	=	By.xpath("//button[text()='View All']");
	By lctr_salesLink = By.xpath("//p[text()='Sales']");
//	By locationsLink =
	
//	public HomePage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
//		this.driver = driver;
//		this.js = js;
//		this.wait = wait;
//	}
	
	// Click on toggle menu
	public HomePage clickToggleMenu() throws InterruptedException {
		try {
			// WebElement toggleMenuIcon =
			// driver.findElement(By.className("slds-icon-waffle"));
			WebElement toggleMenuIcon = driver.findElement(lctr_toggleMenuIcon);
			wait.until(ExpectedConditions.elementToBeClickable(toggleMenuIcon));
			toggleMenuIcon.click();
			Thread.sleep(1000);
			reportStep("Clicked on toggle menu successfully", "pass");
		} catch (Exception e) {
			reportStep("NOT clicked on toggle menu successfully", "pass");
			e.printStackTrace();
		}
		return this;
	}

	public HomePage clickOnViewAllLink() throws InterruptedException {
//		WebElement viewAllLink = driver.findElement(By.xpath("//button[text()='View All']"));
		WebElement viewAllLink = driver.findElement(lctr_viewAllLink);
		wait.until(ExpectedConditions.elementToBeClickable(viewAllLink));
		viewAllLink.click();
		Thread.sleep(1000);
		return this;
	}
	
	/* Click on sales link */
	public HomePage clickOnSalesLink() throws InterruptedException {
		WebElement linkSales = driver.findElement(By.xpath("//p[text()='Sales']"));
		linkSales.click();
//		Thread.sleep(2000);
		return this;	
	}
	
	public HomePage clickOnDashboardsLink() {
		WebElement dashboardsLink = driver.findElement(By.xpath("//p[text()='Dashboards']"));
		wait.until(ExpectedConditions.elementToBeClickable(dashboardsLink));
		js.executeScript("arguments[0].scrollIntoView();", dashboardsLink);
		dashboardsLink.click();
		return this;
	}
	
	// Click on Groups tab
	public GroupsPage clickOnGroupsTab() {
		WebElement groupsTab = driver.findElement(By.xpath("//span[text()='Groups']/parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(groupsTab));
		js.executeScript("arguments[0].click();",groupsTab);
		return new GroupsPage();	
	}
}
