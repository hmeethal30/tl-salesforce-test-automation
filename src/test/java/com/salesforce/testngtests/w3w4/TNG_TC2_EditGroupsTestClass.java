package com.salesforce.testngtests.w3w4;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.test.setup.classes.TestNG_BaseClass;
import com.salesforce.utilities.classes.ReadExcel;

public class TNG_TC2_EditGroupsTestClass extends TestNG_BaseClass{
  @Test (dataProvider = "fetchData")
  public void testCreateNewGroups(String strGroupName, String strGroupDescription, String strFontName, String strFontSize) throws InterruptedException, AWTException {
	  
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
		
		//	6. Click on the Dropdown icon and Select EditGroup
		String xpathMoreActionsDrop = "//a[text()='" + strGroupName + "']/ancestor::tbody/tr[1]/td[last()]/span/div/a";
		driver.findElement(By.xpath(xpathMoreActionsDrop)).click();
		driver.findElement(By.xpath("//a[@title='Edit Group']")).click();
		
		//	7.Enter description as 'TestLeaf'
		WebElement inptDescription = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
		wait.until(ExpectedConditions.visibilityOf(inptDescription));
		inptDescription.click();
		inptDescription.clear();
		inptDescription.sendKeys(strGroupDescription);
		
		//	8.In Information field, change the font into Verdana and Change the font size to 18
		String xpathFontName = "//span[text()='" + strFontName + "']";			//"//span[text()='Verdana']"
		driver.findElement(By.xpath("//label[text()='Font']//following::input[@name='font']")).click();
		driver.findElement(By.xpath(xpathFontName)).click();
		driver.findElement(By.xpath("//label[text()='Font Size']//following::input[@name='font-size']")).click();
		
//		WebElement elemFontSize = driver.findElement(By.xpath("//label[text()='Font Size']//following::input[@name='font-size']"));
		String xpathFontSizeParent = "//span[text()='" + strFontSize + "']/../../..";		//"//span[text()='18']/../../.."
		WebElement elemFontSize = driver.findElement(By.xpath(xpathFontSizeParent));
		Coordinates cordFontSize = ((Locatable)elemFontSize).getCoordinates();
		cordFontSize.onPage();
		cordFontSize.inViewPort();
		Thread.sleep(1000);
		
		String xpathFontSize = "//span[text()='" + strFontSize + "']";		//"//span[text()='18']"
		driver.findElement(By.xpath(xpathFontSize)).click();
		
		Actions scrollDown= new Actions(driver);
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();
	    Thread.sleep(1000);
		WebElement elemAccessTypeSelected = driver.findElement(By.xpath("//span[text()='Access Type']/../following-sibling::div/div/div/div/a"));
		wait.until(ExpectedConditions.elementToBeClickable(elemAccessTypeSelected));
		js.executeScript("arguments[0].click();",elemAccessTypeSelected);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[text()='Access Type']/../following-sibling::div/following::a[text()='Private']")).click();
		Thread.sleep(1000);
		//	10.Click Save and Verify the Group Name"
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//span[text()='Save'])[2]")));
		
		Thread.sleep(1000);
		
		WebElement elemSuccessMsg = driver.findElement(By.xpath("//span[text()='success']/../following-sibling::div/div/span"));
		String strExpectedMsg = "Group \"" + strGroupName + "\" was saved.";
		Boolean boolEditGroup = elemSuccessMsg.isDisplayed();
		if (boolEditGroup) {
			String strSuccessMessage = elemSuccessMsg.getText();
			System.out.println(strSuccessMessage);
			Assert.assertEquals(strSuccessMessage, strExpectedMsg);
			System.out.println("Group " + strGroupName + " is edited successfully!");
		} else if (boolEditGroup){
			System.out.println("New group " + strGroupName + " is NOT edited successfully!");
		}
		
  }

	@DataProvider(name = "fetchData")
	public String[][] sendData(ITestContext context) throws IOException {
		String strFile = context.getCurrentXmlTest().getParameter("inputfile");
		String strSheet = "Edit";
		return ReadExcel.readData(strFile, strSheet);
	}
}
