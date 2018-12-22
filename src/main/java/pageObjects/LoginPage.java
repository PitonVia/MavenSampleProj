package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;

	// Creating Constructor for WebDriver to be able to pass the 'driver' from 
	// the BaseClass to the instances of this class:
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	// Email and Password fields on the login page
	By email = By.id("user_email");
	By password = By.id("user_password");
	
	// Log in button on the login page
	By login = By.name("commit");
	
	
	public WebElement getEmail() {
		
		return driver.findElement(email);
	}
	
	public WebElement getPassword() {
		
		return driver.findElement(password);
	}
	
	public WebElement getLogin() {
		
		return driver.findElement(login);
	}
	
	
}
