package assessment.FITTR.TestCases;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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

public class TestCases extends TestBase{
	
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
	
	public TestCases(){
		super();
	}
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest_setup() throws Exception {
		System.out.println("beforeTest_setup");
		CurrentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());			
		initialization();	
		loginPage=new LoginPage();
		homePage = loginPage.LoginIntoFITTR(prop.getProperty("username"), prop.getProperty("password"));
		extent = Report.getInstance();
		parent = extent.createTest("FITTR-TestCases");
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
	 * @Summary: Feature Tested- Wellness Assessment and get the pdf.   
	 * 
	 * Test Steps:
	 * 1. Navigate to FITTR url
	 * 2. Log in into FITTR
	 * 3. Click on view Stat link
	 * 4. Click on My Plan Link
	 * 5. Click on Get Shredded 'View Link'
	 * Verification Points:
	 * 1. Pdf file is displayed
	 */
	
	@Test(enabled = true, priority = 1)
	public void getWellNessPlan() throws Exception {
			
		logger.info("Feature Tested- Wellness Assessment and get the pdf.");
        homePage.getwellnessAssessment();
	}
	
	/**
	 * @author : Deepak Chakurkar
	 * @Date : 7th March 2020
	 * @Summary: Feature Tested-profile update and based on that check Default Nutrition Chart   
	 * 
	 * Test Steps:
	 * 1. Navigate to FITTR url
	 * 2. Click on profile completion link
	 * 3. complete the profile
	 * 4. Click on View Nutrition Plan link
	 * 5. Click on View get shredded link
	 * Verification Points:
	 * 1. Pdf file is displayed
	 */
	
	@Test(enabled = true, priority = 2,dataProviderClass = DataProvider_FITTR.class, dataProvider = "nutritionPlan")
	public void DefaultNutrionPlan(String exerciseDuration,String foodChoice,String bodyFatPercantageRange, String height,String weight, String fitnessLevel) throws Exception {
			
		logger.info("Feature Tested- Wellness Assessment and get the pdf.");
        homePage.getDefaultNutritionPlan(exerciseDuration, foodChoice, bodyFatPercantageRange, height, weight, fitnessLevel);
	}
	
	/**
	 * @author : Deepak Chakurkar
	 * @Date : 7th March 2020
	 * @Summary: Feature Tested-Enroll with Coach  
	 * 
	 * Test Steps:
	 * 1. Navigate to FITTR url
	 * 2. Click on Enrol Now link
	 * 3. Search Coach by coach name
	 * 4. Select coach
	 * 5. Check slot availability
	 * 6. Click on Enrol
	 * 7. Select package
	 * 8. Add address
	 * Verification Points:
	 * 1. Pdf file is displayed
	 */
	
	@Test(enabled = true, priority = 3,dataProviderClass = DataProvider_FITTR.class, dataProvider = "coach")
	public void enrollWithCoach(String coachName,String packagePlan) throws Exception {
			
		logger.info("Feature Tested- Wellness Assessment and get the pdf.");
        homePage.enrollWithCoach(coachName, packagePlan);
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
