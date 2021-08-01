package com.salesforce.pom.test.classes;

import org.testng.annotations.Test;

import com.salesforce.page.classes.LoginPage;
import com.salesforce.test.setup.classes.BaseMethods;

public class TNG_TC3_DeleteGroupPomTestClass extends BaseMethods{

	@Test(dataProvider = "fetchData", dataProviderClass = BaseMethods.class)
	public void testDeleteGroup(String strGroupName) throws InterruptedException {
		new LoginPage()
		.enterUsername()
		.enterPassword()
		.clickLoginButton()
		.clickToggleMenu()
		.clickOnViewAllLink()
		.clickOnSalesLink()
		.clickOnGroupsTab()
		.searchGroupUsingName(strGroupName)
		.clickOnGroupAfterSearch(strGroupName)
		.clickShowMoreActionsDrpdn()
		.selectDeleteGroupAction()
		.confirmDeletion()
		.verifyDeletedGroupName(strGroupName);
		
	}
}
