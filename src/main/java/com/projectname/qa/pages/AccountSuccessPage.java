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
public class AccountSuccessPage {
	WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	WebElement accountSuccessPageHeading;

	public String retrieveAccountSuccessPageHeading() {
		String accountSuccessPageHeadingText =eleUtil.doElementGetText(accountSuccessPageHeading);
	//	String accountSuccessPageHeadingText = accountSuccessPageHeading.getText();
		return accountSuccessPageHeadingText;
	}

}
