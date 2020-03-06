package fittr.assessment.PageClass;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import fittr.assessment.base.TestBase;
import fittr.assessment.util.Utility;

public class HomePage extends TestBase{
	
	Utility util;

	
	@FindBy(xpath="//li[@class='nav-item sidebar-link py-2 mb-2 pl-4 active']//button[@class='jss29 jss3 jss5 jss8 jss2']")
	WebElement btnHome;
	
	@FindBy(xpath="//a[contains(text(),'View Nutrition Plan')]")
	WebElement linkViewNutritionPlan;
	
	@FindBy(xpath="//a[contains(text(),'View Training Plan')]")
	WebElement linkViewTrainingPlan;
	
	@FindBy(xpath="//a[contains(text(),'Profile Completion')]")
	WebElement linkProfileCompletion;
	
	@FindBy(xpath="//a[contains(text(),'Enrol Now')]")
	WebElement linkEnrolNow;
	
	@FindBy(xpath="//a[contains(text(),'View Stats')]")
	WebElement linkViewStats;
	
	@FindBy(xpath="//p[contains(text(),'My Plan')]")
	WebElement linkMyPlan;
	
	@FindBy(xpath="//p[contains(text(),'Get Shredded')]/following::a[1]")
	WebElement linkViewGetShrededPlan;
	
	@FindBy(xpath="//p[contains(text(),'Get Shredded')]/following::a[1]")
	WebElement linkDownloadGetShredded;
	
	@FindBy(xpath="//span[contains(text(),'NEXT')]")
	WebElement btnNext;
	
	@FindBy(xpath="//input[@placeholder='Enter height']")
	WebElement txtHeight;
	
	@FindBy(xpath="//input[@placeholder='Enter weight']")
	WebElement txtWeight;
	
	@FindBy(xpath="//span[contains(text(),'SUBMIT')]")
	WebElement btnSubmit;
	
	@FindBy(xpath="//input[@placeholder='Search by Coach Name']")
	WebElement txtSearchCoach;
	
	@FindBy(xpath="//span[contains(text(),'Enrol')]")
	WebElement btnEnrol;
	
	@FindBy(xpath="//div[@class='jss2494 jss2495']//button[@class='jss29 jss3 jss11 package_borderBtn__1PGEc']")
	WebElement btnProceedToCheckOut;
	
	@FindBy(xpath="//span[@class='package_slotCount__386pQ']")
	WebElement txtSlotAvailability;
	
	@FindBy(xpath="//div[@id='select-address']")
	WebElement txtAddressSelection;
	
	@FindBy(xpath="//i[@class='fa fa fa-angle-left fa-2x']")
	WebElement btngotoPreviousWindow;
	
	

//*********************Initialze Object Repository*******************************	
		
	public HomePage() {
			PageFactory.initElements(driver, this);
		}	

//**********************Object Specific Method********************	
	// Get the title of Screen
		private String getTitleOfTheScreen() {
			String value = driver.getTitle();
			return value;
		}
		
		//TestCase 1:Wellness Assessment and get the pdf. 
	    
	    private void WellnessAssessment()throws Exception
	    {
	    	linkViewStats.click();
	    	util.waitForPageLoad(linkMyPlan, "click");	    	
	    	linkMyPlan.click();
	    	util.waitForPageLoad(linkViewGetShrededPlan, "click");
	    	String parentWindow=driver.getWindowHandle();
	    	linkViewGetShrededPlan.click();
	    	Thread.sleep(5000);
	    	String childWindow=driver.getWindowHandle();
	    	driver.switchTo().window(parentWindow);
	    	
	    }
	    
	    //Profile Update 
	    
	    private void profileCompletion(String exerciseDuration,String foodChoice,String bodyFatPercantageRange, String height,String weight, String fitnessLevel) throws Exception {
	    	btnHome.click();
	    	util.waitForPageLoad(linkProfileCompletion, "click");
	    	linkProfileCompletion.click();
	    	WebElement exerciseFrequency=driver.findElement(By.xpath("//p[contains(text(),'"+exerciseDuration+"')]"));
	    	exerciseFrequency.click();
	    	btnNext.click();
	    	WebElement foodPreference=driver.findElement(By.xpath("//p[contains(text(),'"+foodChoice+"')]"));
	    	foodPreference.click();
	    	btnNext.click();
	    	WebElement bodyFatPer=driver.findElement(By.xpath("//p[contains(text(),'"+bodyFatPercantageRange+"')]"));
	    	bodyFatPer.click();
	    	btnNext.click();
	    	txtHeight.sendKeys(height);
	    	btnNext.click();
	    	txtWeight.sendKeys(weight);
	    	btnNext.click();
	    	WebElement currentFitnessLevel=driver.findElement(By.xpath("//p[contains(text(),'"+fitnessLevel+"')]"));
	    	currentFitnessLevel.click();
	    	btnSubmit.click();
	    }
	    
	    //Get Default Nutrition Plan
	    private void nutritionPlan() throws Exception{
	    	btnHome.click();
	    	Thread.sleep(2000);
	    	linkViewNutritionPlan.click();
	    	String parentWindow=driver.getWindowHandle();
	    	linkViewGetShrededPlan.click();
	    	String childWindow=driver.getWindowHandle();
	    	driver.switchTo().window(parentWindow);
	    	
	    }
	    
	    //Enroll with Coach
	    private void coachSelection(String coachName,String packagePlan) throws Exception{
	    	
	    	btnHome.click();
	    	linkEnrolNow.click();
	    	util.waitForPageLoad(txtSearchCoach, "click");
	    	txtSearchCoach.sendKeys(coachName+Keys.ENTER);
	    	WebElement coach=driver.findElement(By.xpath("//p[contains(text(),'"+coachName+"')]/preceding::img[1]"));
	    	coach.click();
	    	util.waitForPageLoad(btnEnrol, "click");
	    	btnEnrol.click();
	    	String slotAvailbility=txtSlotAvailability.getText();
	    	if(slotAvailbility.contains("available"));
	    	{
	    		WebElement packageOption=driver.findElement(By.xpath("//p[contains(text(),'"+packagePlan+"')]"));
	    		packageOption.click();
	    	}
	    	
	    	btnProceedToCheckOut.click();
	    	txtAddressSelection.click();
	    	txtAddressSelection.click();
	    	btngotoPreviousWindow.click();
	    	
	    	
	    	
	    }
		
//***************Page Specific Methods******************
		
		
	/** 
	 *  Get the Landing page Title
	 *  @return String : Page Title
	 */ 
    public String getPageTitle(){
		String value= getTitleOfTheScreen();
		return value;

	}
    
    //TestCase 1:Wellness Assessment and get the pdf. 
    
    public void getwellnessAssessment()throws Exception
    {
    	WellnessAssessment();
    	
    }
    
    //TestCase 2: Profile Update and check Default Nutrition Plan
    public void getDefaultNutritionPlan(String exerciseDuration,String foodChoice,String bodyFatPercantageRange, String height,String weight, String fitnessLevel) throws Exception {
    	profileCompletion(exerciseDuration,foodChoice,bodyFatPercantageRange,height,weight,fitnessLevel);
    	nutritionPlan();
    }
    
    //Text Case 3: Enroll with Coach
    public void enrollWithCoach(String coachName,String packagePlan) throws Exception {
    	coachSelection(coachName,packagePlan);
    }
    
    
    
}
