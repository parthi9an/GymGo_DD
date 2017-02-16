package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class TrainerSignUp extends testCore {

	/*
	 * Description:  Register a new Trainer
	 * Author: Shrini Pandhare
	 * Date of creation: Dec 2016
	 * Parameters:
	 * Test Case : 
	 */
	
	@Test
	public void trainerSignUp() throws IOException, InterruptedException {
		// go to site everytime... a frsh opening
		driver.get(config.getProperty("GymGoTestSite"));
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("TrainerSignUp");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");

			// I AM A TRAINER and Register Now are constant Links
			driver.findElement(By.linkText("TRAINER")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Sign Up")).click();

			// Get all the values from Excel sheet
			String firstName = excelRead.readValue(DataSheet, rowfromisExcec, "firstName");
			String lastName = excelRead.readValue(DataSheet, rowfromisExcec, "lastName");
			String emailId = excelRead.readValue(DataSheet, rowfromisExcec, "emailId");
			String password = excelRead.readValue(DataSheet, rowfromisExcec, "password");
			String confirmPassword = excelRead.readValue(DataSheet, rowfromisExcec, "confirmPassword");
			String bioBackground=excelRead.readValue(DataSheet, rowfromisExcec, "bioBackground");
			String trainingStyle=excelRead.readValue(DataSheet, rowfromisExcec, "trainingStyle");
			//String timeZone = excelRead.readValue(DataSheet, rowfromisExcec, "timeZone");
			//certificationType
			
			// Fields to be filled
			driver.findElement(By.xpath(object.getProperty("firstName"))).sendKeys(firstName);
			driver.findElement(By.xpath(object.getProperty("lastName"))).sendKeys(lastName);
			driver.findElement(By.xpath(object.getProperty("emailId"))).sendKeys(emailId);
			driver.findElement(By.xpath(object.getProperty("password"))).sendKeys(password);
			driver.findElement(By.xpath(object.getProperty("confirmPassword"))).sendKeys(confirmPassword);
			driver.findElement(By.xpath(object.getProperty("bioBackground"))).sendKeys(bioBackground);
			driver.findElement(By.xpath(object.getProperty("trainingStyle"))).sendKeys(trainingStyle);

			Select timezone = new Select(driver.findElement(By.xpath(object.getProperty("timeZone"))));
			timezone.selectByIndex(1);// New York time by default
			
//			Select certificationType = new Select(driver.findElement(By.xpath(object.getProperty("certificationType"))));
//			certificationType.selectByIndex(2);
			driver.findElement(By.xpath(object.getProperty("certificationType"))).sendKeys("Cooper");
			driver.findElement(By.xpath(object.getProperty("certificationType"))).sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			driver.findElement(By.xpath(object.getProperty("submitButton"))).click();
		} else
			throw new SkipException("Testcase Skipped....");
	}// registerAsClient
}// ClientRegister
