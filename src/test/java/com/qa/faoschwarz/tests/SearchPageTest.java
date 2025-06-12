package com.qa.faoschwarz.tests;

import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.faoschwarz.api.GetProductListAPI;
import com.qa.faoschwarz.base.BaseTest;
import com.qa.faoschwarz.pages.SearchResultsPage;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Feature("FAOschwarz-Search Feature")
@Owner("Thamali")
public class SearchPageTest extends BaseTest {
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { 
			{ "cars"}
		};

	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify search results are appearead on the page" , dataProvider = "getProductTestData")
	public void searchProductTest(String SearchProduct) {
		searchResultsPage = homePage.doSearch(SearchProduct);
		int actResultsCount = searchResultsPage.getResultsProdutCount();

		getProductListApi = new GetProductListAPI();
		int count = getProductListApi.getProductListNoOfResults(SearchProduct);

		Assert.assertEquals(actResultsCount, count);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify products are sorted by price from low to high in ascending order" , dataProvider = "getProductTestData")
	public void sortTestByLowToHigh(String SearchProduct) {
		searchResultsPage = homePage.doSearch(SearchProduct);
		searchResultsPage.doSortByAsc();
		Assert.assertTrue(searchResultsPage.isProductSortedAscending());
	}

}
