package com.qa.faoschwarz.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.qa.faoschwarz.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	public WebDriver driver;
	public static Logger log = LogManager.getLogger(ElementUtil.class);

	public ElementUtil(WebDriver driver) {

		this.driver = driver;

	}

	/**
	 * Simulates keyboard input by sending the specified text (value) to the web
	 * element identified by locator. It first clears any existing text in the
	 * element before typing the new value.
	 * 
	 * @param locator
	 * @param value
	 */

	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);

	}

	/**
	 * Performs a mouse click on the web element specified by locator.
	 * 
	 * @param locator
	 * 
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * Waits for the web element to become visible (up to timeout seconds) and then
	 * clicks it.
	 * 
	 * @param locator
	 * @param timeout
	 */
	public void doClickWithWait(By locator, int timeout) {
		getElementWithWait(locator, timeout).click();

	}

	/**
	 * Simulates a click by sending the ENTER key to the web element, which can be
	 * useful for buttons or links that respond to keyboard events.
	 * 
	 * @param locator
	 *
	 */
	public void doClickByKeyboardKey(By locator) {
		getElement(locator).sendKeys(Keys.ENTER);
	}

	/**
	 * Finds and returns the first web element matching the given locator on the
	 * page.
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {

		return driver.findElement(locator);

	}

	/**
	 * Waits until the web element is visible (up to timeOut seconds) and then
	 * returns it.
	 * 
	 * @param locator
	 * @return
	 */

	public WebElement getElementWithWait(By locator, int timeOut) {

		return WaitforElementVisible(locator, timeOut);

	}

	/**
	 * Retrieves and returns the visible text from the web element specified by
	 * locator.
	 * 
	 * @param locator
	 * @return
	 */

	public String doElementGetText(By locator) {
		String eleText = getElement(locator).getText();
		log.info("element text is :" + eleText);
		return eleText;

	}

	/**
	 * Returns the number of web elements found on the page that match the given
	 * locator.
	 * 
	 * @param locator
	 * @return
	 */
	public int getElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		log.info("Element list count is :" + eleCount);
		return eleCount;
	}

	/**
	 * Finds and returns a list of all web elements matching the given locator.
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {

		return driver.findElements(locator);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the elements are not only displayed but
	 * also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	@Step("waiting for element using: {0} and timeout : {1}")
	public WebElement WaitforElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> WaitForAllElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			// TODO: handle exception
			return Collections.EMPTY_LIST;
		}

	}

	/**
	 * Checks if the web element identified by locator is visible on the page.
	 * Returns true if visible, otherwise false.
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();

		} catch (NoSuchElementException e) {

			log.info("Element is not presence on the page :" + locator);
			return false;
		}
	}

	/**
	 * Waits for the element to be visible (up to timeOut seconds) and clicks it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public void clickWithWait(By locator, int timeOut) {

		WaitforElementVisible(locator, timeOut).click();
	}

	/**
	 * Waits for the element to be visible (up to timeOut seconds) and sends the
	 * specified text (value) to it.
	 * 
	 * @param locator
	 * @param value
	 * @param timeOut
	 * @return
	 */
	public void sendKeysWithWait(By locator, String value, int timeOut) {

		WaitforElementVisible(locator, timeOut).sendKeys(value);
	}

	/**
	 * Waits until the page title contains the specified substring (fractionTitle)
	 * within the given timeout, then returns the page title. Returns null if the
	 * timeout is reached.
	 * 
	 * @param fractionTitle
	 * @param timeout
	 * @return
	 */

	public String WaitForTitleContains(String fractionTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(fractionTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	/**
	 * Waits until the page title exactly matches the specified title within the
	 * timeout, then returns the page title. Returns null if the timeout is reached.
	 * 
	 * @param title
	 * @param timeout
	 * @return
	 */

	public String WaitForTitle(String title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}

	}

	/**
	 * Waits until the current page URL contains the specified substring
	 * (fractionURL) within the timeout, then returns the current URL. Returns null
	 * if the timeout is reached.
	 * 
	 * @param fractionURL
	 * @param timeout
	 * @return
	 */

	public String WaitForURLContains(String fractionURL, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.urlContains(fractionURL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

	/**
	 * Waits until the current page URL exactly matches the specified url within the
	 * timeout, then returns the current URL. Returns null if the timeout is
	 * reached.
	 * 
	 * @param url
	 * @param timeout
	 * @return
	 */
	public String WaitForURL(String url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.urlToBe(url));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}

	/**
	 * Waits until the frame specified by framelocator is available, then switches
	 * the WebDriver's context to that frame.
	 * 
	 * @param framelocator
	 * @param timeout
	 * @return
	 */
	public void waitForFrameAndSwitchToIt(By framelocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(framelocator));

	}

}
