package com.qa.faoschwarz.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.faoschwarz.exceptions.BrowserException;
import com.qa.faoschwarz.exceptions.FrameworkException;
import com.qa.faoschwarz.utils.JavaScriptUtil;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static final Logger log=LogManager.getLogger(DriverFactory.class);

	/**
	 * This method is used to initialize the driver on the basis of given browser.
	 * Here initialize thread Local driver to create local copy of each and every
	 * driver.(So thread safe)
	 * 
	 * @param browserName
	 */
	public WebDriver initDriver(Properties prop) {
		
		log.info("properties" + prop);

		String browserName = prop.getProperty("browser");
        log.info("Browser Name :" + browserName);
        optionsManager = new OptionsManager(prop);


		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;
		default:
			
			log.error("Please pass the valid browser name.." + browserName);
			throw new BrowserException("===INVALID BROWSER===");

		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();

		return getDriver();
	}

	/**
	 * getDriver:get the local copy of the driver
	 * 
	 * @return
	 */

	public static WebDriver getDriver() {
		return tlDriver.get();

	}

	/**
	 * this is used to init the config properties
	 * 
	 * @return
	 */

	public Properties initProp() {

		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();
		try {
			if (envName == null) {
				log.warn("env is null,hence running the tests on PROD env by defualt..");
				ip = new FileInputStream("src/test/resources/config/config.properties");

			} else {
				System.out.println("Running tests on env" + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("src/test/resources/config/qa.config.properties");
					break;
				case "stg":
					ip = new FileInputStream("src/test/resources/config/stg.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("src/test/resources/config/config.properties");
					break;

				default:
					log.error("----Invalid env name--------" + envName);
					throw new FrameworkException("===INVALID ENV NAME===" + envName);

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}

		return prop;

	}

	public static File getScreenshotFile() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;

	}

}
