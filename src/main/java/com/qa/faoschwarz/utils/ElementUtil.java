package com.qa.faoschwarz.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	public WebDriver driver;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClickWithWait(By locator, int timeout) {
		getElementWithWait(locator, timeout).click();

	}

	public void doClickByKeyboardKey(By locator) {
		getElement(locator).sendKeys(Keys.ENTER);
	}

	public WebElement getElement(By locator) {

		return driver.findElement(locator);

	}

	public WebElement getElementWithWait(By locator, int timeOut) {

		return WaitforElementVisible(locator, timeOut);

	}

	public String doElementGetText(By locator) {
		String eleText = getElement(locator).getText();
		System.out.println("element text is :" + eleText);
		return eleText;

	}

	public int getElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("Element list count is :" + eleCount);
		return eleCount;
	}

	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	public WebElement WaitforElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public List<WebElement> WaitForAllElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			// TODO: handle exception
			return Collections.EMPTY_LIST;
		}

	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			System.out.println("Element is not presence on the page :" + locator);
			return false;
		}
	}

	public void clickWithWait(By locator, int timeOut) {

		WaitforElementVisible(locator, timeOut).click();
	}

	public void sendKeysWithWait(By locator, String value, int timeOut) {

		WaitforElementVisible(locator, timeOut).sendKeys(value);
	}

	public String WaitForTitleContains(String fractionTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(fractionTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	public String WaitForTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	public String WaitForURLContains(String fractionURL, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.urlContains(fractionURL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

	public String WaitForURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.urlToBe(url));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

}
