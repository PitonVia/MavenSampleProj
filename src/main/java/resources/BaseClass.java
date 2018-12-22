package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {

	// Declaring driver as a global variable accessible to all methods from outside.
	// It is static so it pertains to this class only and cannot be changed
	public static WebDriver driver;
	// Declaring an instance of the Properties class as a public global variable:
	public Properties prop;
	
	// Created data.properties file located in the main Package
	// This file will contain global variables:  browser type, etc.
	
	// Returns initialized driver, selects browser based on the value in properties file
	public WebDriver initializeDriver() throws IOException {
		
		prop = new Properties();
		String propLocation = "D:\\Yo\\QA\\JAVA\\eclipse-workspace\\MavenSampleProj\\src\\main\\java\\resources\\data.properties";
		
		//This opens channel to the location of the Properties file
		FileInputStream stream = new FileInputStream(propLocation);
				
		// Loading the file
		prop.load(stream);
		
		String browserType = prop.getProperty("browser");
		
		if (browserType.equals("chrome")) {
			
			ChromeOptions options = new ChromeOptions();
	        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome Dev\\Application\\chrome.exe");
	        System.setProperty("webdriver.chrome.driver", "D:\\Yo\\QA\\Automation\\Selenium_java_3.14.0\\BrowserDrivers\\chromedriver.exe");
	        driver = new ChromeDriver(options);
			
		} else if (browserType.equals("firefox")) {

			String ffPropPath = "D:\\Yo\\QA\\Automation\\Selenium_java_3.14.0\\BrowserDrivers\\geckodriver.exe";
			
			System.setProperty("webdriver.gecko.driver", ffPropPath);
	        driver = new FirefoxDriver();
			
		} else if (browserType.equals("IE")) {
			
			String iePropPath = "D:\\Yo\\QA\\Automation\\Selenium_java_3.14.0\\BrowserDrivers\\IEDriverServer.exe";

	        System.setProperty("webdriver.ie.driver", iePropPath);
	        driver = new InternetExplorerDriver();	
		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		return driver;
	}

	// method for taking screenshots of failed tests that takes a result String
	// from the listener when test fails. result will = name of failed test.
	public void getScreenshot(String result) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("D://Yo//QA//JAVA//eclipse-workspace//MavenSampleProj//screenshots//" + result + "screenshot.png"));
	}

}
