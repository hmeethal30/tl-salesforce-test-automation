package cucumber.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber/features/Login.feature", glue="cucumber/steps")
public class RunTest extends AbstractTestNGCucumberTests {
	
}
