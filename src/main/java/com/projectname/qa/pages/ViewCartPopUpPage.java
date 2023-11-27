package com.projectname.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.projectname.qa.constants.AppConstants;
import com.projectname.qa.utils.ElementUtil;

public class ViewCartPopUpPage {
	WebDriver driver;
	private ElementUtil eleUtil;


	public ViewCartPopUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	@FindBy(css=".table.table-striped td.text-left a")
	private WebElement productNames;
	
	
	public List<String> getProductsValueListInCart() {
		List<WebElement> cartList = eleUtil.waitForElementsVisible(productNames, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
		ArrayList<String> cartProdList = new ArrayList<String>();
		for(WebElement e : cartList) {
			String text = e.getText();
			cartProdList.add(text);
		}
		return cartProdList;
	}
}
