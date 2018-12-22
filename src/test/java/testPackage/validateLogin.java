package testPackage;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LandingPage; // POM class for landing page
import pageObjects.LoginPage; // POM class for login page
import resources.BaseClass;

// Extending BaseClass so that we can use its initializeDriver() method
public class validateLogin extends BaseClass {

	private static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@BeforeTest
	public void initializeTest() throws IOException, InterruptedException {
		
/*		// Another way to call class from another class is to initialize it
		BaseClass bc = new BaseClass();
		driver = bc.initializeDriver();
		In this case, the class will be imported automatically */
		
		// initializing WebDriver by calling custom method of BaseClass
		driver = initializeDriver(); 
	
	}
	
	@Test(dataProvider="getLoginData") // this test will be run twice
	public void validateLoginTest(String email, String password) throws InterruptedException {
		
		// Since we have extended BaseClass, we dodn't need to initialize
		// the Properties class again. We can just call it.
		String baseURL = prop.getProperty("url");
		driver.get(baseURL);
		
		try {
			// removing a subscription pop-up by clicking 'no thanks' button
			driver.findElement(By.xpath("//button[contains(text(),'NO THANKS')]")).click();
			log.info("removing a subscription pop-up by clicking 'no thanks' button");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			log.error("pop-up message was not displayed " + e.getMessage());
		}
				
		LandingPage landPg = new LandingPage(driver);
		landPg.getLogin().click(); // invokes method to click on the login button
		
		LoginPage logPg = new LoginPage(driver);
		logPg.getEmail().sendKeys(email);
		Thread.sleep(2000);
		logPg.getPassword().sendKeys(password);
		Thread.sleep(2000);
		logPg.getLogin().click();
		Thread.sleep(2000);
		
	}
	
	@DataProvider
	public Object[][] getLoginData() {
		
		// first[] - how many tests
		// 2-nd[] - how many variables
		Object[][] data = new Object[2][2]; // 2 tests & 2 vars
		// test # 1 - note array index starts from 0 
		data[0][0]="nonrestricteduser@qw.com";
		data[0][1]="12345";
		// test # 2
		data[1][0]="restricteduser@qw.com";
		data[1][1]="56789";
		
		return data;
	}


	@AfterTest
    public void tearDown() throws Exception {

        Thread.sleep(2000);
        driver.close();
        driver=null; // when many tests, clear all instantiated drivers from heap memory
    }

}
