package com.qa.faoschwarz.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;

public class ProductInforPageTest extends BaseTest{
	
	@Test(description = "Verify selected product is loaded properly in product load page")
	//
	public void ProductHeaderTest() {
		searchResultsPage=homePage.doSearch(prop.getProperty("product"));
		searchResultsPage.doSortByAsc();
		productInforPage=searchResultsPage.selectProduct("Ambulance");
		String actProductHeader=productInforPage.getProductHeader();
		Assert.assertEquals(actProductHeader, "Hurry Ambulance Crazy Motors Car Toy");
		
	}
	

}
