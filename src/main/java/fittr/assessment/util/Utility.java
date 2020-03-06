package fittr.assessment.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import fittr.assessment.base.TestBase;


public class Utility extends TestBase{
	
	WebDriverWait wait;
	String imagePath="";
	final static org.apache.log4j.Logger logger = org.apache.log4j.LogManager.getLogger(Utility.class);
	
	public Utility(){
		super();
       
    }
	
	
	 /** 
		 * Capture screen shot  and store them under 'automationFileOutput' folder
		 * 
		 * @returns :String:ImagePath	
		*/ 
	     public String captureScreenShot(String moduleName,String testmethodName){			
			try {
				TakesScreenshot ts = (TakesScreenshot)driver;
				File Screenshot =ts.getScreenshotAs(OutputType.FILE);
				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
				imagePath=Browser.projectPath+"/automationFileOutput/"+ moduleName +"/" +testmethodName+"_"+timeStamp+".png";
				FileUtils.copyFile(Screenshot,new File(imagePath));
				
			} catch (WebDriverException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return imagePath;
		}
		
     
     
     /** 
 	 * wait until the page is loaded completely with the mentioned webelement
 	 * Conditions for wait is 1. Visibility of the Element  2. Element is ready for a Click 3. Wait for 5 seconds
 	 * @param  WebElement </input
 	 * @param  String : wait Condition  Eg: "visibility","Click","wait" 
 	 * @returns :	
 	*/ 
      public void waitForPageLoad(WebElement elementName , String actionType) throws Exception{		
 		try{
 			if(actionType.equalsIgnoreCase("click")){ 				
				WebDriverWait wait=new WebDriverWait(driver,500);
				wait.until(ExpectedConditions.elementToBeClickable(elementName));
				wait.ignoring(NoSuchElementException.class);
 			}else if(actionType.equalsIgnoreCase("visibility")) {
 				WebDriverWait wait=new WebDriverWait(driver,500);
				wait.until(ExpectedConditions.visibilityOf(elementName));
				wait.ignoring(NoSuchElementException.class);
 			}else if(actionType.equalsIgnoreCase("wait")){
 		    		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 			
 			}
 		}catch (NoSuchElementException e) {
             logger.info("Page is not loaded correctly! "+e);
             Assert.fail("Page is not loaded correctly! "+e);
         }catch (NoSuchSessionException e) {
             logger.info("Page is not loaded correctly! "+e);
             Assert.fail("Page is not loaded correctly! "+e);
         }catch (Exception e) {
             logger.info("Page is not loaded correctly! "+e.getMessage());
             Assert.fail("Page is not loaded correctly! "+e);
         }

 	}
      
      /** 
  	 * Returns true if a select option was found that matches the provided text.
  	 * @returns 
  	 */ 
       public boolean OptionExists(WebElement listElement, String optionText)
  	 {	
    	   boolean isExist=false;
  	 	
  	    Select listObject= new Select(listElement);
  		List<WebElement> options=listObject.getOptions();
  		if(options.size()>0){
  			for(int i=0;i<options.size();i++)
  		     {
  		         if(optionText.equalsIgnoreCase(options.get(i).getText())){
  		        	 isExist= true;
  		        	 break;
  		         }
  		     }
  		}else{
  			logger.info("List is Empty ");
  			Assert.fail("List is Empty ");
  		}
  	     return isExist;
    } 
      
      /** 
  	 *Select Value from a listBox Object
  	 * @returns 
  	 */ 
       public void selectValueFromListBox(WebElement listElement,String valueToSelect){
  		new Actions(driver).moveToElement(listElement).perform();
  		 Select listObject= new Select(listElement);
  		 if(OptionExists(listElement, valueToSelect)){
  			 listObject.selectByVisibleText(valueToSelect);
  		 }
  		
       }
  	 
  	 /** 
  	 *Get the selected option for 'List Object'
  	 * @returns String: current selection	
  	*/ 
       public String getSelectedValueForListBox(WebElement listElement){
  		new Actions(driver).moveToElement(listElement).perform();
  		Select listObject= new Select(listElement);
  		WebElement selctedOption=listObject.getFirstSelectedOption();
  		return selctedOption.getText();
  	}
  	
  	 /** 
  	 *Enter value to the mentioned field
  	 * @throws Exception 
  	 * @returns boolean : isInputSuccess
  	 */ 
       public boolean setValueToField(WebElement element,String valueToSet) throws Exception{
      	 try {
  		 new Actions(driver).moveToElement(element).perform();
  		 element.clear();
  		 element.sendKeys(valueToSet);
  		 return true;
      	 }catch (Exception e) {
      		 return false;
      	 }
  	 }
  	 
  	 /** 
  	 *Verify whether the field is 'Mandatory' or not
  	 * @returns boolean : 'true' if the field is mandatory,else 'false'	
  	 */ 
       public boolean isFieldMandatory(WebElement element){
  		boolean isMandatory=true;
  		String elementName="";
  		try{
  			 new Actions(driver).moveToElement(element).perform();
  			 elementName=element.getAttribute("required");
  			 if(!elementName.isEmpty()){
  				 isMandatory= elementName.equalsIgnoreCase("true")?false:true;
  			 }
  		}catch(NoSuchElementException e){
  			
  		}
  			return isMandatory;
  	 }
  	 
  	
  	/** 
  	 *Verify whether the field is 'ReadOnly' or not
  	 * 
  	 * @returns boolean : 'true' if the field is 'read only',else 'false'		
  	 */ 
       public boolean isFieldReadOnly(WebElement element){
  		boolean isReadOnly=true;
  		String elementName="";
  		try{
  			 new Actions(driver).moveToElement(element).perform();
  			 elementName=element.getAttribute("readOnly");
  			 if(!elementName.isEmpty()){
  				 isReadOnly= elementName.equalsIgnoreCase("true")?false:true;
  				// isReadOnly=true;
  			 }
  		}catch(NoSuchElementException e){
  			
  		}
  			return isReadOnly;
  	 }

  	 /** 
  	 * Get the value displayed in the field. 
  	 * @returns String : value displayed in the field	
  	 * @remarks values for 'key' : "Attribute" or "Value". 
  	 */ 
       public String getValueFromTheField(WebElement element ,String key){
      	 try{
      		 new Actions(driver).moveToElement(element).perform();
      	 }catch(Exception e){
      		 JavascriptExecutor js = (JavascriptExecutor) driver;
      		 js.executeScript("arguments[0].scrollIntoView();", element);
      	 }
      	 String txtValue="";
  		if(key.equalsIgnoreCase("value")){
  			txtValue=element.getText();
  		}else if(key.equalsIgnoreCase("Attribute")){
  			txtValue=element.getAttribute("value");
  		}
  			return txtValue;
  	 }
  	
  	 /** 
  	 *Get the Enable status of field 
  	 * 
  	 * @returns boolean : 'true' if the field is in 'Enable status',else 'false'	
  	 */ 
       public boolean getEnableStatusOfField(WebElement element){
  		 boolean enableStatus=false;
  		 
  		 try {
  			new Actions(driver).moveToElement(element).perform();
  			if(element.isDisplayed()){
  				enableStatus=element.isEnabled();
  			}
  		} catch (NoSuchElementException e) {
  			logger.info("Fied is NOT DISPLAYED to check whether it is enabled or disabled!!");
  		}
  		 return enableStatus;
  	 }
  	
  	 /** 
  	 *Get the Display status of field 
  	 * 
  	 * @returns boolean : 'true' if the field is 'Displayed' on the screen,else 'false'	
  	*/ 
       public boolean getDisplayStatusOfField(WebElement element){
  		boolean isDisplayed=false;
  		try {
  			//new Actions(driver).moveToElement(element).perform();
  			isDisplayed=element.isDisplayed();
  		} catch (NoSuchElementException e) {
  			isDisplayed=false;
  		}
  		 return isDisplayed;
  	 }
  	
  	 /** 
  	 *Get the 'Selected' status of field.Applicable Mainly for 'RadioButtons and Check boxes'
  	 *  @returns boolean : 'true' if the field is in 'Selected' state,else 'false'		
    	 */ 
       public boolean getSelectionStatusOfField(WebElement element){
  		boolean selectionStatus=false;
  		try{
  			if(element.isDisplayed()){
  				new Actions(driver).moveToElement(element).perform();
  				selectionStatus=element.isSelected();
  			}
  		}catch (Exception e){
  			
  		}
  		 return selectionStatus;
  	}
       
       /**  
   	 * Click on a button 
   	 * @returns boolean
   	*/ 
        public boolean clickOnButton(WebElement element,String name) throws Exception{
       	 boolean isClickSuccess=false;
   		Actions action = new Actions(driver);
   		action.moveToElement(element).build().perform();
   		WebElement elem = driver.switchTo().activeElement();
   		try {
   			element.click();
   			isClickSuccess=true;
   		} catch (NoSuchElementException e) {
   			logger.info(name+" Button is not Displayed to accept click!!");
   			Assert.fail(name+" Button is not Displayed to accept click!!");
   		}catch (StaleElementReferenceException e) {
   			logger.info(name+" Button is not in Enabled State to accept click!!");
   			Assert.fail(name+" Button is not in Enabled State to accept click!!");
   		}
   		
   		return isClickSuccess;
   	}
        

}
