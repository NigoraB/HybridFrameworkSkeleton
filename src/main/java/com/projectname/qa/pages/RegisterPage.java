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
public class RegisterPage {
	WebDriver driver;
	private ElementUtil eleUtil;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);

	}

//Objects
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConformField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value = 'Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	// Actions
	public void enterFristName(String firstNameText) {
		eleUtil.doSendKeys(firstNameField, firstNameText);
		// firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		eleUtil.doSendKeys(lastNameField, lastNameText);
		// lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailText) {
		eleUtil.doSendKeys(emailAddressField, emailText);
		// emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneNumber(String telephoneText) {
		eleUtil.doSendKeys(telephoneField, telephoneText);
		// telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		eleUtil.doSendKeys(passwordField, passwordText);
		// passwordField.sendKeys(passwordText);
	}

	public void confirmPassword(String passwordConfirmText) {
		eleUtil.doSendKeys(passwordConformField, passwordConfirmText);
		// passwordConformField.sendKeys(passwordConfirmText);
	}

	public void selectPrivacyPolicy() {
		eleUtil.doClick(privacyPolicyField);
		// privacyPolicyField.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		eleUtil.doClick(continueButton);
		// continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectYesNewsLetterOption() {
		eleUtil.doClick(yesNewsLetterOption);
		// yesNewsLetterOption.click();
	}

	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarningText = eleUtil.doElementGetText(duplicateEmailAddressWarning);
		// String duplicateEmailAddressWarningText =
		// duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarningText;
	}

	public String retrievePrivasyPolicyWarning() {
		String privacyPolicyWarningText = eleUtil.doElementGetText(privacyPolicyWarning);
		// String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}

	public String retrieveFirstNameWarning() {
		String firstNameWarningText = eleUtil.doElementGetText(firstNameWarning);
		// String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}

	public String retrieveLastNameWarning() {
		String lastNameWarningText = eleUtil.doElementGetText(lastNameWarning);
		// String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}

	public String retrieveEmailWarning() {
		String emailWarningText = eleUtil.doElementGetText(emailWarning);
		// String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}

	public String retrieveTelephoneWarning() {
		String telephoneWarningText = eleUtil.doElementGetText(telephoneWarning);
		// String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}

	public String retrievePasswordWarning() {
		String passwordWarningText = eleUtil.doElementGetText(passwordWarning);
		// String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}

	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText,
			String telephoneText, String passwordText, String passwordConfirmText) {
		eleUtil.doSendKeys(firstNameField, firstNameText);
		eleUtil.doSendKeys(lastNameField, lastNameText);
		eleUtil.doSendKeys(emailAddressField, emailText);
		eleUtil.doSendKeys(telephoneField, telephoneText);
		eleUtil.doSendKeys(passwordField, passwordText);
		eleUtil.doSendKeys(passwordConformField, passwordConfirmText);
		eleUtil.doSendKeys(lastNameField, lastNameText);
		eleUtil.doClick(privacyPolicyField);
		eleUtil.doClick(continueButton);
		
//		firstNameField.sendKeys(firstNameText);
//		lastNameField.sendKeys(lastNameText);
//		emailAddressField.sendKeys(emailText);
//		telephoneField.sendKeys(telephoneText);
//		passwordField.sendKeys(passwordText);
//		passwordConformField.sendKeys(passwordConfirmText);
//		privacyPolicyField.click();
//		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText,
			String telephoneText, String passwordText, String passwordConfirmText) {
		eleUtil.doSendKeys(firstNameField, firstNameText);
		eleUtil.doSendKeys(lastNameField, lastNameText);
		eleUtil.doSendKeys(emailAddressField, emailText);
		eleUtil.doSendKeys(telephoneField, telephoneText);
		eleUtil.doSendKeys(passwordField, passwordText);
		eleUtil.doSendKeys(passwordConformField, passwordConfirmText);
		eleUtil.doClick(yesNewsLetterOption);
		eleUtil.doClick(privacyPolicyField);
		eleUtil.doClick(continueButton);
		
//		firstNameField.sendKeys(firstNameText);
//		lastNameField.sendKeys(lastNameText);
//		emailAddressField.sendKeys(emailText);
//		telephoneField.sendKeys(telephoneText);
//		passwordField.sendKeys(passwordText);
//		passwordConformField.sendKeys(passwordConfirmText);
//		yesNewsLetterOption.click();
//		privacyPolicyField.click();
//		continueButton.click();
		return new AccountSuccessPage(driver);
	}

}
