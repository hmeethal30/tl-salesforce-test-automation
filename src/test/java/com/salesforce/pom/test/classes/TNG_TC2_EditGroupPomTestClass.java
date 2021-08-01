package com.salesforce.pom.test.classes;

import org.testng.annotations.Test;

import com.salesforce.page.classes.LoginPage;
import com.salesforce.test.setup.classes.BaseMethods;

public class TNG_TC2_EditGroupPomTestClass extends BaseMethods {

	@Test(dataProvider = "fetchData", dataProviderClass = BaseMethods.class)
	public void testEditGroup(String strGroupName, String strGroupDescription, String strFontName, String strFontSize) throws InterruptedException {
		new LoginPage()
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickToggleMenu()		// 2. Click on toggle menu button from the left corner
		.clickOnViewAllLink()	// 3. Click view All and click Sales from App Launcher
		.clickOnSalesLink()		// 4. Click on Groups tab 
		.clickOnGroupsTab()
		.searchGroupUsingName(strGroupName)
		.selectEditGroup(strGroupName)
		.enterGroupDescription(strGroupDescription)
		.selectFontType(strFontName)
		.selectFontSize(strFontSize)
		.selectAccessTypeAsPrivate()
		.clickSaveAfterEdit()
		.verifyEditedGroupName(strGroupName);;	
	}
}
