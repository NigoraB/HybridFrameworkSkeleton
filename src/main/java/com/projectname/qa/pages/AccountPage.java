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
public class AccountPage {
	WebDriver driver;
	private ElementUtil eleUtil;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;

	public boolean getDisplayStatusEditYourAccountInformationOption() {
		boolean dispalyStatus=eleUtil.doElementIsDisplayed(editYourAccountInformationOption);
	//	boolean dispalyStatus = editYourAccountInformationOption.isDisplayed();
		return dispalyStatus;
	}

}
