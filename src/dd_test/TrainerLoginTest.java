package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.SkipException;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class TrainerLoginTest extends testCore {
	/*
	 * Description:  To login by Trainer
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */
	@Test
	public void doLogin() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("TrainerLoginTest");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {
			// To get the Data from Excel
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
			String clientEmailID = excelRead.readValue(DataSheet, rowfromisExcec, "trainerEmailID");
			String clientPassword = excelRead.readValue(DataSheet, rowfromisExcec, "trainerPassword");
			// go to site everytime... a fresh opening
			driver.get(config.getProperty("GymGoTestSite"));
			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

			// Application flow steps
			Thread.sleep(4000);
			driver.findElement(By.linkText("TRAINER")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(object.getProperty("trainerEmailID"))).sendKeys(clientEmailID);
			driver.findElement(By.xpath(object.getProperty("trainerPassword"))).sendKeys(clientPassword);
			driver.findElement(By.xpath(object.getProperty("trainerLogin"))).click();
		} else
			throw new SkipException("Testcase Skipped....");
	} // doLogin()
}
