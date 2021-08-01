package com.salesforce.page.classes;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.salesforce.test.setup.classes.BaseMethods;
import com.salesforce.test.setup.classes.TestNG_BaseClass;

public class GroupsPage extends BaseMethods {
//	private WebDriver driver;
	
//	public GroupsPage(WebDriver driver, JavascriptExecutor js, WebDriverWait wait) {
//		this.driver = driver;
//		this.js = js;
//		this.wait = wait;
//	}
	
	public GroupsPage clickOnNewButton() {
		WebElement btnNew = driver.findElement(By.xpath("//div[@title='New']/parent::a"));
		wait.until(ExpectedConditions.elementToBeClickable(btnNew));
		btnNew.click();
		return this;
	}
	
	public GroupsPage enterGroupName(String strGroupName) {
		WebElement groupNameTextbox = driver.findElement(By.xpath("//span[text()='Name']/parent::label/following-sibling::input"));
		wait.until(ExpectedConditions.visibilityOf(groupNameTextbox));
		groupNameTextbox.click();
		groupNameTextbox.sendKeys(strGroupName);
		return this;
	}
	
	public GroupsPage selectAccessTypeAsPublic() {
		driver.findElement(By.xpath("//span[text()='Access Type']/following::a[1]")).click();
		driver.findElement(By.xpath("//a[@title='Public']")).click();
		return this;
	}
	
	public GroupsPage clickSaveAndNext() {
		driver.findElement(By.xpath("//span[text()='Save & Next']/parent::button")).click();
		return this;
	}
	
	public GroupsPage uploadImage(String imageFile) throws InterruptedException, AWTException {
		driver.findElement(By.xpath("//button[text()='Upload Image']")).click();
		Thread.sleep(2000);
		
		Robot rb = new Robot();
		
		StringSelection strFileName = new StringSelection(imageFile);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strFileName, null);
		
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);

		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
