package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.ExcelReadWrite;
import dd_utils.testUtils;

public class LogOut extends testCore {
	
	/*
	 * Description:  For Client and Trainer Logout only
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */

	@Test
	public void doLoout() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		Thread.sleep(4000);
		// click userName and then click Logout
		driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[1]/div/div/div/div[2]/div/div[3]/p")).click();
		Thread.sleep(3000);         
		//Click Logout
		driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[1]/div/div/div/div[2]/div/div[3]/div/a")).click();
		//driver.quit();
	}// doLogout
}// LogOut
