package com.salesforce.page.classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupsPageFactory {
	private WebDriver driver;
	
	/* Locators */
	
	@FindBy(xpath= "//div[@title='New']/parent::a")
	WebElement newBtn;
	
	@FindBy(xpath= "//span[text()='Name']/parent::label/following-sibling::input")
	WebElement grpNameTxtbox;
	
	@FindBy(xpath= "//span[text()='Access Type']/following::a[1]")
	WebElement accessTypeDrpdwn;

	@FindBy(xpath= "//a[@title='Public")
	WebElement accessTypePublic;
	
	@FindBy(xpath= "//span[text()='Save & Next']/parent::button")
	WebElement saveBtn;
	
	@FindBy(xpath= "//button[text()='Upload Image']")
	WebElement imageUploadBtn;
	
	@FindBy(xpath= "//span[text()='Drag and resize to adjust thumbnail.']")
	WebElement imageUploadIcon;
	
	@FindBy(xpath= "//span[text()='Next']")
	WebElement nextBtn;
	
	@FindBy(xpath= "//span[text()='Done']")
	WebElement doneBtn;

	//Constructor
	public GroupsPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
