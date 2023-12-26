/**
 * 
 */
package com.projectname.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.projectname.qa.utils.ElementUtil;
import com.projectname.qa.constants.AppConstants;

/**
 * 
 */
public class AccountPage {
	WebDriver driver;
	private ElementUtil eleUtil;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		eleUtil = new ElementUtil(driver);
	}

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	@FindBy(xpath = "//div[@id='content']// h2")
	private List<WebElement> accsHeaders;

	@FindBy(name = "search")
	private WebElement search;

	@FindBy(css = "#search button")
	private WebElement searchIcon;

	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;

	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIsAndFetch(AppConstants.PAIGE_WAIT_TIME,
				AppConstants.ACCOUNTS_PAGE_TITLE_VALUE);
		System.out.println("Acc page title is : " + title);
		return title;
	}

	public String getAccPageURL() {
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.PAIGE_WAIT_TIME,
				AppConstants.ACCOUNTS_PAGE_URL_FRACTION_VALUE);
		System.out.println("Acc page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.EXPLICIT_WAIT_TIME).isDisplayed();
	}

	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.EXPLICIT_WAIT_TIME).isDisplayed();
	}
	
	public List<String> getAccountsPageHeadersList() {
		List<String> accountList = new ArrayList<>();
		for (WebElement e : accsHeaders) {
			String text = e.getText();
			System.out.println(text);
			accountList.add(text);
		}
		return accountList;
	}

	public SearchPage performSearch(String searchKey) {

		if (isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		} else {
			System.out.println("Search field is not present on the page....");
			return null;
		}
	}

	public boolean getDisplayStatusEditYourAccountInformationOption() {
		boolean dispalyStatus = eleUtil.doElementIsDisplayed(editYourAccountInformationOption);
		// boolean dispalyStatus = editYourAccountInformationOption.isDisplayed();
		return dispalyStatus;
	}

}
