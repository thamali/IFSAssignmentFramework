package com.qa.faoschwarz.constants;

import java.util.List;

import org.openqa.selenium.By;

public class AppConstants {
	
	public static final int DEFAULT_TIMEOUT=5;
	public static final int MEDIUM_DEFAULT_TIMEOUT=10;
	public static final int LONG_DEFAULT_TIMEOUT=15;
           
	public static final String HOME_PAGE_TITLE="Iconic Toy Store For Kids of All Ages | FAO Schwarz";
	public static final String HOME_PAGE_FRACTION_URL="faoschwarz";
	
	public static List<String> homePageSiteNavHeadersList=List.of("New","Shop By Age",
			                                                            "Shop By Category","Brands","FAOMG!","Sale");
	
	public static final String POPUP_SELECTOR = "body > div:nth-child(104)";
	
	public static final By ACCEPT_COOKIE_BTN = By.xpath("//button[@id='onetrust-accept-btn-handler']");
	
	public static final String PRODUCT_SHEET_NAME = "product";

	
	
}
