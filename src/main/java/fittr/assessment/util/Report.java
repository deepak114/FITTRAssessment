package fittr.assessment.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import fittr.assessment.base.TestBase;

public class Report extends TestBase{
	
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static WebDriver driver;
	static ExtentReports extent;
	final static Logger logger = LoggerFactory.getLogger(Report.class);


	public Report(WebDriver driver){
		this.driver = driver;
	}
	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance(System.getProperty("user.dir")+"\\Reports\\AutomationTestReport.html");		
			//createInstance(System.getProperty("user.dir")+"/Reports/");
	    return extent;
	}
	public static ExtentReports createInstance(String fileName) {
		//Working one
	   ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    htmlReporter.config().setEncoding("utf-8");
	    htmlReporter.config().setDocumentTitle("iFactory Automated TestResults");
	    htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
	    htmlReporter.config().setReportName("Regression TestResults");	    
	    htmlReporter.config().setCSS(".r-img { width: 50%; height: auto; }");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    


	    return extent;
	}



	

	//@AfterSuite
public void cleanUp(){
	extentReports.flush();
}



}
