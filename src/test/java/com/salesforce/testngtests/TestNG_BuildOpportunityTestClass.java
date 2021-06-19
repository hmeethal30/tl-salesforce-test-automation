package com.salesforce.testngtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNG_BuildOpportunityTestClass extends TestNG_BaseClass {
	
	@Test(dataProvider = "fetchData")
	public void testBuildOpportunity(String opName, String opAmount, String opDate) throws InterruptedException {

		// 2. Click on toggle menu button from the left corner
		// 2.1: Locate ad click on toggle menu
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElement(By.className("slds-icon-waffle"));
		iconToggle.click();
		Thread.sleep(1000);

		// 3. Click view All and click Sales from App Launcher
//		Actions act =  new Actions(driver);
//		act.moveToElement(driver.findElementByXPath("//button[text()='View All']")).click().perform();
		WebElement btnViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		btnViewAll.click();
		WebElement linkSales = driver.findElement(By.xpath("//p[text()='Sales']"));
		linkSales.click();

		// 4. Click on Content tab

		// 5. Click View All Key Deals in Key Deals
		WebElement linkViewAllDeals = driver.findElement(By.xpath("//span[text()='View All Key Deals']"));
		js.executeScript("arguments[0].scrollIntoView();", linkViewAllDeals);
		linkViewAllDeals.click();

		// 6. Click the dropdown from Opportunities and select All Opportunities
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Opportunities']/following::span[text()='Opportunities']")));
		driver.findElement(By.xpath("//ol//following::span[text()='Recently Viewed' and @data-aura-class='uiOutputText']")).click(); 
		driver.findElement(By.xpath("//span[text()='All Opportunities' and @class=' virtualAutocompleteOptionText']")).click();
		Thread.sleep(2000);

		// 7. Click on New
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='New']/parent::a")));
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']/parent::a"));
		btnNew.click();
		Thread.sleep(1000);
		
		// 8. Give Opportunity Name as SRM Steels
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[text()='Opportunity Name']/following-sibling::div/input"))); 
		WebElement txtOpportunity = driver.findElement(By.xpath("//label[text()='Opportunity Name']/following-sibling::div/input"));
		txtOpportunity.click();
		txtOpportunity.sendKeys(opName);

		// 9. Select Type as New Customer and Lead Source as Partner Referral
		// 9.1: Locate and select Type as New Customer
		WebElement dropdownType = driver.findElement(By.xpath("//label[text()='Type']/following::input"));
		dropdownType.click();
		driver.findElement(By.xpath("//span[text()='New Customer']")).click();


		// 9.2: Locate and select Lead Source as Partner Referral
		WebElement dropdownLeadSource = driver.findElement(By.xpath("//label[text() = 'Lead Source']/following::input"));
		dropdownLeadSource.click();
		driver.findElement(By.xpath("//span[text()='Partner Referral']")).click();

		// 10. Give Amount as 75000 and Select Close Date as Next month 20th day
		// 10.1: Locate and Give Amount as 75000
		WebElement txtAmount = driver.findElement(By.xpath("//label[text()='Amount']/following::input"));
		txtAmount.click();
		txtAmount.sendKeys(opAmount);

		// 10.2: Locate and select Close Date as Next month 20th day
		WebElement txtCloseDate = driver.findElement(By.xpath("//label[text() = 'Close Date']/following::input"));
		txtCloseDate.click();
		txtCloseDate.sendKeys(opDate);

		// 11. Select Stage as Needs Analysis
		WebElement dropdownStage = driver.findElement(By.xpath("//label[text() = 'Stage']/following::input"));
		dropdownStage.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@title='Needs Analysis']")));
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();

		// 12. Click in Primary Campaign Source and Select first option
		driver.findElement(By.xpath("//label[text()='Primary Campaign Source']/following::input")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='Recent Campaigns']/ancestor::li//following-sibling::li")));
		driver.findElement(By.xpath("//h3[text()='Recent Campaigns']/ancestor::li//following-sibling::li")).click();

		// 13. Click Save and Verify the SRM Steels opportunity is created
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
	}
	
	@DataProvider(name = "fetchData")
	public String[][] sendData() {
		String[][] data = new String[2][3]; 
		
		data [0][0] = "SRM Steels";
		data [0][1] = "75000";
		data [0][2] = "6/20/2021";
		
		data [1][0] = "SRM Steels";
		data [1][1] = "75000";
		data [1][2] = "6/22/2021";
		
		return data;
	}

}
