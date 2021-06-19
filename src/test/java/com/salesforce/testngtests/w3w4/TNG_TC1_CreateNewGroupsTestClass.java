package com.salesforce.testngtests.w3w4;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.salesforce.testngtests.ReadExcel;
import com.salesforce.testngtests.TestNG_BaseClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TNG_TC1_CreateNewGroupsTestClass extends TestNG_BaseClass{
  @Test (dataProvider = "fetchData")
  public void testCreateNewGroups(String strGroupName, String strFile) throws InterruptedException, AWTException {
	  
//	  	String strGroupName = "Haseena3";
		// 2. Click on toggle menu button from the left corner
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
		Thread.sleep(2000);


		// 4. Click on Groupss tab 
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
		
		//	7. Select Access Type as 'Public'
		driver.findElement(By.xpath("//span[text()='Access Type']/following::a[1]")).click();
		driver.findElement(By.xpath("//a[@title='Public']")).click();
		
		//	8. click Save and Next
		driver.findElement(By.xpath("//span[text()='Save & Next']/parent::button")).click();
		
		//	9.Click Upload image and Upload any picture
		driver.findElement(By.xpath("//button[text()='Upload Image']")).click();
		Thread.sleep(2000);
		
		Robot rb = new Robot();
		
		StringSelection strFileName = new StringSelection(strFile);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strFileName, null);
		
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		
		//	10.Click Next
		Thread.sleep(2000);
		String xpathImageUpload = "//span[text()='Drag and resize to adjust thumbnail.']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathImageUpload)));
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		//	11.Click Done and Verify the Group name"
		driver.findElement(By.xpath("//span[text()='Done']")).click();

		
		String xpathGrpName = "//span[text()='"+strGroupName+"']";
		Boolean boolNewGroup = driver.findElement(By.xpath(xpathGrpName)).isDisplayed();
		if (boolNewGroup) {
			System.out.println("New group " + strGroupName + " is created successfully!");
		} else {
			System.out.println("New group " + strGroupName + " is NOT created successfully!");
		}
		
  }
	@DataProvider(name = "fetchData")
	public String[][] sendData(ITestContext context) throws IOException {
  	    String strFile = context.getCurrentXmlTest().getParameter("inputfile");
		return ReadExcel.readData(strFile);}
}
