package com.orangeHRM.utilities;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {

	public static WebDriver driver;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static String dateandTime;

	
	
	// Getting prop value
		public static String getPropVal(String key) {
			String value = null;
			try {
				if (value == null) {
					Properties prop = new Properties();
					FileInputStream ip = new FileInputStream(
							System.getProperty("user.dir") + "\\src\\test\\resources\\ConfigFiles\\config.properties");
					prop.load(ip);
					value = prop.getProperty(key);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
		
	// Browser Launch
	public static void browserLaunch(String bname) {

		switch (bname) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Launching Chrome browser..");
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("No valid broswer name launching the default chrome browser...");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// Launch Url
	public static void launchUrl(String url) {
		driver.get(url);
	}

	// Checking element availabiltiy
	public static boolean waitforme(WebElement element, String elementName) {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			test.log(Status.PASS, elementName + " is available");
			flag = true;

		} catch (Exception e) {
			System.out.println("Error occured in filding the WebElement" + e.getMessage());
			test.log(Status.FAIL, elementName + " is not available");
		}
		return flag;
	}

	// Click action
	public static void clickElement(WebElement element, String elementName) {

		try {
			if (waitforme(element, elementName)) {
				element.click();
				test.log(Status.PASS, elementName + " is clicked..");
			}

		} catch (Exception e) {
			System.out.println("Error in clicking the webElement" + e.getMessage());
			test.log(Status.FAIL, elementName + "Failed to click the element");
		}

	}

	// Getting date and time
	public static String GetDateandTime() {
		DateFormat dateFormat = null;
		Date date = null;
		try {
			dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			date = new Date();
		} catch (Exception e) {
			System.out.println("Error in Getdateandtime : " + e.getMessage());
		}

		return dateFormat.format(date);
	}

	// Extent report starter

	public void reportStarter() {
		try {
			dateandTime = GetDateandTime();
			htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\Reports\\TestReport.html");
			htmlReporter.config().setDocumentTitle("OrangeHRM Test Report");
			htmlReporter.config().setReportName("Functional Test Report");
			htmlReporter.config().setTheme(Theme.DARK);

			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			extent.setSystemInfo("Hostname", "LocalHost");
			extent.setSystemInfo("Product Name", "OrangeHRM");
		} catch (Exception e) {
			System.out.println("Error in starting extent report:" + e.getMessage());

		}
	}

	// Report Finisher
	public static void extentReportFinisher() {

		try {
			extent.flush();
		} catch (Exception e) {
			System.out.println("Error in ExtentReportFinisher : " + e.getMessage());
		}
	}
	
	// Type text
	public static void typeText(WebElement element, String elementName, String text) {
		
		try {
			if (waitforme(element, elementName)) {
				element.sendKeys(text);
				test.log(Status.PASS, "Typed " + text + " in the text box " + elementName);
			}
		} catch (Exception e) {
			System.out.println("Error in typing text in the WebElement" + e.getMessage());
			test.log(Status.FAIL, "Failed to type the text  the element :" + elementName);
		}

	}
	
	// Select values from drop down
	public static void selectDeopdown(WebElement element, String text) {
		Select sc = new Select(element);
		sc.selectByVisibleText(text);
		
	}
	
	//Alert Handling
	public void acceptingAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	//Mouse hovering{
	public void mouseHoverandClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.moveToElement(element).click().build().perform();;
		
	}
	
	
	
	
	//Actions methos
	public void deleteText(WebElement element) {
		//Actions ac = new Actions(driver);
		element.sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);;
		//ac.keyUp(element, Keys.BACK_SPACE);
	}
	
	//Getting window handle
	public String gettingWindowHandle() {
		return driver.getWindowHandle();
	}
	
	// Close the browser

	public void closeBrowser() {
		driver.close();

	}

}
