/**
 * 
 */
package com.projectname.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.projectname.qa.factory.DriverFactory;
import com.projectname.qa.pages.AccountPage;
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.LoginPage;
import com.projectname.qa.utils.Utilities;
import com.projectname.qa.utils.ExcellUtil;

/**
 * 
 */
public class LoginTest extends DriverFactory {

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	public LoginTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
//		homePage.clickOnMyAccount();
//		loginPage = homePage.selectLoginOption();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	// optimized version of login in a single line
	// ------------------------------------------
	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data = { { "qa.test@gmail.com", "validPassword@123" } };
		return data;
	}

	@Test(dataProvider = "supplyTestData")
	public void verifyLoggingWithValidCredentials2(String email, String password) {

		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusEditYourAccountInformationOption());
	}

	// ----------------------------------------------------

	@Test(description = "Verify logging into the Application using valid credentials")
	public void TC_LF_001() {
		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPwd"));
		Assert.assertTrue(accountPage.getDisplayStatusEditYourAccountInformationOption());

	}

	@Test(description = "Verify logging into the Application using invalid credentials (i.e. Invalid email address and Invalid Password)")
	public void TC_LF_002() {
		loginPage.login(Utilities.generateEmailWithTimestamp(), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = loginPage.retriveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	@Test(description = "Verify logging into the Application using invalid email address and valid Password)")
	public void TC_LF_003() {
		loginPage.login(Utilities.generateEmailWithTimestamp(), prop.getProperty("validPwd"));
		String actualWarningMessage = loginPage.retriveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	@Test(description = "Verify logging into the Application using valid email address and invalid Password")
	public void TC_LF_004() {
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		String actualWarningMessage = loginPage.retriveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}
	
	@Test(description = "Verify logging into the Application without providing any credentials")
	public void TC_LF_005() {

		loginPage.clickOnLoginBtn();
		String actualWarningMessage = loginPage.retriveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
	}

	
	
	
	// ------------------------------------------
	// login action in a multiple lines
	@Test(description = "Verify logging into the Application using valid credentials")
	public void verifyLoggingWithValidCredentials1() {

		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(prop.getProperty("validPwd"));
		accountPage = loginPage.clickOnLoginBtn();
		Assert.assertTrue(accountPage.getDisplayStatusEditYourAccountInformationOption());

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] getTestData() {
		Object[][] data = ExcellUtil.getTestDataFromExcel("Login");
		return data;
	}

	@Test(dataProvider = "validCredentialsSupplier", description = "Verify logging into the Application using valid credentials")
	public void verifyLoggingWithValidCredentials3(String email, String password) {

		loginPage.enterEmailAddress(email);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginBtn();
		AccountPage accountPage = new AccountPage(driver);
		Assert.assertTrue(accountPage.getDisplayStatusEditYourAccountInformationOption());
	}

	@Test(description = "Verify logging into the Application using invalid credentials")
	public void verifyLogingWithInvalidCredentials() {
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimestamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginBtn();
		String actualWarningMessage = loginPage.retriveWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertEquals(actualWarningMessage, expectedWarningMessage, "Expected Warning Message is not displayed");
	}

	

}
