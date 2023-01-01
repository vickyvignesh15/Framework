package com.orangeHRM.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.orangeHRM.utilities.Utils;

public class LoginPage extends Utils {
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement login_btn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Login Functionality
	public void Login(String uname, String psw) throws IOException {

		typeText(username, "Username", uname);
		typeText(password, "Password", psw);
		clickElement(login_btn, "Login button");
		

		
	}
	
	// Validating login
	public void validateLogin() {
		String acutalUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		String expUrl = driver.getCurrentUrl();
		
		if(acutalUrl.equals(expUrl)) {
			test.log(Status.PASS, "Login is sucessfull...");
		}
		else {
			test.log(Status.FAIL, "Login is Failed...!");
		}
	
		
	}

		
	}


