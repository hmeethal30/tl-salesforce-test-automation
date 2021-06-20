package com.salesforce.testngtests.w3w4;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.testngtests.ReadExcel;
import com.salesforce.testngtests.TestNG_BaseClass;

public class TNG_TC3_DeleteGroupsTestClass extends TestNG_BaseClass{
  @Test (dataProvider = "fetchData")
  public void testCreateNewGroups(String strGroupName) throws InterruptedException, AWTException {
	  
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
		
		//	5. Search the GroupName 'YourName'
		WebElement inptSearch = driver.findElement(By.xpath("//input[@placeholder='Search this list...']"));
		wait.until(ExpectedConditions.elementToBeClickable(inptSearch));
		inptSearch.click();
		inptSearch.clear();
		inptSearch.sendKeys(strGroupName);
		inptSearch.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		
		//	6. Click on the 'Your Name'
		String xpathElemGroupLink = "//a[text()='" + strGroupName + "']";
		WebElement elemGroupLink = driver.findElement(By.xpath(xpathElemGroupLink));
		wait.until(ExpectedConditions.elementToBeClickable(elemGroupLink));
		elemGroupLink.click();
		
		//	7.Click on the Dropdown icon near the New Case 
		String xpathElemShowActions = "//span[text()='Show more actions']/ancestor::a";
		WebElement elemShowActions = driver.findElement(By.xpath(xpathElemShowActions));
		wait.until(ExpectedConditions.elementToBeClickable(elemShowActions));
		elemShowActions.click();
		
		//	8.Select the Delete Group and Click Delete
		driver.findElement(By.xpath("//a[@title='Delete Group']")).click();
		driver.findElement(By.xpath("//span[text()='Delete Group']/parent::button")).click();
		Thread.sleep(1000);
		
		//	9.Verify the Group is deleted by the Group Name"		
		WebElement elemSuccessMsg = driver.findElement(By.xpath("//span[text()='success']/../following-sibling::div/div/span"));
		String strExpectedMsg = "Group \"" + strGroupName + "\" was deleted. ";
		Boolean boolEditGroup = elemSuccessMsg.isDisplayed();
		if (boolEditGroup) {
			String strSuccessMessage = elemSuccessMsg.getText();
//			System.out.println(strSuccessMessage);
			Assert.assertTrue(strSuccessMessage.contains(strExpectedMsg));
			System.out.println("Group " + strGroupName + " is deleted successfully!");
		} else if (boolEditGroup){

			System.out.println("New group " + strGroupName + " is NOT deleted successfully!");
		}
		
  }

	@DataProvider(name = "fetchData")
	public String[][] sendData(ITestContext context) throws IOException {
		String strFile = context.getCurrentXmlTest().getParameter("inputfile");
		String strSheet = "Delete";
		return ReadExcel.readData(strFile, strSheet);
	}
}
