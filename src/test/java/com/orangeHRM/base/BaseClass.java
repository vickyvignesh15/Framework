package com.orangeHRM.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.Status;
import com.orangeHRM.utilities.Utils;

public class BaseClass extends Utils{
	
	@BeforeSuite
	public void setupReport() {
		reportStarter();
	}

	@BeforeMethod
	@Parameters("testcase")
	public void BeforeTest() {
		
		browserLaunch(getPropVal("browser"));
		launchUrl(getPropVal("url"));
	}

	@AfterMethod
	public void AfterTest() throws Exception {
			closeBrowser();
	}

	@AfterSuite
	public void closeReport() {
		extentReportFinisher();
	}

}
