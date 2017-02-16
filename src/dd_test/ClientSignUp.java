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

public class ClientSignUp extends testCore {

	/*
	 * Description:  Register a new client
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */
	
	@Test
	public void clientSignUp() throws IOException, InterruptedException {
		// go to site everytime... a frsh opening
		driver.get(config.getProperty("GymGoTestSite"));
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("ClientSignUp");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");

			// I AM A CLIENT and Register Now are constant Links
			driver.findElement(By.linkText(object.getProperty("iAmClient"))).click();
			driver.findElement(By.linkText(object.getProperty("registerNow"))).click();
			Thread.sleep(3000);
			// Cancel button and control should go to Login page
			driver.findElement(By.xpath(object.getProperty("cancelSignUpButton"))).click();
			
			//Again click SignUp
			driver.findElement(By.linkText(object.getProperty("registerNow"))).click();
			
			
			// Get all the values from Excel sheet
			String firstName = excelRead.readValue(DataSheet, rowfromisExcec, "firstName");
			String lastName = excelRead.readValue(DataSheet, rowfromisExcec, "lastName");
			String emailId = excelRead.readValue(DataSheet, rowfromisExcec, "emailId");
			String DoB = excelRead.readValue(DataSheet, rowfromisExcec, "DoB");
			String password = excelRead.readValue(DataSheet, rowfromisExcec, "password");
			String confirmPassword = excelRead.readValue(DataSheet, rowfromisExcec, "confirmPassword");
			String heightInFeet = excelRead.readValue(DataSheet, rowfromisExcec, "heightInFeet");
			String heightInInches = excelRead.readValue(DataSheet, rowfromisExcec, "heightInInches");
			String weightInPounds = excelRead.readValue(DataSheet, rowfromisExcec, "weightInPounds");
			String timeZone = excelRead.readValue(DataSheet, rowfromisExcec, "timeZone");

			// Fields to be filled
			driver.findElement(By.xpath(object.getProperty("firstName"))).sendKeys(firstName);
			driver.findElement(By.xpath(object.getProperty("lastName"))).sendKeys(lastName);
			driver.findElement(By.xpath(object.getProperty("emailId"))).sendKeys(emailId);
			driver.findElement(By.xpath(object.getProperty("DoB"))).clear();
			driver.findElement(By.xpath(object.getProperty("DoB"))).sendKeys(DoB);
			driver.findElement(By.xpath(object.getProperty("DoB"))).sendKeys(Keys.ENTER);
			
			System.out.println("Dob "+DoB);
			Select heightFeet = new Select(driver.findElement(By.xpath(object.getProperty("heightInFeet"))));
			heightFeet.selectByValue(heightInFeet);

			Select heightInch = new Select(driver.findElement(By.xpath(object.getProperty("heightInInches"))));
			heightInch.selectByValue(heightInInches);
			driver.findElement(By.xpath(object.getProperty("weightInPounds"))).sendKeys("100");
			
			driver.findElement(By.xpath(object.getProperty("clientSignUpPassword"))).sendKeys(password);
			driver.findElement(By.xpath(object.getProperty("clientSignUpconfirmPassword"))).sendKeys(confirmPassword);

			Select timezone = new Select(driver.findElement(By.xpath(object.getProperty("clientSignUptimeZone"))));
			timezone.selectByIndex(1);// New York time by default

			driver.findElement(By.xpath(object.getProperty("clientSignUpsubmitButton"))).click();
		
			//Check if message is displayed "Someone else is already registered with this email address
			int alreadyRegisteredMsg=driver.findElements(By.xpath(object.getProperty("SomeoneAlreadyRegisteredEmailMessage"))).size();
			
			if (alreadyRegisteredMsg !=0)
			{
				System.out.println("Someone else is already registered with this email address...Not registered..");
					// Cancel button and control should go to Login page
				driver.findElement(By.xpath(object.getProperty("cancelSignUpButton"))).click();
			}
		
		} else
			throw new SkipException("Testcase Skipped....");
	}// registerAsClient
}// ClientRegister
