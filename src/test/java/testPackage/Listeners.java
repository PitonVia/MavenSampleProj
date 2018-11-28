package testPackage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import resources.BaseClass;

public class Listeners implements ITestListener{

	private static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	BaseClass bc = new BaseClass();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub	
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	// When test fails the following code will be executed:
	public void onTestFailure(ITestResult result) {
		
		// print the name of the failed test
		result.getName();
		
		log.info("Test case failed");
		try {
			// want to name the screenshot file with the name of the failed test
			bc.getScreenshot(result.getName());
		} catch (IOException e) {
			log.error("Failed to tack screenshot after test failed");
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
