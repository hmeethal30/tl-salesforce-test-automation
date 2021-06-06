package com.salesforce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardsPage {
	WebDriver driver;

	//Locators
	
	//Serch dashbord input box
	@FindBy(how=How.XPATH, using = "//label[text()='Search recent dashboards...']/../div/input")
	private WebElement iptSearchDBoard;
	
	//Dashboard actions dropdown arrow
	@FindBy(how=How.XPATH, using = "//a[@title='Salesforce Automation by Haseena']/ancestor::th/following-sibling::td[last()]/following::button)[1]")
	private WebElement btnDBoardActions;
	
	//Edit option
	@FindBy(how=How.XPATH, using = "//span[text()='Edit'])[1]")
	private WebElement optEdit;
	
	//iframe
	@FindBy(how=How.XPATH,using = "//iframe[@title='dashboard']") 
	private WebElement ifrmDashboard;
	
	//Edit dashboard iccon/button
	@FindBy(how=How.XPATH, using = "//span[text()='Edit Dashboard name']/parent::button")
	private WebElement icnEditDashboard;
	
	//Edit dashboard name input box
	@FindBy(how=How.XPATH, using = "//input[@id='edit-dashboard-title']")
	private WebElement iptEditDBoardName;
	
	

}
