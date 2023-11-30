package com.projectname.qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.projectname.qa.factory.DriverFactory;
import com.projectname.qa.pages.AccountPage;
import com.projectname.qa.pages.HomePage;
import com.projectname.qa.pages.LoginPage;
import com.projectname.qa.pages.ProductInfoPage;
import com.projectname.qa.pages.RegisterPage;
import com.projectname.qa.pages.SearchPage;
import com.projectname.qa.pages.ViewCartPopUpPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountPage accPage;
	protected SearchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected ViewCartPopUpPage viewCartPopUpPage;
	protected RegisterPage registerPage;

	protected SoftAssert softAssert;

	@BeforeMethod
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.intializeProperties();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		driver = df.initializeBrowserAndOpenAppURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
		softAssert = new SoftAssert();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
