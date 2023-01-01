package com.orangeHRM.pages;

import org.checkerframework.common.subtyping.qual.Unqualified;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.Status;
import com.orangeHRM.utilities.Utils;

public class PIM_Module_Page extends Utils {
	
	@FindBy(xpath="//ul[@class='oxd-main-menu']//li[2]/a")
	WebElement pim_btn;
	
	@FindBy (xpath="//div[@class='orangehrm-paper-container']/div/button[1]")
	WebElement add_btn;
	
	@FindBy(xpath="//input[@placeholder='First Name']")
	WebElement emp_first_name;
	
	@FindBy(xpath="//input[@placeholder='Middle Name']")
	WebElement emp_middle_name;
	
	@FindBy(xpath="//input[@placeholder='Last Name']")
	WebElement emp_last_name;
	
	@FindBy(xpath="//div[@class='oxd-grid-2 orangehrm-full-width-grid']//input")
	WebElement emp_id;
	
	
	@FindBy(xpath="//div[@class='oxd-switch-wrapper']//input")
	WebElement creat_Login_checkbox;
	
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement save_btn;
	
	
	//Search function elements
	@FindBy(xpath="//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']")
	WebElement search_id;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement search_btn;
	
	
	@FindBy(xpath="//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']")
	WebElement success_mesg;
	
	//Edit employee elements
	@FindBy(xpath="//form[@class='oxd-form']/div[2]/div[1]/div[1]/div[1]/div[2]/input[1]")
	WebElement edit_emp_ID;
	
	@FindBy(xpath="//form[@class='oxd-form']/div[5]/button[1]")
	WebElement edit_save_btn;
	
	
	
	//Search results and edit elements
	@FindBy(xpath="//div[@class=\"oxd-table orangehrm-employee-list\"]/div[2]/div/div/div[9]/div/button[2]")
	WebElement edit_btn;
	
	
	//Delete element
	@FindBy (xpath="//body/div[@id='app']/div[@class='oxd-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container']/div[@class='orangehrm-paper-container']/div[@class='orangehrm-container']/div[@role='table']/div[@role='rowgroup']/div[@class='oxd-table-card']/div[@role='row']/div[1]")
	WebElement emp_det_checkbox;
	
	@FindBy(xpath="//div[@class='oxd-table orangehrm-employee-list']/div[2]/div/div/div[9]//button[1]")
	WebElement delete_btn;
	
	@FindBy(xpath="//button[normalize-space()='Delete Selected']")
	WebElement del_btn;
	
	@FindBy(xpath="//div[@class='oxd-sheet oxd-sheet--rounded oxd-sheet--white oxd-dialog-sheet oxd-dialog-sheet--shadow oxd-dialog-sheet--gutters orangehrm-dialog-popup']//div[3]/button[2]")
	WebElement delete_popup_btn;
	
	
	public PIM_Module_Page() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void add_Employee(String fname,String mname, String lname , String empID) throws Exception {
		
		
		
		clickElement(pim_btn, "PIM Button");
		clickElement(add_btn, "Add Button");
		typeText(emp_first_name, "First Name", fname);
		typeText(emp_middle_name, "Middle Name", mname);
		typeText(emp_last_name, "Last Name", lname);
		deleteText(emp_id);
		typeText(emp_id, "Emp ID", empID);
		String old_url = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee";
		Thread.sleep(5000);
		clickElement(save_btn, "Save Button");
		
		Thread.sleep(5000);
		String current_url = driver.getCurrentUrl() ;
		
		if(!(old_url.equals(current_url))){
			test.log(Status.PASS, "Employee added successfully...");
			
		}
		else {
			test.log(Status.FAIL, "Add employee is not sucessfull...");
		}
		
		
		
	}
	
	//Search by ID
	public void searcByID(String ID) throws Exception {
		clickElement(pim_btn, "PIM Button");
		typeText(search_id, "Employee ID", ID);
		clickElement(search_btn, "Search Button");
		Thread.sleep(10000);
		clickElement(edit_btn, "Edit Button");
		
	

	}
	
	//Edit employee
	public void edit_employee_ID(String new_ID) {
		deleteText(edit_emp_ID);
		typeText(edit_emp_ID, "Employee ID Field", new_ID);
		clickElement(edit_save_btn, "Edit Save Button");
		
		
	}
	
	
	//Delete employee
	public void deleteEmployee(String ID) throws InterruptedException {
		clickElement(pim_btn, "PIM Button");
		typeText(search_id, "Employee ID", ID);
		clickElement(search_btn, "Search Button");
		Thread.sleep(10000);
		clickElement(emp_det_checkbox, "Employee details selection checkbox");
		clickElement(del_btn, "Delete Selected button");
		clickElement(delete_popup_btn, "Delete employee popup button");
		Thread.sleep(10000);
	}
}