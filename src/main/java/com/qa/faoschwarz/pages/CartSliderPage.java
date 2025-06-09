package com.qa.faoschwarz.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

public class CartSliderPage {

	WebDriver driver;
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;

	private final By CartSliderHeader = By.xpath("//div[@id='CartDrawer']//div[@class='h2 drawer__title']");
	private final By cartItems = By.xpath("//div[@class='cart__items']");

	public CartSliderPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	public String getCartSliderHeader() {
		return eleUtil.WaitforElementVisible(CartSliderHeader, AppConstants.DEFAULT_TIMEOUT).getText();

	}

	public int getProductQuantity(String productname) {

		List<WebElement> itemsCountList = eleUtil.WaitForAllElementsVisible(cartItems, AppConstants.DEFAULT_TIMEOUT);

		for (WebElement item : itemsCountList) {
			try {
				
				WebElement product = item.findElement(
						By.xpath(".//div[contains(@class,'cart__item-title') and contains(normalize-space(.),'"
								+ productname + "')]"));
				if (product.getText().contains(productname)) {
					String quantityInput = item.findElement(By.xpath(
							"//div[contains(@class,'cart__item-title') and contains(normalize-space(.),'"+productname+"')]/following-sibling::div//input[@class='js-qty__num']"))
							.getDomAttribute("value");
					System.out.println("Quantity display in cartSlider: " + productname);
					return Integer.parseInt(quantityInput);
				}
			} catch (NoSuchElementException e) {
				System.out.println("Quantity input not found for product: " + productname);

			}
		}

		return 0;

	}
}
