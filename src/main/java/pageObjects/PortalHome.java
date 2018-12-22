package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// User lands to this page after successful login from the LoginPage
public class PortalHome {
	
	public WebDriver driver;

	// Constructor to pass the 'driver' of WebDriver to the instances of this class: 
	public PortalHome(WebDriver driver) {
		this.driver = driver;
	}

	// 'Find a course' search box
	By searchBox = By.id("search-courses");
	
	public WebElement getSearchCoursesBox() {
		
		return driver.findElement(searchBox);
	}	
}
