/**
 * 
 */
package com.projectname.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.projectname.qa.factory.DriverFactory;
import com.projectname.qa.pages.AccountSuccessPage;
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.RegisterPage;
import com.projectname.qa.utils.Utilities;

/**
 * 
 */
public class RegisterTest extends DriverFactory {
	WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimestamp(),
				dataProp.getProperty("phoneNumber"), prop.getProperty("validPwd"), prop.getProperty("validPwd"));

		String actualSuccssesHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccssesHeading, dataProp.getProperty("accountSuccesfullyCreatedHeading"));
	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountByProvidingAllFields() {

		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimestamp(),
				dataProp.getProperty("phoneNumber"), prop.getProperty("validPwd"), prop.getProperty("validPwd"));

		String actualSuccssesHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccssesHeading, dataProp.getProperty("accountSuccesfullyCreatedHeading"));
	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress() {

		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), prop.getProperty("validEmail"), dataProp.getProperty("phoneNumber"),
				prop.getProperty("validPwd"), prop.getProperty("validPwd"));

		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertEquals(actualWarning, dataProp.getProperty("duplicateEmailWarning"));
	}

	@Test(priority = 4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() {

		registerPage.clickOnContinueButton();

		String actualPrivacyPolicyWarning = registerPage.retrievePrivasyPolicyWarning();
		Assert.assertEquals(actualPrivacyPolicyWarning, dataProp.getProperty("privacyPolicyWarning"));

		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertEquals(actualFirstNameWarning, dataProp.getProperty("firstNameWarning"));

		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning, dataProp.getProperty("lastNameWarning"));

		String actualEmailWarning = registerPage.retrieveEmailWarning();
		Assert.assertEquals(actualEmailWarning, dataProp.getProperty("emailAddressWarning"));

		String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning, dataProp.getProperty("telephoneWarning"));

		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning, dataProp.getProperty("passwordWarning"));
	}

	// initial TC
	@Test(priority = 1)
	public void verifyRegisteringAccountWithMandatoryFields() {

		registerPage.enterFristName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		registerPage.enterTelephoneNumber(dataProp.getProperty("phoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPwd"));
		registerPage.confirmPassword(prop.getProperty("validPwd"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		String actualSuccssesHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(actualSuccssesHeading, dataProp.getProperty("accountSuccesfullyCreatedHeading"));
	}
}
