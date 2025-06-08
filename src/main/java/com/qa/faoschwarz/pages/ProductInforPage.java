package com.qa.faoschwarz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.utils.ElementUtil;

public class ProductInforPage {

	WebDriver driver;
	ElementUtil eleUtil;

	private final By productHeader = By.tagName("h1");

	public ProductInforPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeader() {
		return eleUtil.WaitforElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();

	}

}
