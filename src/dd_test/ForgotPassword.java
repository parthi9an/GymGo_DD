package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class ForgotPassword extends testCore {

	/*
	 * Description:  To get forgotten password for client and trainer
	 * Author: Shrini Pandhare 
	 * Date of creation: Dec 2016
	 * Parameters: Test Case :
	 */
	
	@Test
	public void ClientTrainerForgotPassword() throws InterruptedException, IOException {
		
	// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("ForgotPassword");
		// If test run mode is Yes then execute the test.

		if (rowfromisExcec > 0) {
			// Get all the values from Excel sheet
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
			
			String clientEmailID = excelRead.readValue(DataSheet, rowfromisExcec, "clientEmailID");
			String trainerEmailID = excelRead.readValue(DataSheet, rowfromisExcec, "trainerEmailID");
			ForgotPasswordForBoth("CLIENT", clientEmailID);
			Thread.sleep(3000);
			ForgotPasswordForBoth("TRAINER", trainerEmailID);
		}
		else
			throw new SkipException("Testcase Skipped....");
	
	}
			

public void ForgotPasswordForBoth(String clientOrTrainer, String emailId) throws InterruptedException
{
	driver.get(config.getProperty("GymGoTestSite"));
	driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	Thread.sleep(3000);
	//Click Client tab
	driver.findElement(By.linkText(clientOrTrainer)).click();
	Thread.sleep(3000);
	//Click Forgot? link
	driver.findElement(By.xpath("//*[@id='home1']/div[1]/div/div[2]/form/div[2]/div/span")).click();
	driver.findElement(By.xpath("//*[@id='email']")).sendKeys(emailId);
	//Click Send New password button
	driver.findElement(By.xpath("//*[@id='changepdSave']")).click();
	}//ClientTrainerForgotPassword

}//ForgotPassword
