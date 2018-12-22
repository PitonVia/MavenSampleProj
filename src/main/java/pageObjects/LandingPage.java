package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	
	public WebDriver driver;

	// Creating Constructor for WebDriver to be able to pass the 'driver' from 
	// the BaseClass to the instances of this class:
	public LandingPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Instantiating the By class for the Login button on the landing page
	By signin = By.cssSelector("a[href*='sign_in']");
	
	// Featured Courses header in the middle of the page
	By featuredCourses = By.xpath("//h2[contains(text(),'Featured Courses')]");
	
	// Nav bar on the top of the landing page
	By navBar = By.xpath("//ul[@class='nav navbar-nav navbar-right']");
	
	// No thanks button on the occasional subscription pop-up window
	By noThanks = By.xpath("//button[contains(text(),'NO THANKS')]");


	public WebElement getLogin() {
		
		return driver.findElement(signin);
	}	
	public WebElement getFeaturedCourses() {
		
		return driver.findElement(featuredCourses);
	}
	public WebElement getNavBar() {
		
		return driver.findElement(navBar);
	}
	public WebElement getNoThanks() {
		return driver.findElement(noThanks);
	}
	public int getNoThanksListSize() {
		return driver.findElements(noThanks).size();
	}
}