//		Thread.sleep(1200);
		String xpathImageUpload = "//span[text()='Drag and resize to adjust thumbnail.']";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathImageUpload)));
		return this;
	}

	public GroupsPage clickNext() {
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		return this;
	}
	
	public GroupsPage clickDone() {
		driver.findElement(By.xpath("//span[text()='Done']")).click();
		return this;
	}

	public void verifyGroupName(String strGroupName) {
		String xpathGrpName = "//span[text()='"+strGroupName+"']";
		Boolean boolNewGroup = driver.findElement(By.xpath(xpathGrpName)).isDisplayed();
		if (boolNewGroup) {
			System.out.println("New group " + strGroupName + " is created successfully!");
		} else {
			System.out.println("New group " + strGroupName + " is NOT created successfully!");
		}
	}

	/*
	 * // Search the GroupName 'YourName'
	 */
	public GroupsPage searchGroupUsingName(String strGroupName) throws InterruptedException {
		WebElement searchTextbox = driver.findElement(By.xpath("//input[@placeholder='Search this list...']"));
		wait.until(ExpectedConditions.elementToBeClickable(searchTextbox));
		searchTextbox.click();
		searchTextbox.clear();
		searchTextbox.sendKeys(strGroupName);
		searchTextbox.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		return this;
	}

	/* Click on the Dropdown icon and Select EditGroup */
	public GroupsPage selectEditGroup(String strGroupName) {
		String xpathMoreActionsDrop = "//a[text()='" + strGroupName + "']/ancestor::tbody/tr[1]/td[last()]/span/div/a";
		driver.findElement(By.xpath(xpathMoreActionsDrop)).click();
		driver.findElement(By.xpath("//a[@title='Edit Group']")).click();
		return this;
	}
	
	/* Enter description as <value> */
	public GroupsPage enterGroupDescription(String strGroupDescription) {
		WebElement grpDescriptionTextbox = driver.findElement(By.xpath("//span[text()='Description']/parent::label/following-sibling::textarea"));
		wait.until(ExpectedConditions.visibilityOf(grpDescriptionTextbox));
		grpDescriptionTextbox.click();
		grpDescriptionTextbox.clear();
		grpDescriptionTextbox.sendKeys(strGroupDescription);
		return this;
	}
	
	/* In Information field, change the font into <Verdana> */
	public GroupsPage selectFontType(String strFontName) {
		String xpathFontName = "//span[text()='" + strFontName + "']";			//"//span[text()='Verdana']"
		driver.findElement(By.xpath("//label[text()='Font']//following::input[@name='font']")).click();
		driver.findElement(By.xpath(xpathFontName)).click();
		return this;
	}
	
	
	/* In Information field, Change the font size to <18> */
	public GroupsPage selectFontSize(String strFontSize) throws InterruptedException {
		driver.findElement(By.xpath("//label[text()='Font Size']//following::input[@name='font-size']")).click();
		String xpathFontSizeParent = "//span[text()='" + strFontSize + "']/../../..";		//"//span[text()='18']/../../.."
		WebElement elemFontSize = driver.findElement(By.xpath(xpathFontSizeParent));
		Coordinates cordFontSize = ((Locatable)elemFontSize).getCoordinates();
		cordFontSize.onPage();
		cordFontSize.inViewPort();
		Thread.sleep(1000);
		
		String xpathFontSize = "//span[text()='" + strFontSize + "']";		//"//span[text()='18']"
		driver.findElement(By.xpath(xpathFontSize)).click();
		return this;
	}
	
	/* Enter description as <value> */
	public GroupsPage selectAccessTypeAsPrivate() throws InterruptedException {
		Actions scrollDown= new Actions(driver);
	    scrollDown.sendKeys(Keys.PAGE_DOWN).perform();
	    Thread.sleep(1000);
		WebElement elemAccessTypeSelected = driver.findElement(By.xpath("//span[text()='Access Type']/../following-sibling::div/div/div/div/a"));
		wait.until(ExpectedConditions.elementToBeClickable(elemAccessTypeSelected));
		js.executeScript("arguments[0].click();",elemAccessTypeSelected);
		Thread.sleep(1000);

		driver.findElement(By.xpath("//span[text()='Access Type']/../following-sibling::div/following::a[text()='Private']")).click();
		Thread.sleep(1000);
		return this;
	}
	
	/*// 10.Click Save and Verify the Group Name"*/	
	public GroupsPage clickSaveAfterEdit() throws InterruptedException {
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("(//span[text()='Save'])[2]")));
		Thread.sleep(1000);
		return this;
	}
	
	/* Verify if edited group name is displayed correctly */
	public void verifyEditedGroupName(String strGroupName) {
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
	
	/*
	 * // Click on the link with group name strGroupName from search results
	 */
	public GroupsPage clickOnGroupAfterSearch(String strGroupName) {
		String xpathElemGroupLink = "//a[text()='" + strGroupName + "']";
		WebElement groupLinkSearched = driver.findElement(By.xpath(xpathElemGroupLink));
		wait.until(ExpectedConditions.elementToBeClickable(groupLinkSearched));
		groupLinkSearched.click();
		return this;
	}
	
	/*
	 * // Click on the Dropdown icon near the New Case
	 */
	public GroupsPage clickShowMoreActionsDrpdn() {
		String xpathElemShowActions = "//span[text()='Show more actions']/ancestor::a";
		WebElement showMoreActionsDrpdn = driver.findElement(By.xpath(xpathElemShowActions));
		wait.until(ExpectedConditions.elementToBeClickable(showMoreActionsDrpdn));
		showMoreActionsDrpdn.click();
		return this;
	}
	
	/*
	 * // Select delete group from more actions
	 */
	public GroupsPage selectDeleteGroupAction() {
		driver.findElement(By.xpath("//a[@title='Delete Group']")).click();
		return this;
	}
	
	/*
	 * // Click on the Dropdown icon near the New Case
	 */
	public GroupsPage confirmDeletion() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Delete Group']/parent::button")).click();
		Thread.sleep(1000);
		return this;
	}
	
	/*
	 * // Verify group name after deletion
	 */
	public void verifyDeletedGroupName(String strGroupName) throws InterruptedException {
		WebElement elemSuccessMsg = driver.findElement(By.xpath("//span[text()='success']/../following-sibling::div/div/span"));
		String strExpectedMsg = "Group \"" + strGroupName + "\" was deleted. ";
		Boolean boolEditGroup = elemSuccessMsg.isDisplayed();
		if (boolEditGroup) {
			String strSuccessMessage = elemSuccessMsg.getText();
			Assert.assertTrue(strSuccessMessage.contains(strExpectedMsg));
			System.out.println("Group " + strGroupName + " is deleted successfully!");
		} else if (boolEditGroup){

			System.out.println("New group " + strGroupName + " is NOT deleted successfully!");
		}
	}
	
	/*
	 * // 8.Verify the Alert message(These required fields must be completed: Access
	 * Type)""+ "
	 */	public void verifyAlertforGroupsWithoutMndtryFlds() {
		
		String expectedErrorMsg = "These required fields must be completed: Access Type";
		WebElement elemErrorMessage = driver.findElement(By.xpath("//ul[@class='errorsList']/li"));
		Boolean boolError = elemErrorMessage.isDisplayed();
		if (boolError) {
			Assert.assertEquals(elemErrorMessage.getText(), expectedErrorMsg);
			System.out.println("Error message component is displayed when mandatory fields are not provided");
			reportStep("Error message component is displayed when mandatory fields are not provided", "pass");
		} else {
			System.out.println("Error message component is NOT displayed when mandatory fields are not provided");
			reportStep("Error message component is NOT displayed when mandatory fields are not provided", "fail");
		}
	}
}
