package com.projectname.qa.testcases;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.projectname.qa.constants.AppConstants;
import com.projectname.qa.factory.DriverFactory;
import com.projectname.qa.pages.AccountPage;
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.LoginPage;
import com.projectname.qa.pages.ProductInfoPage;
import com.projectname.qa.pages.SearchPage;

public class AccountsPageTest extends DriverFactory {

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	SearchPage searchPage;
	ProductInfoPage productInfoPage;

	public AccountsPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPwd"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test()
	public void accPageTitleTest() {
		String actTitle = accountPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accountPage.getAccPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE));
	}

	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}

	@Test
	public void accPageHeadersCountTest() {
		List<String> actualAccPageHeadersList = accountPage.getAccountsPageHeadersList();
		System.out.println("Acc Page Headers List: " + actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}

	@Test
	public void accPageHeadersValueTest() {
		List<String> actualAccPageHeadersList = accountPage.getAccountsPageHeadersList();
		System.out.println("Actual Acc Page Headers List: " + actualAccPageHeadersList);
		System.out.println("Expected Acc Page Headers List:" + AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccPageHeadersList, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook" }, { "iMac" } };
//			{ "Apple" }, 
//			{ "Samsung" } };
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage = accountPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount() > 0);
	}

	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] { { "Macbook", "MacBook Pro" } };
//			{ "Macbook", "MacBook Air" }, { "iMac", "iMac" },
//		
//				{ "Apple", "Apple Cinema 30" }, { "Samsung", "Samsung SyncMaster 941BW" },
//				{ "Samsung", "Samsung Galaxy Tab 10.1" }, };
	}

	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey, String productName) {
		searchPage = accountPage.performSearch(searchKey);
		if (searchPage.getSearchProductsCount() > 0) {
			productInfoPage = searchPage.selectProduct(productName);
			String actProductHeader = productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}
	}

}
