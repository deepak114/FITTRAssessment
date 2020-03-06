package fittr.assessment.util;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Browser {
	
	public static WebDriver driver;
	 public static WebDriverWait wait;
	 public static String projectPath = System.getProperty("user.dir");
	 public static String applicationUrl ="https://www.fittr.com/auth";
	 public static final String Path_TestData = projectPath+"/src/main/java/fittr/assessment/testData/testData.xlsx";
	 public static final String File_TestData = "testData.xlsx";
	

	 
	 /** 
	 * Set Chrome profile
	 * 
	 * @returns :	
	 */ 
    public static WebDriver getChromeDriver(){
		System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver.exe");    	
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Arrays.asList("--start-maximized", "allow-running-insecure-content", "ignore-certificate-errors"));
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capabilities);
		wait = new WebDriverWait(Browser.driver, 4000);
		return driver;
	 }
	
	/** 
	 * Set Firefox Profile
	 * 
	 * @returns :	
	 */ 
    public static WebDriver getFireFoxDriver(){        
       System.setProperty("webdriver.gecko.driver",projectPath+"/drivers/geckodriver.exe");
       driver = new FirefoxDriver();
       return  driver;
	 }
	
	/** 
	 * Set Internet Explorer Profile
	 * 
	 * @returns :	
	 */ 
    public static WebDriver getInternetExplorerDriver(){		        
	        System.setProperty("webdriver.gecko.driver",projectPath+"/drivers/IEDriverServer.exe");
	        driver = new InternetExplorerDriver();
	        return  driver;
	 }
    
   

}
