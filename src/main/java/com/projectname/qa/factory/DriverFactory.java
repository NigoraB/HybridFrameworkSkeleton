/**
 * 
 */
package com.projectname.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.projectname.qa.constants.AppConstants;
import com.projectname.qa.exception.FrameworkException;

/**
 * 
 */
public class DriverFactory {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	File propFile;
	File dataPropFile;
	FileInputStream fis;
	FileInputStream fis2;

	public DriverFactory() {

		prop = new Properties();
		propFile = new File(
				System.getProperty("user.dir") + "/src/main/java/com/projectname/qa/config/Config.properties");

		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataProp = new Properties();
		dataPropFile = new File(
				System.getProperty("user.dir") + "/src/main/java/com/projectname/qa/testdata/testdata.properties");
		try {
			fis2 = new FileInputStream(dataPropFile);
			dataProp.load(fis2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenAppURL(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("plz pass the right browser name...." + browserName);	
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION....");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConstants.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(AppConstants.PAIGE_WAIT_TIME));
		driver.get(prop.getProperty("url").trim());
		return driver;
	}

}
