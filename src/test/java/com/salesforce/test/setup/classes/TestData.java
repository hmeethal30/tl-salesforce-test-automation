package com.salesforce.test.setup.classes;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.salesforce.utilities.classes.ReadExcel;

public class TestData {
	
	@DataProvider(name = "fetchData")
	public String[][] sendData(String sheetName, String tableName) throws IOException {
  	    String strFile = Constants.File_Path+Constants.File_Name;
		return ReadExcel.readData(strFile, sheetName);}

}
