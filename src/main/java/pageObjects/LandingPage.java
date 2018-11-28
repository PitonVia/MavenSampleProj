package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;

	// Instantiating the By class for the Login button on the landing page
	By signin = By.cssSelector("a[href*='sign_in']");
	
	// Featured Courses header in the middle of the page
	By featuredCourses = By.xpath("//h2[contains(text(),'Featured Courses')]");
	
	// Nav bar on the top of the landing page
	By navBar = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	
	// creating Constructor for WebDriver to be able to pass the 'driver' to 
	// the instances of this class:
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getLogin() {
		
		return driver.findElement(signin);
	}
	
	public WebElement getFeaturedCourses() {
		
		return driver.findElement(featuredCourses);
	}
	
	public WebElement getNavBar() {
		
		return driver.findElement(navBar);
	}
}
