package testPackage;

import java.io.IOException;

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
public class validateNavBar extends BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeTest
	public void initializeTest() throws IOException {

		// initializing WebDriver by calling custom method of BaseClass
		driver = initializeDriver(); 
		
		// Since we have extended BaseClass, we dodn't need to initialize
		// the Properties class again. We can just call it.
		String baseURL = prop.getProperty("url");
		driver.get(baseURL);	
	}
	
	@Test
	public void validateNavBarTest() throws IOException, InterruptedException {
			
		try {
			// removing a subscription pop-up by clicking 'no thanks' button
			driver.findElement(By.xpath("//button[contains(text(),'NO THANKS')]")).click();
			log.info("removing a subscription pop-up by clicking 'no thanks' button");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			log.error("pop-up message was not displayed " + e.getMessage());
		}
		
		LandingPage landPg = new LandingPage(driver);
			
		// Check if Navbar on the landing page is available
		Assert.assertTrue(landPg.getNavBar().isDisplayed());
		System.out.println("navBar is displayed?  " + landPg.getNavBar().isDisplayed());
	}
	
	@AfterTest
    public void tearDown() throws Exception {

        Thread.sleep(1500);
        driver.close();
        driver=null; // when many tests, clear all instantiated drivers from heap memory
    }
	
}
