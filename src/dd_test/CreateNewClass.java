package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class CreateNewClass extends testCore {

	/*
	 * Description:  Create a New Class or Session by a Trainer
	 * Author: Shrini Pandhare
	 * Date of creation: Dec 2016
	 * Parameters:
	 * Test Case : 
	 */

	@Test
	public void myClassesCreateNewClasses() throws IOException, InterruptedException {
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("CreateNewClass");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {
			// ****************** Trainer create classes
			// Define 10 seconds implicit wait
			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

			// Click Create Session button after Login
			WebElement createsessionBtn=(new WebDriverWait(driver, 20))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='sessionbox-createclassbtn']")));     //*[@id='page-top']/div[1]/div/div/div[4]/div/div/div[2]/a")));
			createsessionBtn.click();
																				//*[@id="page-top"]/div[1]/div/div/div[5]/div/div/div[2]/a
			// Get all the values from Excel sheet
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
			String className = excelRead.readValue(DataSheet, rowfromisExcec, "className");
			String classDescription = excelRead.readValue(DataSheet, rowfromisExcec, "classDescription");
			String classCategory = excelRead.readValue(DataSheet, rowfromisExcec, "classCategory");
			String classLevel = excelRead.readValue(DataSheet, rowfromisExcec, "classLevel");
			String classType = excelRead.readValue(DataSheet, rowfromisExcec, "classType");
			String classDate = excelRead.readValue(DataSheet, rowfromisExcec, "classDate");
			String classTimeHH = excelRead.readValue(DataSheet, rowfromisExcec, "classTimeHH");
			String classTimeMM = excelRead.readValue(DataSheet, rowfromisExcec, "classTimeMM");
			String classTimeAMPM = excelRead.readValue(DataSheet, rowfromisExcec, "classTimeAMPM");
			String classlength = excelRead.readValue(DataSheet, rowfromisExcec, "classLength");

			// Enter all the required fields
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ses_name']")).sendKeys(className);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id='ses_msg']")).sendKeys(classDescription);
			Thread.sleep(2000);
			// Category
			Select sessionCategory = new Select(driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[4]/div/div/div/form/div[3]/div[1]/div/select")));
		//	Select sessionCategory = new Select(driver.findElement(By.xpath("//form/div[3]/div[3]/div[@class='userregister-formgroup']/select")));
			sessionCategory.selectByVisibleText(classCategory);

			// Beginner Intermediate Advanced, CLASS LEVEL
			Select sessionLevel = new Select(driver.findElement(By.xpath(
					object.getProperty("SessionLevel"))));
			sessionLevel.selectByVisibleText("Intermediate");

			//Service Type, 0 =1 to 1, 1=Small Group
			Select serviceType=new Select(driver.findElement(By.xpath(object.getProperty("ServiceType"))));
			serviceType.selectByIndex(4);
	//		serviceType.selectByVisibleText(classType);
	/*		
			// Class Date , Clear before entry MM/DD/YYYY
			driver.findElement(
					By.xpath(object.getProperty("SessionDate")))
					.clear();
			driver.findElement(
					By.xpath(object.getProperty("SessionDate")))
					.sendKeys(classDate);
			
			//Session length
			Select classLengthSelect = new Select(driver.findElement(
					By.xpath(object.getProperty("SessionLength"))));
			classLengthSelect.selectByVisibleText("30 Minutes");
*/
			//Session Time HH
			Select classTimeHHSelect = new Select(driver.findElement(By.xpath(
					object.getProperty("SessionTimeHH"))));
			classTimeHHSelect.selectByVisibleText(classTimeHH);
			//Class time MM
			Select classTimeMMSelect = new Select(driver.findElement(By.xpath(
					object.getProperty("SessionTimeMM"))));
			classTimeMMSelect.selectByVisibleText(classTimeMM);

			Select classTimeAMPMSelect = new Select(driver.findElement(By.xpath(
					object.getProperty("SessionTimeAMPM"))));
			classTimeAMPMSelect.selectByIndex(1);

			// Click SUBMIT button
			driver.findElement(By.xpath(object.getProperty("SubmitButton"))).click();
			Thread.sleep(2000);

			//Not allowed to create past sessions 
			int notAllowedMsg=driver.findElements(By.xpath(object.getProperty("NotallowedCreatePastSessions"))).size();
			// If message ,
			// You have already created the session for this time		
			// OR Not allowed to create past sessions  
			// is displayed then cancel and return
			
			if (notAllowedMsg != 0) {
				// Click Cancel
				driver.findElement(By.xpath(object.getProperty("CancelButton"))).click();
				//driver.findElement(By.xpath("//*[contains(text(),' CANCEL')]")).click();
				System.out.println("Not allowed to create past sessions  , hence Cancelled");
			}
		} // if (rowfromisExcec > 0)
	} // myClasses method
}// MyClasses
