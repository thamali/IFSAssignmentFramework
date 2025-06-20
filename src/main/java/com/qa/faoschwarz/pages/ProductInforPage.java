package com.qa.faoschwarz.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.exceptions.BrowserException;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class ProductInforPage {

	WebDriver driver;
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	
	public static Logger log=LogManager.getLogger(ProductInforPage.class);

	private final By productHeader = By.tagName("h1");
	private final By plusButton = By.xpath(
			"//input[@id='Quantity-template--16759263133783__main7403307139159']/parent::div//button[@aria-label='Increase item quantity by one']");
	private final By quantityInput = By.xpath("//input[@type='text' and @name='quantity']");
	private String quantityJSQuarySelector = "input[name=\"quantity\"]";
	private final By addToCartBtn=By.cssSelector("#AddToCart-");

	public ProductInforPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	@Step("getting a selected product header")
	public String getProductHeader() {
		return eleUtil.WaitforElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();

	}

	@Step("Increasing item of the quantity up to : {0}")
	public int increaseQantityWithPlusButton(int desiredQuantity) {

		int targetQuantity = desiredQuantity;
		System.out.println("Target quantity: " + targetQuantity);

		WebElement plusButtonElement = eleUtil.WaitforElementVisible(plusButton, AppConstants.DEFAULT_TIMEOUT);

		int finalQuantity = 0;
		try {

			finalQuantity = AddQuantityController(targetQuantity, plusButtonElement);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    log.info("Current updated quantity :" + finalQuantity);
		return finalQuantity;
	}

	
	private int AddQuantityController(int targetQuantity, WebElement plusBtn) throws InterruptedException {

		String initialQuantity = jsUtil.getElementValueByJS(quantityJSQuarySelector);

		int currentQuantity = Integer.parseInt(initialQuantity);
		int clickCount = 0;

		while (currentQuantity < targetQuantity) {
			plusBtn.click();
			clickCount = (currentQuantity + 1);

			Thread.sleep(300);

			String updatedQuantity = jsUtil.getElementValueByJS(quantityJSQuarySelector);

			int newQuantity = Integer.parseInt(updatedQuantity);

			if (newQuantity <= currentQuantity) {
				throw new BrowserException("Quantity did not increase after clicking plus button");
			}

			currentQuantity = newQuantity;

		}
		log.info("Clicked plus button attempts : " + clickCount);
		return currentQuantity;

	}
	
	@Step("add to cart and navigate to cart slider page")
	public CartSliderPage addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		
		return new CartSliderPage(driver);
		
	}

}
