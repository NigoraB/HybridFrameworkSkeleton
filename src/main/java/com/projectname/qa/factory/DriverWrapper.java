package com.projectname.qa.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class DriverWrapper {

	private static WebDriver driver;
	String USERNAME = "nigorabowles";
	String ACCESS_KEY = "e32aab0c2ea547bb9caa2d61c2dad879";
	String SAUCE_URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

	@Parameters({ "url", "browserName" })
	@BeforeMethod
	public void initDriver(String appUrl, @Optional("chrome") String client) {
		System.out.println("Client name: " + client);
		switch (client.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "sauce":
			ChromeOptions browserOptions = new ChromeOptions();
			browserOptions.setPlatformName("macOS 14");
			browserOptions.setBrowserVersion("latest");

			try {
				driver = new RemoteWebDriver(new URL(SAUCE_URL), browserOptions);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		default:
			new Exception("invalid browser name: " + client);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// driver.get("https://clubs3qa1.scholastic.com/");
		driver.get("https://www.hotels.com/");

	}

	@AfterMethod
	public void quitDriver() {
		driver.quit();
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
