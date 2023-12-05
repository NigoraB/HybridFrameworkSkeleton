/**
 * 
 */
package com.projectname.qa.listeners;

/**listing properties --
os.name=Mac OS X
java.version=11.0.17
user.name=.... 
 */
public class DemoClass {

	public static void main(String[] args) {
		//To retrieve system information, so we can use it in extentReport.setSystemInfo
	System.getProperties().list(System.out);
	}
}
