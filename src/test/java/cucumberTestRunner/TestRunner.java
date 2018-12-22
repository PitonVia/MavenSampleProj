package cucumberTestRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features/Login.feature", 
		glue = "stepDefinitions")

// If you want to execute all features, provide path until features pkg only:
// features = "src/test/java/features"

// stepDefinitions package has to be located in the src/test/java/ 

public class TestRunner extends AbstractTestNGCucumberTests{
	
}
