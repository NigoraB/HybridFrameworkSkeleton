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
public class LoginPage  {

	WebDriver driver;
	private ElementUtil eleUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	//Objects
	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement warning;
	
	
	//Actions
	
	
	public void enterEmailAddress(String emailText) {
		eleUtil.doSendKeys(emailAddressField, emailText);
	//	emailAddressField.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		eleUtil.doSendKeys(passwordField, passwordText);
	//	passwordField.sendKeys(passwordText);

	}

	
	public AccountPage clickOnLoginBtn() {
		eleUtil.doClick(loginButton);
	//	loginButton.click();
		return new AccountPage(driver);
	}

	public AccountPage login(String emailText, String passwordText) {
		eleUtil.doSendKeys(emailAddressField, emailText);
		eleUtil.doSendKeys(passwordField, passwordText);
		eleUtil.doClick(loginButton);
//		emailAddressField.sendKeys(emailText);
//		passwordField.sendKeys(passwordText);
//		loginButton.click();
		return new AccountPage(driver);
	}

	public String retriveWarningMessageText() {
		String warningText= eleUtil.doElementGetText(warning);
//		String warningText = warning.getText();
		return warningText;
	}

}
