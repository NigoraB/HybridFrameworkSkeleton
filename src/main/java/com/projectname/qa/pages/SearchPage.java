/**
 * 
 */
package com.projectname.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.projectname.qa.utils.ElementUtil;

/**
 * 
 */
public class SearchPage {

	WebDriver driver;
	private ElementUtil eleUtil;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

//Objects
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;

//Actions

	public boolean displayStatusOfProduct() {
		boolean displayStatus = eleUtil.doElementIsDisplayed(validHPProduct);
		// boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}

	public String retrieveNoProductMessageText() {
		String noProductMessageText = eleUtil.doElementGetText(noProductMessage);
//	String noProductMessageText=noProductMessage.getText();
		return noProductMessageText;
	}

}
