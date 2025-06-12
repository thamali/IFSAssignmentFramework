package com.qa.faoschwarz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.faoschwarz.utils.ElementUtil;

import io.qameta.allure.Step;

import static com.qa.faoschwarz.constants.AppConstants.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private final By logo = By.xpath("//a[contains(@class, 'logo--has-inverted')]/img[1]");
	private final By siteNavHeaders = By.xpath("//li[contains(@class,'site-nav__item')]/a");
	private final By searchIcon = By.cssSelector(".js-search-header");
	private final By searchLayout = By.cssSelector(".site-header__search-input");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("getting home page title")
	public String getHomePageTitle() {

		String title = eleUtil.WaitForTitle(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Home Page Title : " + title);
		return title;
	}

	@Step("getting home page url")
	public String getHomePageURL() {
		String currentUrl = eleUtil.WaitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Home Page URL : " + currentUrl);
		return currentUrl;
	}

	@Step("checking home page logo exists")
	public boolean isHomePageLogoDisplayed() {

		return eleUtil.isElementDisplayed(logo);

	}

	@Step("checking home page site navigation headers exist")
	public List<String> getHomePageSiteNavHeaders() {
		
		List<WebElement> navHeaderList = eleUtil.getElements(siteNavHeaders);
		List<String> navHeadertextList = new ArrayList<String>();

		for (WebElement e : navHeaderList) {
			String text = e.getText();
			navHeadertextList.add(text);
		}
		System.out.println("Home Page Site Nav Headers" + navHeadertextList);
		return navHeadertextList;
	}

	@Step("open search layout")
	public boolean getSearchLayOut() {
		eleUtil.doClick(searchIcon);
		return eleUtil.isElementDisplayed(searchLayout); 

	}

	@Step("navigate to search results page that displayed : {0} products")
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doClick(searchIcon);
		eleUtil.doSendKeys(searchLayout, searchKey);
		eleUtil.doClickByKeyboardKey(searchLayout);
		return new SearchResultsPage(driver);
	}

}
