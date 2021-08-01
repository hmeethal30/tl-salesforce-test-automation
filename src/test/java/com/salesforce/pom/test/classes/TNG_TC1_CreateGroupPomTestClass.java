package com.salesforce.pom.test.classes;

import java.awt.AWTException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.salesforce.page.classes.LoginPage;
import com.salesforce.test.setup.classes.BaseMethods;

public class TNG_TC1_CreateGroupPomTestClass extends BaseMethods{

	@BeforeTest
	public void provideTestDetails() {
		testName = "Create Group";
		testDescription = "Create Group with mandatory info" ;
		testAuthor = "Haseena";
		testCategory = "Regression";
	}
	
	@Test(dataProvider = "fetchData", dataProviderClass = BaseMethods.class)
	public void testCreateGroup(String strGroupName, String imageFile) throws InterruptedException, AWTException {
		new LoginPage()
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickToggleMenu()
		.clickOnViewAllLink()
		.clickOnSalesLink()
		.clickOnGroupsTab()
		.clickOnNewButton()
		.enterGroupName(strGroupName)
		.selectAccessTypeAsPublic()
		.clickSaveAndNext()
		.uploadImage(imageFile)
		.clickNext()
		.clickDone()
		.verifyGroupName(strGroupName);
	}
}
