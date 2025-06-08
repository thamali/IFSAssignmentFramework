package com.qa.faoschwarz.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.faoschwarz.api.GetProductListAPI;
import com.qa.faoschwarz.base.BaseTest;
import com.qa.faoschwarz.pages.SearchResultsPage;

public class SearchPageTest extends BaseTest {

	@Test(description = "Verify search results are appearead on the page")
	public void searchProductTest() {
		searchResultsPage = homePage.doSearch(prop.getProperty("product"));
		int actResultsCount = searchResultsPage.getResultsProdutCount();

		getProductListApi = new GetProductListAPI();
		int count = getProductListApi.getProductListNoOfResults(prop.getProperty("product"));

		Assert.assertEquals(actResultsCount, count);
	}

	@Test(description = "Verify products are sorted by price from low to high in ascending order")
	public void sortTestByLowToHigh() {
		searchResultsPage = homePage.doSearch(prop.getProperty("product"));
		searchResultsPage.doSortByAsc();
		Assert.assertTrue(searchResultsPage.isProductSortedAscending());
	}

}
