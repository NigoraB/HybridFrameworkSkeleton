/**
 * 
 */
package com.projectname.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.projectname.qa.utils.ElementUtil;
import com.projectname.qa.constants.AppConstants;
import com.projectname.qa.pages.ProductInfoPage;

/**
 * 
 */
public class SearchPage {

	WebDriver driver;
	private ElementUtil eleUtil;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

//Objects
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProduct;

	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement noProductMessage;
	
	@FindBy(css = "div#content div.product-layout")
	private WebElement searchProductResults;
	

//Actions

	public boolean displayStatusOfProduct() {
		boolean displayStatus = eleUtil.doElementIsDisplayed(validHPProduct);
		// boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}

	public String retrieveNoProductMessageText() {
		String noProductMessageText = eleUtil.doElementGetText(noProductMessage);
//	String noProductMessageText=noProductMessage.getText();
		return noProductMessageText;
	}
	public int getSearchProductsCount() {
		int productCount = eleUtil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("Product Count:::" + productCount);
		return productCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, AppConstants.DEFAULT_MEDIUM_TIME_OUT).click();
		return new ProductInfoPage(driver);
	}

}
