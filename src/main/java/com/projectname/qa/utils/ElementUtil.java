package com.projectname.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public WebElement getElement(WebElement element) {
		jsUtil.flash(element);
		return element;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public WebElement getElement(WebElement locator, int timeOut) {
		return waitForElementVisible( locator, timeOut);
		
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public List<WebElement> getElements(List<WebElement> element) {
		return element;
	}

	public void doSendKeys(WebElement locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
	}

	public void doActionsSendKeys(WebElement element, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(element), value).build().perform();
	}

	public void doClick(WebElement element) {
		getElement(element).click();
	}

	public void doActionsCick(WebElement element) {
		Actions act = new Actions(driver);
		act.click(getElement(element)).build().perform();
	}

	public String doElementGetText(WebElement element) {
		return getElement(element).getText();
	}

	public boolean doElementIsDisplayed(WebElement element) {
		return getElement(element).isDisplayed();
	}

	public String getElementAttribute(WebElement element, String attrName) {
		return getElement(element).getAttribute(attrName);
	}

	public void getElementAttributes(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String attrVal = e.getAttribute(attrName);
			System.out.println(attrVal);
		}
	}

	public int getTotalElementsCount(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("total elements for : " + locator + "--->" + eleCount);
		return eleCount;
	}

	public List<String> getElementsTextList(By locator) {
		List<String> eleTextList = new ArrayList<String>();// size=0
		List<WebElement> eleList = getElements(locator);
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	// *************************Select based drop down utils****************//

	public void doSelectDropDownWebElementIndex(WebElement element, int index) {
		Select select = new Select(getElement(element));
		select.selectByIndex(index);
	}

	public void doSelectDropDownWebElementValue(WebElement element, String value) {
		Select select = new Select(getElement(element));
		select.selectByValue(value);
	}

	public void doSelectDropDownWebElementVisibleText(WebElement element, String text) {
		Select select = new Select(getElement(element));
		select.selectByVisibleText(text);
	}

	public List<WebElement> getDropDownOptionsList(WebElement element) {
		Select select = new Select(getElement(element));
		return select.getOptions();
	}

	public List<String> getDropDownOptionsTextList(WebElement element) {
		List<WebElement> optionsList = getDropDownOptionsList(element);
		List<String> optionsTextList = new ArrayList<String>();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionsTextList.add(text);
		}
		return optionsTextList;
	}

	public void selectDropDownValue(WebElement element, String expValue) {
		List<WebElement> optionsList = getDropDownOptionsList(element);
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(expValue)) {
				e.click();
				break;
			}
		}
	}

	public int getTotalDropDownOptions(WebElement element) {
		int optionsCount = getDropDownOptionsList(element).size();
		System.out.println("total options ==> " + optionsCount);
		return optionsCount;
	}

	public void doSearch(By suggListLocator, String suggName) {
		List<WebElement> suggList = getElements(suggListLocator);
		System.out.println(suggList.size());

		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}

	// ************************Wait Utils **********************//
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementPresence(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(element));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public WebElement waitForElementVisible(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locator are visible. Visibility means that the elements are not
	 * only displayed but also have a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> waitForElementsVisible(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * 
	 * @param timeOut
	 * @return
	 */
	public Alert waitForAlertPresence(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		return waitForAlertPresence(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresence(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresence(timeOut).dismiss();
	}

	public void alertSendKeys(int timeOut, String value) {
		waitForAlertPresence(timeOut).sendKeys(value);
	}

	public String waitForTitleContainsAndFetch(int timeOut, String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();
	}

	public String waitForTitleIsAndFetch(int timeOut, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.titleIs(titleValue));
		return driver.getTitle();
	}

	public String waitForURLContainsAndFetch(int timeOut, String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlContains(urlFractionValue));
		return driver.getCurrentUrl();
	}

	public String waitForURLIsAndFetch(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.urlToBe(urlValue));
		return driver.getCurrentUrl();
	}

	public boolean waitForURLContains(int timeOut, String urlFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFractionValue));

	}

	public void waitForFrameAndSwitchToItWebElementIDOrName(int timeOut, String idOrName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
	}

	public void waitForFrameAndSwitchToItWebElementIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameAndSwitchToItWebElementFrameElement(int timeOut, WebElement frameElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public void waitForFrameAndSwitchToItWebElementFrameLoctor(int timeOut, WebElement frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param timeOut
	 * @param locator
	 */
	public void clickWhenReady(int timeOut, WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitForElementToBeClickable(int timeOut, WebElement locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doClickWithActionsAndWait(int timeOut, WebElement locator) {
		WebElement ele = waitForElementToBeClickable(timeOut, locator);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();
	}

	public WebElement waitForElementPresenceWithFluentWait(int timeOut, int pollingTime, By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(pollingTime)).withMessage("...element is not found on the page....");

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	public void waitForAlertWithFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoAlertPresentException.class).pollingEvery(Duration.ofSeconds(pollingTime))
				.withMessage("...Alert is not found on the page....");

		wait.until(ExpectedConditions.alertIsPresent());

	}

	/**
	 * @param productHeader
	 * @return
	 */

}
