package com.qa.faoschwarz.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.exceptions.BrowserException;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;
import io.qameta.allure.Step;

public class CartSliderPage {

	WebDriver driver;
	ElementUtil eleUtil;
	JavaScriptUtil jsUtil;
	
	public static Logger log=LogManager.getLogger(CartSliderPage.class);

	private final By CartSliderHeader = By.xpath("//div[@id='CartDrawer']//div[@class='h2 drawer__title']");
	private final By cartItems = By.xpath("//div[@class='cart__items']");
	private final By subTotal = By.xpath("//div[contains(@class,'ajaxcart__subtotal')]/following-sibling::div");
	private final By framelocator = By.xpath("//iframe[@id='chat-button' and @title='Gorgias live chat messenger']");
	private final By chatIcon = By.xpath("//button[@id='gorgias-chat-messenger-button']");

	public CartSliderPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);

	}

	@Step("getting cart slider header")
	public String getCartSliderHeader() {
		return eleUtil.WaitforElementVisible(CartSliderHeader, AppConstants.DEFAULT_TIMEOUT).getText();

	}
 
	@Step("checking displayed :{0} product quantity of the cart")
	public int getProductQuantity(String productname) {

		List<WebElement> itemsCountList = eleUtil.WaitForAllElementsVisible(cartItems, AppConstants.DEFAULT_TIMEOUT);

		for (WebElement item : itemsCountList) {
			try {

				WebElement product = item.findElement(
						By.xpath(".//div[contains(@class,'cart__item-title') and contains(normalize-space(.),'"
								+ productname + "')]"));
				if (product.getText().contains(productname)) {
					String quantityInput = item
							.findElement(By
									.xpath("//div[contains(@class,'cart__item-title') and contains(normalize-space(.),'"
											+ productname + "')]/following-sibling::div//input[@class='js-qty__num']"))
							.getDomAttribute("value");
					log.info("Quantity display in cartSlider: " + productname);
					return Integer.parseInt(quantityInput);
				}
			} catch (NoSuchElementException e) {
				log.info("Quantity input not found for product: " + productname);

			}
		}

		return 0;

	}
	
	@Step("calculate all item lists price total")
	public double calculateCartTotal() {

		List<WebElement> itemsCountList = eleUtil.WaitForAllElementsVisible(cartItems, AppConstants.DEFAULT_TIMEOUT);

		log.info("=== SHOPPING CART CALCULATION ===");
		double subTotal = 0.0;

		for (WebElement item : itemsCountList) {
			try {

				String productName = getItemName(item);

				int quantity = getQuantityOfEachProduct(item);
				double unitPrice = getUnitPriceOfItem(item);

				double totalOfItem = quantity * unitPrice;
				subTotal += totalOfItem;

				log.info("Product: " + productName);
				log.info("Quantity: " + quantity);
				log.info("Unit Price: $" + unitPrice);
				log.info("Item Total: $" + totalOfItem);

			} catch (Exception e) {
				log.warn("=====Please check calculation==========");
				throw new BrowserException("===CHECK CALCULATION===");
			}

		}
		log.info("Sub Total: $" + subTotal);
		return subTotal;

	}

	private int getQuantityOfEachProduct(WebElement item) {

		List<WebElement> itemPriceSelectorList = item.findElements(By.xpath(
				"//div[contains(@class,'cart__item-title')]/following-sibling::div//input[@class='js-qty__num']"));

		for (WebElement selectorPrice : itemPriceSelectorList) {
			try {
				String itemQuantity = selectorPrice.getDomAttribute("value");

				if (!itemQuantity.isEmpty()) {
					int itemQnt = Integer.parseInt(itemQuantity.trim());
					return itemQnt;
				}
			} catch (Exception e) {
				throw new BrowserException("==No Quantity Found===");

			}
		}
		return 0;

	}

	private double getUnitPriceOfItem(WebElement item) {

		List<WebElement> itemPriceSelectorList = item.findElements(By.xpath(
				"//div[contains(@class,'cart__item-title')]/following-sibling::div//div[contains(@class,'cart__item-price-col')]/span"));

		for (WebElement selectorPrice : itemPriceSelectorList) {
			try {
				String itemPriceVal = selectorPrice.getText();

				if (itemPriceVal != null && !itemPriceVal.isEmpty()) {
					double itemPrice = Double.parseDouble(itemPriceVal.replace("$", "").trim());
					return itemPrice;
				}
			} catch (Exception e) {
				throw new BrowserException("==No Price Found===");

			}
		}
		return 0.0;

	}

	private String getItemName(WebElement item) {

		List<WebElement> nameSelectorList = item.findElements(By.xpath(".//div[contains(@class,'cart__item-title')]"));
		try {

			for (WebElement selectorname : nameSelectorList) {
				try {
					String itemName = selectorname.getText().trim();
					if (!itemName.isEmpty()) {
						return itemName;
					}
				} catch (Exception ignored) {
				}
			}
		} catch (Exception e) {
			return "No products found";
		}
		return "No products found";

	}

	@Step("getting displayed sub total of the cart")
	public double getActualSubTotal() {

		WebElement subTotalElement = eleUtil.WaitforElementVisible(subTotal, AppConstants.MEDIUM_DEFAULT_TIMEOUT);

		String subTotalVal = subTotalElement.getText().replace("$", "").trim();
		log.info("Sub Total: $" + subTotalVal);

		return Double.parseDouble(subTotalVal);

	}

	@Step("checking chatIcon is displayed on the cart slider")
	public boolean isChatIconDisplayOnCartSlider() {
		try {

			log.info("Switching to iframe...");

			eleUtil.waitForFrameAndSwitchToIt(framelocator, AppConstants.DEFAULT_TIMEOUT);
			log.info("Successfully switched to iframe");

			Thread.sleep(2000);

			WebElement chatIconElement = eleUtil.WaitforElementVisible(chatIcon, AppConstants.DEFAULT_TIMEOUT);

			return chatIconElement.isDisplayed();

		} catch (Exception e) {
			throw new BrowserException("===Chat icon not displayed========");
		} finally {

			driver.switchTo().defaultContent();
		}

	}
}
