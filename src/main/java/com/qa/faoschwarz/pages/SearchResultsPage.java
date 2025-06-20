package com.qa.faoschwarz.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.factory.DriverFactory;
import com.qa.faoschwarz.utils.ElementUtil;
import com.qa.faoschwarz.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {

	WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	
	public static Logger log=LogManager.getLogger(SearchResultsPage.class);
	
	private final By resultsProduct = By.xpath("//div[@class='kuResults']//li");
	private final By sortByDropdown = By.cssSelector(".kuDropSortBy");
	private final By priceAsc = By.xpath("//div[@data-value='PRICE_ASC']");
	private final By productPrice = By.xpath("//span[contains(@class, \"kuSalePrice\")]");


	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	@Step("getting all the products counts")
	public int getResultsProdutCount() {
		int searchCount = eleUtil.WaitForAllElementsVisible(resultsProduct, AppConstants.MEDIUM_DEFAULT_TIMEOUT).size();
		log.info("Total number of search products through UI: " + searchCount);
		return searchCount;
	}

	@Step("sort all the products by price ascending order")
	public void doSortByAsc() {
		eleUtil.doClickWithWait(sortByDropdown, AppConstants.MEDIUM_DEFAULT_TIMEOUT);

		List<Double> priceListBeforeSort = getProductPriceData();
		log.info("Actual product price list before sort" + priceListBeforeSort);

		eleUtil.doClickWithWait(priceAsc, AppConstants.LONG_DEFAULT_TIMEOUT);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Double> priceListAfterSort = getProductPriceData();
		log.info("Actual product price list after sort" + priceListAfterSort);

	}
	
	
	private List<Double> getProductPriceData() {

		List<Double> prices = new ArrayList<>();
		List<WebElement> productPriceList = getWebElementList();

		for (WebElement ele : productPriceList) {
			String productvalue = ele.getText().replace("$", "").trim();
			double price = Double.parseDouble(productvalue);

			if (price > 0) {
				prices.add(price);
			}

		}

		return prices;
	}


	private List<WebElement> getWebElementList() {
		List<WebElement> priceList;
		try {

			priceList = eleUtil.WaitForAllElementsVisible(productPrice, AppConstants.DEFAULT_TIMEOUT);
			return priceList;

		} catch (StaleElementReferenceException e) {

			return eleUtil.WaitForAllElementsVisible(productPrice, AppConstants.DEFAULT_TIMEOUT);
		}
	}

	
	@Step("checking products price list is sorted by ascending")
	public boolean isProductSortedAscending() {

		List<Double> actualPrices = getProductPriceData();
		boolean isSorted = false;

		for (int i = 0; i < actualPrices.size() - 1; i++) {
			double currentPrice = actualPrices.get(i);
			double nextPrice = actualPrices.get(i + 1);

			if (currentPrice > nextPrice) {
				System.out.println("Products are not sorted in ascending order");
				return isSorted;
			}

		}
		log.info("Products are sorted in ascending order");
		return isSorted = true;

	}

	@Step("select a :{0} product and navigate to the product information page")
	public ProductInforPage selectProduct(String productName) {
		log.info("product name : " + productName);
		WebElement productNameElement = eleUtil.getElement(By.partialLinkText(productName));
		jsUtil.clickElementByJS(productNameElement);
		return new ProductInforPage(driver);
	}
}
