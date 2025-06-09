package com.qa.faoschwarz.base;

import java.util.Properties;

import com.qa.faoschwarz.pages.SearchResultsPage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.faoschwarz.api.GetProductListAPI;
import com.qa.faoschwarz.factory.DriverFactory;
import com.qa.faoschwarz.pages.CartSliderPage;
import com.qa.faoschwarz.pages.HomePage;
import com.qa.faoschwarz.pages.ProductInforPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	Properties prop;
	
	protected HomePage homePage;
	protected SearchResultsPage searchResultsPage;
	protected GetProductListAPI getProductListApi;
	protected ProductInforPage productInforPage;
	protected CartSliderPage cartSliderPage;
	
	@BeforeTest
	public void setup() {
		df=new DriverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		
		homePage=new HomePage(driver);
		
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
