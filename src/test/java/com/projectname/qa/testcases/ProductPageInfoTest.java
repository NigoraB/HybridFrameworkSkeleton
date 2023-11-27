package com.projectname.qa.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.projectname.qa.factory.DriverFactory;
import com.projectname.qa.pages.AccountPage;
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.LoginPage;
import com.projectname.qa.pages.ProductInfoPage;
import com.projectname.qa.pages.SearchPage;
import com.projectname.qa.pages.ViewCartPopUpPage;

public class ProductPageInfoTest extends DriverFactory {
	
	
	ArrayList<String> expProdListInCart;
	WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;
	SearchPage searchPage;
	ProductInfoPage productInfoPage;
	ViewCartPopUpPage viewCartPopUpPage;
	
	public ProductPageInfoTest() {
		super();
	}

	@BeforeMethod
	public void productInfoPageSetup() {
		driver = initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		accountPage = loginPage.login(prop.getProperty("validEmail"), prop.getProperty("validPwd"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	
	@Test(dataProvider = "getProductImagesTestData")	
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accountPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}
	
	
	
	@Test
	public void productInfoTest() {
		searchPage = accountPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		Assert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		Assert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		Assert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
	}
	
	//assert vs verify(soft assertion)
	
	
	@DataProvider
	public Object[][] getCartTestData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro", 1},
			{"iMac", "iMac", 2},
		};
	}
	
	
	@Test(dataProvider = "getCartTestData")
	public void addtToCartTest(String searchKey, String productName, int quantity) {
		searchPage = accountPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		productInfoPage.enterQuantity(quantity);
		String actCartMesg = productInfoPage.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		Assert.assertTrue(actCartMesg.indexOf("Success")>=0);
		Assert.assertTrue(actCartMesg.indexOf(productName)>=0);
		Assert.assertEquals(actCartMesg, "Success: You have added "+productName+" to your shopping cart!");
		
		//new code: checking cart details as well:
		viewCartPopUpPage = productInfoPage.openCart();
		List<String> cartProdList = viewCartPopUpPage.getProductsValueListInCart();
		
		Object[][] data = getCartTestData();
		expProdListInCart = new ArrayList<String>();
		for(int i=0; i< data.length; i++) {
			expProdListInCart.add(data[i][1].toString());
		}
		
		System.out.println(expProdListInCart);
		Assert.assertTrue(expProdListInCart.containsAll(cartProdList));

	}
	

}
