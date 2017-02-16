package dd_test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dd_core.testCore;

public class TermsofServicePrivacyPolicyContactSupport extends testCore {

	/*
	 * Description:  To view the content of Terms of Service | Privacy Policy | Contact Support
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */
	@Test
	public void ClickEachLinks() throws InterruptedException {
		// Click Terms of Service
		Thread.sleep(7000);
		driver.findElement(By.linkText("Terms of Service")).click();
		// Get handles of all the window opened
		ArrayList<String> TStab2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(TStab2.get(1)); // switch to new tab
		Thread.sleep(7000);
		driver.close();
		driver.switchTo().window(TStab2.get(0));

		// Click Privacy Policy
		driver.findElement(By.linkText("Privacy Policy")).click();
		// Get handles of all the window opened
		ArrayList<String> PPtab2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(PPtab2.get(1)); // switch to new tab
		Thread.sleep(7000);
		driver.close();
		driver.switchTo().window(PPtab2.get(0));

		// Click Contact Support
		driver.findElement(By.linkText("Contact Support")).click();
//		// Get handles of all the window opened
//		ArrayList<String> CStab2 = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(CStab2.get(1)); // switch to new tab
//		Thread.sleep(7000);
//		driver.close();
//		driver.switchTo().window(CStab2.get(0));
	}
}
