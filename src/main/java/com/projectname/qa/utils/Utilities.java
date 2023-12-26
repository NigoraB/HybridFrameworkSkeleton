/**
 * 
 */
package com.projectname.qa.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

/**
 * 
 */
public class Utilities {

	public static String generateEmailWithTimestamp() {

		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "test" + timestamp + "@gmail.com";
	}

	public static String captureScreenshot(WebDriver driver, String testName) {

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}
}
