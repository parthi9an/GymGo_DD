package dd_test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dd_core.testCore;

public class FAQ extends testCore{

	/*
	 * Description:  FAQ
	 * Author: Shrini Pandhare 
	 * Date of creation: Dec 2016
	 * Parameters: Test Case :
	 */

@Test
	public void frequentlyAskedQuestion() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		driver.findElement(By.linkText("FAQs")).click();
		
//		// Click the FAQ (Updated - 10/16/2016) link
//		
//		// Get windows handlers for all the windows opened
//
//		ArrayList<String> tab2 = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tab2.get(1)); // switch to new tab
//		Thread.sleep(7000);// wait for the second window tab open
//		driver.close();// close the second tab
//		driver.switchTo().window(tab2.get(0));// switch back to original tab
	}
}
