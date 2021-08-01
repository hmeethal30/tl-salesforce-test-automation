package extentreport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentTestReport {

	public static void main(String[] args) {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		
		reporter.setAppendExisting(true);
		
		ExtentReports extent = new ExtentReports();
		
		extent.attachReporter(reporter);
		
		ExtentTest testcase1 = extent.createTest("CreateGroup", "Create Group with mandatory fields");
		testcase1.assignAuthor("Haseena");
		testcase1.assignCategory("Regression");
		
		testcase1.fail("Group created successfully");
		
		extent.flush();
	}

}
