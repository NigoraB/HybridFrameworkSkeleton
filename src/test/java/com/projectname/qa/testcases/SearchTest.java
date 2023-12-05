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
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.SearchPage;

/**
 * 
 */
public class SearchTest extends DriverFactory {
	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.searchForProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfProduct());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		searchPage = homePage.searchForProduct(dataProp.getProperty("invalidProductName"));
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("NoProductTextInSearch"),
				"Message is not matching");
		//dataProp.getProperty("NoProductTextInSearch")
	}

	@Test(priority = 3, dependsOnMethods= {"verifySearchWithValidProduct", "verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchBtn();
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("NoProductTextInSearch"),
				"Message is not matching");
	}

	// initial TC
	@Test(priority = 1)
	public void verifySearchWithValidProduct1() {

		homePage.enterProductIntoSearchBox(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchBtn();
		Assert.assertTrue(searchPage.displayStatusOfProduct());
	}
}
