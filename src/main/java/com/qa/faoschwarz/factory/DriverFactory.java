package com.qa.faoschwarz.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.faoschwarz.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;

	/**
	 * This method is used to initialize the driver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName=prop.getProperty("browser");

		System.out.println("Browser Name :" + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Please pass the valid browser name.." + browserName);
			throw new BrowserException("===INVALID BROWSER===");

		}
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
	}
	
	/**
	 * this is used to init the config properties
	 * @return
	 */
	
	public Properties initProp() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}

}
