package com.qa.faoschwarz.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.faoschwarz.base.BaseTest;
import com.qa.faoschwarz.constants.AppConstants;

import static com.qa.faoschwarz.constants.AppConstants.*;

import java.util.List;

public class HomePageTest extends BaseTest {

	@Test(description = "Verify Home Page Title")
	public void homePageTitleTest() {
		String actTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actTitle, HOME_PAGE_TITLE);

	}

	@Test(description = "Verify Home Page Url")
	public void loginPageURLTest() {
		String actURL = homePage.getHomePageURL();
		Assert.assertTrue(actURL.contains(HOME_PAGE_FRACTION_URL));
	}

	@Test(description = "Verify Home Page Logo is loaded")
	public void validateHomePageLogoLoaded() {
		Assert.assertTrue(homePage.isHomePageLogoDisplayed());

	}

	@Test(description = "Verify site navigation item headers are loaded properly")
	public void getHomePageSiteNavHeaders() {
		List<String> actNavHeaders = homePage.getHomePageSiteNavHeaders();
        Assert.assertEquals(actNavHeaders, AppConstants.homePageSiteNavHeadersList);
	}
	
	@Test(priority = Short.MAX_VALUE ,description="Verify search layout is loaded properly" )
	public void validateSearchLayoutLoading() {
		Assert.assertTrue(homePage.getSearchLayOut());
	}

	
}
