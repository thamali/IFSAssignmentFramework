package com.qa.faoschwarz.base;

import java.util.Properties;

import com.qa.faoschwarz.pages.SearchResultsPage;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.qa.faoschwarz.api.GetProductListAPI;
import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.exceptions.BrowserException;
import com.qa.faoschwarz.factory.DriverFactory;
import com.qa.faoschwarz.listeners.TestAllureListener;
import com.qa.faoschwarz.pages.CartSliderPage;
import com.qa.faoschwarz.pages.HomePage;
import com.qa.faoschwarz.pages.ProductInforPage;

public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	Properties prop;
	JavaScriptUtil js;
	ElementUtil eleUtil;


	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected GetProductListAPI getProductListApi;
	protected ProductInforPage productInforPage;
	protected CartSliderPage cartSliderPage;

	@Parameters({ "browser" })
	@BeforeMethod
	public void setup(String browserName) {
			
		df = new DriverFactory();
		prop = df.initProp();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		driver = df.initDriver(prop);

		js = new JavaScriptUtil(driver);
		eleUtil = new ElementUtil(driver);
	

		js.injectPopupBlockingCSS();
		js.removePopupElements();
		js.disablePopupWithJavaScript();

		acceptCookies();
		
		homePage = new HomePage(driver);
		

	}
	
	private void acceptCookies() {
		 	
		try {

			final By acceptCookieBtn = By.xpath("//button[@id='onetrust-accept-btn-handler']");
			WebElement btn = eleUtil.getElement(acceptCookieBtn);
			btn.click();

		} catch (Exception e) {
			throw new BrowserException("==Cookie banner not present====");
		}
	}
	
}
