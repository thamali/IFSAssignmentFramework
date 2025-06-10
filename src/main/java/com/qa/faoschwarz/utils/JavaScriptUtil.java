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
	
	 
	 public void disablePopupWithJavaScript() {
	     
	        try {
	            Thread.sleep(3000);
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	        
	        String script = "try { " +
	            "var popup = document.querySelector('body > div:nth-child(104)'); " +
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
	    

	    public void removePopupElements() {
	        try {
	         
	            js.executeScript(
	                "var element = document.querySelector('body > div:nth-child(104)'); " +
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
	    
	 
	  public void injectPopupBlockingCSS() {
	        try {
	            String cssScript = 
	                "var style = document.createElement('style'); " +
	                "style.innerHTML = 'body > div:nth-child(104) { display: none !important; } " +
	                "[class*=\"popup\"], [class*=\"modal\"] { display: none !important; }'; " +
	                "document.head.appendChild(style);";
	            
	            js.executeScript(cssScript);
	            System.out.println("Popup blocking CSS injected");
	        } catch (Exception e) {
	            System.out.println("Error injecting CSS: " + e.getMessage());
	        }
	    }
	
}
