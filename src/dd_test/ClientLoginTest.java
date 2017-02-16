package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.ExcelReadWrite;
import dd_utils.testUtils;

public class ClientLoginTest extends testCore {

	/*
	 * Description: For Client Login only Author: Shrini Pandhare Date of
	 * creation: Parameters: Test Case : GYM_TC_SIT_REGISTER_01
	 */

	@Test
	public void doLogin() throws IOException // String emailId, String password
, InterruptedException
	{
		// Go to test site every time... a fresh opening
		driver.get(config.getProperty("GymGoTestSite"));
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("ClientLoginTest");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {
			XSSFSheet DataSheet = excelRead.Setsheet("GymGoTestData");
			// Wait for Client link to display
			WebElement clientLink = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(By.linkText("CLIENT")));
			clientLink.click();
			
			String clientEmailID = excelRead.readValue(DataSheet, rowfromisExcec, "clientEmailID");
			String clientPassword = excelRead.readValue(DataSheet, rowfromisExcec, "clientPassword");

			// driver.findElement(By.xpath(object.getProperty("clientLoginWithEmailButton"))).click();
			driver.findElement(By.xpath(object.getProperty("clientemailIdText"))).sendKeys(clientEmailID);
			driver.findElement(By.xpath(object.getProperty("clientPassword"))).sendKeys(clientPassword);
			driver.findElement(By.xpath(object.getProperty("clientLoginButton"))).click();
			Thread.sleep(5000);
		} else
			throw new SkipException("Testcase Skipped....");
	}// doLogin
	
	
	/*
	 *  To check the error message for invalid emails id and password
	 */
	
	public void InvalidLoginCheck()
	{
		driver.findElement(By.xpath(object.getProperty("clientemailIdText"))).sendKeys("invalidemail@invalid.com");
		driver.findElement(By.xpath(object.getProperty("clientPassword"))).sendKeys("invalid");
		driver.findElement(By.xpath(object.getProperty("clientLoginButton"))).click();
	}
	
}// ClientLoginTest
