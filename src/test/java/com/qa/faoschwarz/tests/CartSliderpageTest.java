package com.qa.faoschwarz.tests;

import org.testng.Assert;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;

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
			{ "cars", "Ambulance", 3 },

		};

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider loaded properly", dataProvider = "getProductQuantityTestData")
	public void CartSliderHeaderTest(String searchProduct, String productName, int desiredQuantity) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		
		cartSliderPage=productInforPage.addProductToCart();	
		String actCartSliderHeader=cartSliderPage.getCartSliderHeader();
		
		Assert.assertEquals(actCartSliderHeader, "Cartz");

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider quantity is matched with selected quantity", dataProvider = "getProductQuantityTestData")
	public void CartSliderQuantityTest(String searchProduct, String productName, int desiredQuantity) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		int actQuantity=productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		
		cartSliderPage=productInforPage.addProductToCart();	
		int currentQuantity=cartSliderPage.getProductQuantity(productName);
		Assert.assertEquals(actQuantity, currentQuantity);
		
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify cartSlider subtotal is matched with calculated total", dataProvider = "getProductQuantityTestData")
	public void CartSliderSubTotalTest(String searchProduct, String productName, int desiredQuantity){
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		
		cartSliderPage=productInforPage.addProductToCart();	
		double expectedSubtotal=cartSliderPage.calculateCartTotal();
		double actualSubtotal=cartSliderPage.getActualSubTotal();
		
		Assert.assertEquals(actualSubtotal, expectedSubtotal);
		
		
	}
	
	
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verify chatIcon displays on the cartslider popup", dataProvider = "getProductQuantityTestData")
	public void validateChatIcon(String searchProduct, String productName, int desiredQuantity){
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		
		cartSliderPage=productInforPage.addProductToCart();	
		
		Assert.assertTrue(cartSliderPage.isChatIconDisplayOnCartSlider());
		
		
	}

}
