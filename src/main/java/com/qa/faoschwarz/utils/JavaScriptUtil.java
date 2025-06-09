package com.qa.faoschwarz.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {

		this.driver = driver;
		js = (JavascriptExecutor) this.driver;

	}

	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	public String getElementValueByJS(String  qrySelector) {
		return js.executeScript(
	            "return document.querySelector('"+qrySelector+"').value;"
		        ).toString();
	}
}
