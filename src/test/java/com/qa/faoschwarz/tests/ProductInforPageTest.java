package com.qa.faoschwarz.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;

public class ProductInforPageTest extends BaseTest {

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { 
			{ "cars", "Ambulance", "Hurry Ambulance Crazy Motors Car Toy" },

		};

	}
	
	@DataProvider
	public Object[][] getProductQuantityTestData() {
		return new Object[][] { 
			{ "cars", "Ambulance", 3 },

		};

	}

	@Test(description = "Verify selected product is loaded properly in product load page", dataProvider = "getProductTestData")
	public void ProductHeaderTest(String searchProduct, String productName, String FullProductName) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		String actProductHeader = productInforPage.getProductHeader();
		Assert.assertEquals(actProductHeader, FullProductName);

	}
	
	@Test(description = "Verify selected product quantity is updated" ,dataProvider = "getProductQuantityTestData")
	public void increaseQantityTest(String searchProduct, String productName, int desiredQuantity) {
		searchResultsPage = homePage.doSearch(searchProduct);
		searchResultsPage.doSortByAsc();
		productInforPage = searchResultsPage.selectProduct(productName);
		int actQuantity=productInforPage.increaseQantityWithPlusButton(desiredQuantity);
		Assert.assertEquals(actQuantity, desiredQuantity);
		
	}

}
