package com.salesforce.pom.test.classes;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.salesforce.page.classes.LoginPage;
import com.salesforce.test.setup.classes.BaseMethods;

public class TNG_TC4_CreateGroupWithoutMndatoryFieldsTestClass extends BaseMethods{
	LoginPage LoginPage;

	@BeforeTest
	public void provideTestDetails() {
		testName = "Create Group";
		testDescription = "Create Group with mandatory info";
		testAuthor = "Haseena";
		testCategory = "Regression";
	}
	
  @Test
  public void testCreateGroupWithoutMndtryFlds() throws InterruptedException {
	  new LoginPage()
	  .enterUsername()
	  .enterPassword()
	  .clickLoginButton()
	  .clickToggleMenu()
	  .clickOnViewAllLink()
	  .clickOnSalesLink()
	  .clickOnGroupsTab()
	  .clickOnNewButton()
	  .enterGroupName("TestGroup")
	  .clickSaveAndNext()
	  .verifyAlertforGroupsWithoutMndtryFlds();
  }
}
