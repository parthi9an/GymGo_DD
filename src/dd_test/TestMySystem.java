package dd_test;

import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import dd_core.testCore;
import dd_utils.testUtils;

public class TestMySystem extends testCore {

	/*
	 * Description: Create a New Class or Session by a Trainer Author: Shrini
	 * Pandhare Date of creation: Parameters: Test Case :
	 */
	@Test
	public void SystemCheck() throws InterruptedException, IOException, AWTException {
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("TestMySystem");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {

			driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
			// Click Test My System tab on menu
			driver.findElement(By.linkText("Test My System")).click();
			
			//Click Start Test button
			driver.findElement(By.xpath("//*[@id='start_test']/button")).click();
			//Click Next Step
			driver.findElement(By.xpath("//*[@id='browser_check']/button[2]")).click();

			
			
			//Popup may display
			

			
			ArrayList<String> popupAlloCamera = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("popupAlloCamera "+popupAlloCamera.size());
			driver.switchTo().window(popupAlloCamera.get(1));

			//Press Escape key programmatically
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ESCAPE);
			
			// Check Video window
			WebElement checkVideoWindow = (new WebDriverWait(driver, 20))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='video_check']/div[1]")));
			if (checkVideoWindow.isDisplayed()) {
				// Click yes button if video is displayed
				driver.findElement(By.xpath("//*[@id='video_check']/div[2]/div[2]/button")).click();
				Thread.sleep(2000);
				
				//Click Done
				driver.findElement(By.xpath("//*[@id='test_complete']/button[2]")).click();
				Thread.sleep(2000);
			} // if  
		} // if rowfromisExec
	} // SystemCheck
}// Class TestMySystem
