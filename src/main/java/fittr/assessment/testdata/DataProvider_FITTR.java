package fittr.assessment.testdata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import fittr.assessment.util.Browser;

public class DataProvider_FITTR {
	
	public static Workbook book;
	public static Sheet sheet;


	public static Object[][] getTestData(String sheetName) {

		try {
			FileInputStream ip = new FileInputStream(Browser.Path_TestData);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);

			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
			}
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// Test Data for functionality -Login
			@DataProvider(name = "Login")
			public static Object[][] Login() {
				Object[][] login = getTestData("Login");
				return login;

			}
			
			//Test data for Default Nutrition Plan
			@DataProvider(name = "nutritionPlan")
			public static Object[][] NutritionPlan() {
				Object[][] NutritionPlan = getTestData("NutrionPlan");
				return NutritionPlan;

			}
			
			//Test data for Coach Enrollment
			@DataProvider(name = "coach")
			public static Object[][] coachEnroll() {
				Object[][] coachEnroll = getTestData("coach");
				return coachEnroll;

			}
			
			

}
