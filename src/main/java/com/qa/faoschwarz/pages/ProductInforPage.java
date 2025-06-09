package com.qa.faoschwarz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

public class ProductInforPage {

	WebDriver driver;
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;

	private final By productHeader = By.tagName("h1");
	private final By plusButton = By.xpath(
			"//input[@id='Quantity-template--16759263133783__main7403307139159']/parent::div//button[@aria-label='Increase item quantity by one']");
	private final By quantityInput = By.xpath("//input[@type='text' and @name='quantity']");
	private String quantityJSQuarySelector = "input[name=\"quantity\"]";

	public ProductInforPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	public String getProductHeader() {
		return eleUtil.WaitforElementVisible(productHeader, AppConstants.DEFAULT_TIMEOUT).getText();

	}

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

		System.out.println("Current updated quantity :" + finalQuantity);
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
				throw new RuntimeException("Quantity did not increase after clicking plus button");
			}

			currentQuantity = newQuantity;

		}
		System.out.println("Clicked plus button attempts : " + clickCount);
		return currentQuantity;

	}

}
