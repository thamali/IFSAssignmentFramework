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

	/**
	 *Performs a click action on the specified web element using JavaScript execution. 
	 *Useful when standard Selenium click actions do not work due to overlays or dynamic UI issues.
	 * 
	 * @param element
	 * 
	 */
	public void clickElementByJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

	/** 
	 * Retrieves and returns the value attribute of the first DOM element matching the provided CSS selector using JavaScript.
	 * 
	 * @param qrySelector
	 * @return
	 */
	public String getElementValueByJS(String  qrySelector) {
		return js.executeScript(
	            "return document.querySelector('"+qrySelector+"').value;"
		        ).toString();
	}
	
	 
	/**
	 * Attempts to hide and remove a specific popup (identified as body > div:nth-child(104)) from the page using JavaScript. 
	 * It also logs the result and handles potential errors during execution.
	 */
	 public void disablePopupWithJavaScript(String popupSelector) {
	     
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	        
	        String script = "try { " +
	            "var popup = document.querySelector('"+popupSelector+"; " +
	            "if (popup) { " +
	                "popup.style.display = 'none'; " +
	                "popup.remove(); " +
	                "console.log('Popup removed'); " +
	                "return 'Success'; " +
	            "} " +
	            "return 'No popup found'; " +
	        "} catch (e) { " +
	            "console.log('Error: ' + e.message); " +
	            "return 'Error: ' + e.message; " +
	        "}";
	        
	        try {
	            Object result = js.executeScript(script);
	            System.out.println("Popup removal result: " + result);
	        } catch (Exception e) {
	            System.out.println("JavaScript execution failed: " + e.getMessage());
	        }
	    }
	    
/**
 * Removes specific popup elements from the page by:

    Removing the element matching body > div:nth-child(104)

    Removing all elements whose class names contain "popup" or "modal"
    Logs the outcome or any errors encountered.
 */
	    public void removePopupElements(String popupSelector) {
	        try {
	         
	            js.executeScript(
	                "var element = document.querySelector('"+popupSelector+"; " +
	                "if (element) { element.remove(); }"
	            );
	            
	     
	            js.executeScript(
	                "var popups = document.querySelectorAll('[class*=\"popup\"], [class*=\"modal\"]'); " +
	                "for (var i = 0; i < popups.length; i++) { popups[i].remove(); }"
	            );
	            
	            System.out.println("Popup elements removed successfully");
	        } catch (Exception e) {
	            System.out.println("Error removing popup elements: " + e.getMessage());
	        }
	    }
	    
	 /**
	  * Injects a CSS style block into the page that hides the popup (body > div:nth-child(104)) and any elements with class names containing "popup" or "modal". 
	  * This prevents such popups from displaying by setting their display property to none !important
	  */
	  public void injectPopupBlockingCSS(String popupSelector) {
	        try {
	            String cssScript = 
	                "var style = document.createElement('style'); " +
	                "style.innerHTML = '"+popupSelector+" { display: none !important; } " +
	                "[class*=\"popup\"], [class*=\"modal\"] { display: none !important; }'; " +
	                "document.head.appendChild(style);";
	            
	            js.executeScript(cssScript);
	            System.out.println("Popup blocking CSS injected");
	        } catch (Exception e) {
	            System.out.println("Error injecting CSS: " + e.getMessage());
	        }
	    }
	
}
