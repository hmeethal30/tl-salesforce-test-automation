package com.salesforce.testngtests.w3w4;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.testngtests.ReadExcel;
import com.salesforce.testngtests.TestNG_BaseClass;

public class TNG_TC4_CreateGroupsWithoutMndatoryTestClass extends TestNG_BaseClass{
  @Test 
  public void testGroupsWithoutMandatoryFields() throws InterruptedException, AWTException {
	  	String expectedErrorMsg = "These required fields must be completed: Access Type";
	  	String strGroupName="Haseena";
		// 2. Click on toggle menu button from the left corner
		wait.until(ExpectedConditions.elementToBeClickable(By.className("slds-icon-waffle")));
		WebElement iconToggle = driver.findElement(By.className("slds-icon-waffle"));
		iconToggle.click();
		Thread.sleep(1000);

		// 3. Click view All and click Sales from App Launcher

		WebElement btnViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		btnViewAll.click();
		WebElement linkSales = driver.findElement(By.xpath("//p[text()='Sales']"));
		linkSales.click();
		Thread.sleep(2000);

		// 4. Click on Groups tab 
		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement elemGroupsTab = driver.findElement(By.xpath("//span[text()='Groups']/parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(elemGroupsTab));
		js.executeScript("arguments[0].click();",elemGroupsTab);
		
		//  5. Click on New button
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']/parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(btnNew));
		btnNew.click();
		
		//	6. Enter Name as 'Your Name' 		
		WebElement inptName = driver.findElement(By.xpath("//span[text()='Name']/parent::label/following-sibling::input"));
		wait.until(ExpectedConditions.visibilityOf(inptName));
		inptName.click();
		inptName.sendKeys(strGroupName);
				
		//	7.  click Save and Next
		driver.findElement(By.xpath("//span[text()='Save & Next']/parent::button")).click();
		
		//		8.Verify the Alert message(These required fields must be completed: Access Type)""+ "
		WebElement elemErrorMessage = driver.findElement(By.xpath("//ul[@class='errorsList']/li"));
		Boolean boolError = elemErrorMessage.isDisplayed();
		if (boolError) {
			Assert.assertEquals(elemErrorMessage.getText(), expectedErrorMsg);
			System.out.println("Error message component is displayed when mandatory fields are not provided");
		} else {
			System.out.println("Error message component is NOT displayed when mandatory fields are not provided");
		}		
  }

}
