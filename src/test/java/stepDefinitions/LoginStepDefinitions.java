package stepDefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import pageObjects.PortalHome;
import resources.BaseClass;

// Note that we are inheriting from the BaseClass where WebDriver is declared!
public class LoginStepDefinitions extends BaseClass {

	// This is required for the log4j
	private static final Logger log = LogManager.getLogger(BaseClass.class.getName());
	
	@Given("^Open Chrome browser$")
	public void open_Chrome_browser() throws Throwable {
		// initializing WebDriver by calling custom method of the BaseClass
		driver = initializeDriver(); 
	}

	@Given("^Navigate to the \"([^\"]*)\" website Home page$")
	public void navigate_to_the_website_Home_page(String arg1) throws Throwable {
		// Note that now we don't need to get the BaseURL from the data.properties file.
		// The URL is specified in the feature file and passed to this method as an argument.
		driver.get(arg1);
	}

	@Given("^Click on the Login button on Home page to land on Sign in page$")
	public void click_on_the_Login_button_on_Home_page_to_land_on_Sign_in_page() throws Throwable {
		// Locators and getters for the landing page are in the LandingPage class (POM).
		
		// Note how we are passing the initialized WebDriver from the BaseClass (as it 
		// works with the ChromeDriver) into the new instance of the LandingPage class!!!
		
		// Then, the initialized WebDriver from the BaseClass will be executing all the 
		// methods inside the LandingPage class. 

		// If the initialized driver is not passed to the instance of the LandingPage class
		// as an argument, there will be a NullPointerException returned for the driver!

		LandingPage landPg = new LandingPage(driver);
		
		// First, need to handle the subscription pop-up by clicking 'no thanks' button.
		// Note that in the LandingPage class we have 2 getters for the NoThanks button,
		// one is for the WebElement and another is for the int size of List<WebElements>.
		if (landPg.getNoThanksListSize() > 0) {
			landPg.getNoThanks().click();
			log.info("removing a subscription pop-up by clicking 'no thanks' button");
		} else {
			log.error("pop-up message was not displayed ");
		}
		
		// invokes method to get WebElement for login button and clicks on it	
		landPg.getLogin().click(); 
	}

	@When("^User inputs valid user name (.+) and password (.+) and clicks on the login button$")
    public void user_inputs_valid_user_name_and_password_and_clicks_on_the_login_button(String username, String password) throws Throwable {

		// Locators and getters for the login page are in the LoginPage class (POM)
		LoginPage logPg = new LoginPage(driver);
		// Again, note how we are passing the user name and password values from the Feature 
		// file, i.e., not from the TestNG @DataProvider
		logPg.getEmail().sendKeys(username);
		Thread.sleep(1000);
		logPg.getPassword().sendKeys(password);
		Thread.sleep(1000);
		logPg.getLogin().click();
		Thread.sleep(1000);
    }

	@Then("^Verify that user successfully logs in$")
	public void verify_that_user_successfully_logs_in() throws Throwable {
	    PortalHome phPg = new PortalHome(driver);
	    // Verifying if the search courses box is displayed.
	    // If it is not displayed, Assertion will fail the script. 
	    Assert.assertTrue(phPg.getSearchCoursesBox().isDisplayed());
	}
	
    @And("^Close the browsers$")
    public void close_the_browsers() throws Throwable {
        driver.quit();
    }
   
}
