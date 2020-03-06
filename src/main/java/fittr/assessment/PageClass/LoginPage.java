package fittr.assessment.PageClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import fittr.assessment.base.TestBase;
import fittr.assessment.util.Utility;

public class LoginPage extends TestBase{

	Utility util;

//****************************Object Repository***********************************

	@FindBy(xpath = "//input[@name='email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='adornment-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//span[contains(text(),'Sign In')]")
	WebElement btnSignIn;

//*********************Initialze Object Repository*******************************

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

//**********************Object Specific Method********************

	// Get the title of Screen
	private String getTitleOfTheScreen() {
		String value = driver.getTitle();
		return value;
	}

	// Get Display Status of field Email
	private boolean isEmailDisplayed() {
		boolean status = false;
		status = util.getDisplayStatusOfField(txtEmail);
		return status;
	}

	// Get Enable status of field EMail
	private boolean isEmailEnabled() {
		boolean status = false;
		status = util.getEnableStatusOfField(txtEmail);
		return status;
	}

	// set value in the field Email
	private void setValueEmail(String value) {
		 txtEmail.sendKeys(value);
		
	}

	// get Value from the Email text box

	private String readValueofEMail() {
		String value = util.getValueFromTheField(txtEmail, "value");
		return value;
	}

	// Get Display Status of field Password
	private boolean isPasswordDisplayed() {
		boolean status = false;
		status = util.getDisplayStatusOfField(txtPassword);
		return status;
	}

	// Get Enable status of field Password
	private boolean isPasswordEnabled() {
		boolean status = false;
		status = util.getEnableStatusOfField(txtPassword);
		return status;
	}

	// set value in the field Password
	private void setValuePassword(String value) {
		
		txtPassword.sendKeys(value);
		
	}

	// Click On Button 'Sign In'
	private void clickOnbtnSignIn() throws Exception {
		btnSignIn.click();
	}

	// Get 'Enable' status of Button 'Sign In'
	private boolean isbtnSignInEnabled() {
		boolean status = util.getEnableStatusOfField(btnSignIn);
		return status;
	}

	// Get 'Display' status of Button 'Sign In'
	private boolean isbtnSignInDisplayed() {
		boolean status = util.getDisplayStatusOfField(btnSignIn);
		return status;
	}
	
//*********************************Page Specific Method**************************************
	
	
	
	/** 
	 *  Get the Landing page Title
	 *  @return String : Page Title
	 */ 
    public String getPageTitle(){
		String value= getTitleOfTheScreen();
		return value;

	}
    
    /** 
	    *  Set value  for 'Email' Text Box
	    *  @param String: value
	    *  @return boolean : isSuccess
	    *  @remarks 
	    *   this API will key in 'Email' Text Box
	    */ 
	   public void setEmailAs(String value) throws Exception{		   			
	   			setValueEmail(value);	   	
	    	}
	   	
		
	    /** 
	   	 *  Get value displayed for the field 'Email' Text Box
	   	 *  @return String: value displayed in the field
	   	 */ 
	   	public String getValueOfEmail(){	
	   		String value = "";
	   		try {	
	   			value = readValueofEMail();
	   		}catch(Exception e) {
	   			
	   		}	
	   		return value;
	   	}
	   	/** 
	   	 *  Get Enable status of the field 'Email' Text Box
	   	 *  @return boolean
	   	 */ 
	   	public boolean getEnableStatusOfEmail(){	
	   		boolean value = false;
	   		try {			
	   			value = isEmailEnabled();
	   		}catch(Exception e) {
	   			
	   		}	
	   		return value;
	   	}
	   	/** 
	   	 *  Get Display status of the field 'Email' Text Box Text Box
	   	 *  @return boolean
	   	 */ 
	   	public boolean getDisplayStatusOfEmail(){	
	   		boolean value = false;
	   		try {
	   			value = isEmailDisplayed();
	   		}catch(Exception e) {
	   			
	   		}	
	   		return value;
	   	}
	   	
	   	/** 
		    *  Set value  for 'Password' Text Box
		    *  @param String: value
		    *  @return boolean : isSuccess
		    *  @remarks 
		    *   this API will key in 'Password' Text Box
		    */ 
		   public void setPasswordAs(String value) throws Exception{	
		   	setValuePassword(value);
		   }
		   	
		   	/** 
		   	 *  Get Enable status of the field 'Password' Text Box
		   	 *  @return boolean
		   	 */ 
		   	public boolean getEnableStatusOfPassword(){	
		   		boolean value = false;
		   		try {			
		   			value = isPasswordEnabled();
		   		}catch(Exception e) {
		   			
		   		}	
		   		return value;
		   	}
		   	/** 
		   	 *  Get Display status of the field 'Password' Text Box Text Box
		   	 *  @return boolean
		   	 */ 
		   	public boolean getDisplayStatusOfPassword(){	
		   		boolean value = false;
		   		try {
		   			value = isPasswordDisplayed();
		   		}catch(Exception e) {
		   			
		   		}	
		   		return value;
		   	}
		   	
			/**
			 * Click On Button 'Sign In'
			 * @return void
			 * @throws Exception
			 */
			public void clickOnButtonSignIn() throws Exception{	
				clickOnbtnSignIn();							
			}
			
			/**
			 * Get 'Enable' Status of Button 'Sign In'
			 * @return boolean :status
			 */
			public boolean getEnableStatusOfButtonSignIn(){	
				boolean status=isbtnSignInEnabled();
				return status;		
			}
			
			/**
			 * Get Display Status Of button  'Sign In'
			 * @return boolean:status
			 */
			public boolean getDisplayStatusOfButtonSignIn(){
				boolean status=isbtnSignInDisplayed();
				return status;
			}
			
//********************Work Flow***********************************************
			
			//This API will Perform Successful Sign In Operation on Login Page
			
			public HomePage LoginIntoFITTR(String Email, String password) throws Exception {
				setEmailAs(Email);
				setPasswordAs(password);
				clickOnButtonSignIn();
				
				return new HomePage();
				
			}

		

}
