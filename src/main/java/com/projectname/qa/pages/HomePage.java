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
public class HomePage {

	WebDriver driver;
	private ElementUtil eleUtil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

//Objects	
	@FindBy(xpath = " //span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement LoginOption;

	@FindBy(linkText = "Register")
	private WebElement registerOption;

	@FindBy(name = "search")
	private WebElement searchBoxField;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchBtn;

	// Actions
	public void clickOnMyAccount() {
		eleUtil.doClick(myAccountDropMenu);
	//	myAccountDropMenu.click();
	}

	public LoginPage selectLoginOption() {
		eleUtil.doClick(LoginOption);
	//	LoginOption.click();
		return new LoginPage(driver);
	}

	public LoginPage navigateToLoginPage() {
		eleUtil.doClick(myAccountDropMenu);
		eleUtil.doClick(LoginOption);
	//	myAccountDropMenu.click();		
	//	LoginOption.click();
		return new LoginPage(driver);
	}

	public RegisterPage selectRegisterOption() {
		eleUtil.doClick(registerOption);
	//	registerOption.click();
		return new RegisterPage(driver);
	}

	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(myAccountDropMenu);
		eleUtil.doClick(registerOption);
//		myAccountDropMenu.click();
//		registerOption.click();
		return new RegisterPage(driver);
	}

	public void enterProductIntoSearchBox(String productText) {
		eleUtil.doSendKeys(searchBoxField, productText);
	//	searchBoxField.sendKeys(productText);
	}

	public SearchPage clickOnSearchBtn() {
		eleUtil.doClick(searchBtn);
	//	searchBtn.click();
		return new SearchPage(driver);
	}

	public SearchPage searchForProduct(String productText) {
		eleUtil.doSendKeys(searchBoxField, productText);
		eleUtil.doClick(searchBtn);
//		searchBoxField.sendKeys(productText);
//		searchBtn.click();
		return new SearchPage(driver);
	}

}
