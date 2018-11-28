package testPackage;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage; // POM class for landing page
import pageObjects.LoginPage; // POM class for login page
import resources.BaseClass;

// Extending BaseClass so that we can use its initializeDriver() method
public class validateTitle extends BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeTest
	public void initializeTest() throws IOException {
		
		// initializing WebDriver by calling custom method of BaseClass
		driver = initializeDriver();
		log.info("WebDriver is initialized");
		
		// Since we have extended BaseClass, we dodn't need to initialize
		// the Properties class again. We can just call it.
		String baseURL = prop.getProperty("url");
		driver.get(baseURL);
		log.info("Navigating to landing page");
	}
	
	@Test
	public void validateTitleTest() throws IOException, InterruptedException {
		
		try {
			// removing a subscription pop-up by clicking 'no thanks' button
			driver.findElement(By.xpath("//button[contains(text(),'NO THANKS')]")).click();
			log.info("removing a subscription pop-up by clicking 'no thanks' button");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			log.error("pop-up message was not displayed " + e.getMessage());
		}
		
		LandingPage landPg = new LandingPage(driver);
		
		// Need to compare the text from browser with the actual value 
		// Calling the Featured Courses header from landing page and getting its text
		// inside the Assert.assertEquals("expected String", "actual String") method:
		Assert.assertEquals(landPg.getFeaturedCourses().getText(),"FEATURED COURSES123"); // this will fail
		System.out.println(landPg.getFeaturedCourses().getText());
		log.info("Validating title: FEATURED COURSES");
	}
	
	@AfterTest
    public void tearDown() throws Exception {

        Thread.sleep(1500);
        driver.close();
        driver=null; // when many tests, clear all instantiated drivers from heap memory
    }
	
}
