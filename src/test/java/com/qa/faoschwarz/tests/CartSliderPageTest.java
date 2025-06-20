package com.qa.faoschwarz.tests;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;
import com.qa.faoschwarz.constants.AppConstants;
import com.qa.faoschwarz.utils.CSVUtil;
import com.qa.faoschwarz.utils.ExcelUtil;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Feature("FAOschwarz-Cart Feature")
@Owner("Thamali")
public class CartSliderPageTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getProductQuantityTestData() {
		return new Object[][] { 
			{ "cars", "Ambulance", "3" },

		};

	}
	
	@DataProvider
	public Object[][] getProductCSVTestData() {
		return CSVUtil.csvData("product");

	}
	
	@DataProvider
	public Object[][] getProductExcelTestData() {
		Object productdata[][]= ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		return productdata;

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider loaded properly", dataProvider = "getProductCSVTestData")
	public void cartSliderHeaderTest(String searchProduct, String productName, String desiredQuantity) {
		
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(Integer.parseInt(desiredQuantity));
		
		cartSliderPage=productInforPage.addProductToCart();	
		String actCartSliderHeader=cartSliderPage.getCartSliderHeader();
		
		Assert.assertEquals(actCartSliderHeader, "Cart");

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider quantity is matched with selected quantity", dataProvider = "getProductExcelTestData")
	public void cartSliderQuantityTest(String searchProduct, String productName, String desiredQuantity) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		int actQuantity=productInforPage.increaseQantityWithPlusButton(Integer.parseInt(desiredQuantity));
		
		cartSliderPage=productInforPage.addProductToCart();	
		int currentQuantity=cartSliderPage.getProductQuantity(productName);
		Assert.assertEquals(actQuantity, currentQuantity);
		
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider subtotal is matched with calculated total", dataProvider = "getProductQuantityTestData")
	public void cartSliderSubTotalTest(String searchProduct, String productName, String desiredQuantity){
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(Integer.parseInt(desiredQuantity));
		
		cartSliderPage=productInforPage.addProductToCart();	
		double expectedSubtotal=cartSliderPage.calculateCartTotal();
		double actualSubtotal=cartSliderPage.getActualSubTotal();
		
		Assert.assertEquals(actualSubtotal, expectedSubtotal);
		
		
	}
	
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verify chatIcon displays on the cartslider popup", dataProvider = "getProductQuantityTestData")
	public void validateChatIcon(String searchProduct, String productName, String desiredQuantity){
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(Integer.parseInt(desiredQuantity));
		
		cartSliderPage=productInforPage.addProductToCart();	
		
		Assert.assertTrue(cartSliderPage.isChatIconDisplayOnCartSlider());
		
		
	}

}
