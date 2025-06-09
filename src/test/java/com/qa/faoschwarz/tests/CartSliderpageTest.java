package com.qa.faoschwarz.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;

public class CartSliderpageTest extends BaseTest {
	
	
	@DataProvider
	public Object[][] getProductQuantityTestData() {
		return new Object[][] { 
			{ "cars", "Ambulance", 3 },

		};

	}
	
	@Test(description = "Verify cartSlider loaded properly", dataProvider = "getProductQuantityTestData")
	public void CartSliderHeaderTest(String searchProduct, String productName, int desiredQuantity) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		
		cartSliderPage=productInforPage.addProductToCart();	
		String actCartSliderHeader=cartSliderPage.getCartSliderHeader();
		
		Assert.assertEquals(actCartSliderHeader, "Cart");

	}
	
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

}
