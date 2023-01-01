package com.orangeHRM.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHRM.pages.LoginPage;
import com.orangeHRM.pages.PIM_Module_Page;
import com.orangeHRM.base.BaseClass;

public class TestCases extends BaseClass {
	
	

	@DataProvider(name = "TestData")
	public Object[][] tData() {
		return new Object[][] { { "Admin", "admin123" }, { "Admin", "admin125" } };
	}

	@Test(dataProvider = "TestData")
	public static void LoginTest(String uname, String psw) throws IOException, InterruptedException {

		LoginPage lp = new LoginPage();
		test = extent.createTest("LoginTest");
		lp.Login(uname, psw);
		lp.validateLogin();

	}

	@Test(priority=1)
	public void addEmployee_test() throws Exception {
		LoginPage lp = new LoginPage();
		PIM_Module_Page pim = new PIM_Module_Page();
		test = extent.createTest("Add employee test");
		lp.Login("Admin", "admin123");
		lp.validateLogin();
		pim.add_Employee("Vigneshwer", "QA", "Tester", "1523011");
	}

	@Test (priority=2)
	public void editEmployee_test() throws Exception {
		test = extent.createTest("Edit employee Test");
		LoginPage lp = new LoginPage();
		lp.Login("Admin", "admin123");
		PIM_Module_Page pim = new PIM_Module_Page();
		pim.searcByID("1523011");
		pim.edit_employee_ID("1523");
	}

	@Test(priority=3)
	public void deleteEmployee_test() throws Exception {
		test = extent.createTest("Delete employee Test");
		LoginPage lp = new LoginPage();
		lp.Login("Admin", "admin123");
		Thread.sleep(5000);
		lp.validateLogin();
		PIM_Module_Page pim = new PIM_Module_Page();
		pim.deleteEmployee("1523");

	}

}
