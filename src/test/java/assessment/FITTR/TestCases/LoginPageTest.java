package assessment.FITTR.TestCases;

import static org.testng.Assert.assertFalse;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import fittr.assessment.PageClass.HomePage;
import fittr.assessment.PageClass.LoginPage;
import fittr.assessment.base.TestBase;
import fittr.assessment.testdata.DataProvider_FITTR;
import fittr.assessment.util.Report;
import fittr.assessment.util.Utility;

public class LoginPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	Utility util;
	private static ExtentReports extent;
	ExtentTest parent, testNode, firstChild;
	public ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal test = new ThreadLocal();
	Logger logger = LoggerFactory.getLogger(LoginPageTest.class);
	private int tcNo = 0;
	int stepNo = 0;
	String CurrentTime;
	
	public LoginPageTest(){
		super();
	}
	
	
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest_setup() throws Exception {
		System.out.println("beforeTest_setup");
		CurrentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());			
		initialization();	
		loginPage=new LoginPage();
		extent = Report.getInstance();
		parent = extent.createTest("FITTR-Login");
	}
	
	@BeforeMethod
	 public void beforeMethod() {
		 System.out.println("beforeMethod");
		 tcNo++;
			System.out.println("================================================");
			System.out.println("TC No : " + tcNo);
			logger.info("================================================");
	 }

	
	/**
	 * @author : Deepak Chakurkar
	 * @Date : 7th March 2020
	 * @Summary: Feature Tested- Navigate to Login Screen
	 * 
	 * Test Steps:
	 * 1. Navigate to FITTR url
	 * Verification Points:
	 * 1. Expected Title is displayed
	 */
	
	@Test(enabled = true, priority = 1)
	public void navigateToLoginScreen() throws Exception {
				
		firstChild = parent.createNode("Feature Tested- Navigate to FITTR."); 
		logger.info("Feature Tested- Navigate to FITTR Login Screen.");
        String pageTitle=loginPage.getPageTitle();
        System.out.println(pageTitle);
        String imagePath=util.captureScreenShot("FITTR","Login Screen");
        if(pageTitle.equalsIgnoreCase(prop.getProperty("Logintitle"))) {
        	logger.info("User is navigated to FITTR Login Screen.");
        	testNode.info("User is navigated to FITTR Login Screen.");
			testNode.log(Status.INFO, "FITTR:",MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());	
			testNode.log(Status.PASS,MarkupHelper.createLabel("Navigate to FITTR: TEST PASSED", ExtentColor.GREEN));
		}	else {
			logger.info("User is not navigated to FITTR Login Screen.");
			testNode.info("User is not navigated to FITTR.");
			testNode.log(Status.INFO, "FITTR-Login:",MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());	
			testNode.log(Status.FAIL,MarkupHelper.createLabel("Navigate to FITTR: TEST FAILED",ExtentColor.RED));
			Assert.fail("Navigate to Fittr: TEST FAILED");
		}
        	       
	}
	
	/**
	 * @author : Deepak Chakurkar
	 * @Date : 7th March 2020
	 * @Summary: Feature Tested- Log in into FITTR
	 * 
	 * Test Steps:
	 * 1. Navigate to FITTR url
	 * 2. Enter the Email and password. 
	 * 3. Click on Sign In Button
	 * Verification Points:
	 * 1. HomePage is displayed
	 */
	
	@Test(enabled=true,priority=2,dataProviderClass = DataProvider_FITTR.class, dataProvider = "Login")
	public void LoginIntoFITTR(String email, String password) throws Exception {
		firstChild = parent.createNode("Feature Tested- Log in into FITTR.");
		logger.info("Feature Tested- Login into FITTR.");
		homePage=loginPage.LoginIntoFITTR(email, password);
		String imagePath =util.captureScreenShot("FITTR","LogIn");
		String pageTitle=homePage.getPageTitle();
		if(pageTitle.equalsIgnoreCase(prop.getProperty("HomePagetitle"))) {
			logger.info("User is navigated to Dashboard. Login Successful");
        	testNode.info("User is navigated to Dashboard. Login Successful");
			testNode.log(Status.INFO, "FITTR-Login:",MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());	
			testNode.log(Status.PASS,MarkupHelper.createLabel("Log In into FITTR: TEST PASSED", ExtentColor.GREEN));
		}	else {
			logger.info("User is not navigated to FITTR Home Screen. Login Failed");
			testNode.info("User is not navigated to FITTR Home Screen. Login Failed");
			testNode.log(Status.INFO, "FITTR-Login:",MediaEntityBuilder.createScreenCaptureFromPath(imagePath).build());	
			testNode.log(Status.FAIL,MarkupHelper.createLabel("Log In into FITTR: TEST FAILED",ExtentColor.RED));	
			Assert.fail("Log in into FITTR: TEST FAILED");
		}
		
	}
	
	@AfterTest(alwaysRun = true)
	public void AfterTest_CleanUp() throws Exception {
		try {
			Thread.sleep(3000);
			stepNo = 0;
			System.out.println("End of this Test");
		    
		} catch (InterruptedException e) {
			logger.info("Failed to capture screenshot/close the driver. " + e);
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		try {
			Thread.sleep(1000);
			System.out.println("quitting driver");
			driver.quit();
		} catch (Exception e) {
			logger.info("Failed to close the driver. " + e);
			e.printStackTrace();
		}
	}

	
	

	
	

}
