package dd_test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dd_core.testCore;

public class BuyPoints extends testCore {

	/*
	 * Description: To purchase the packages from the options by PayPal by
	 * entering email id and password 
	 * Author: Shrini Pandhare 
	 * Date of creation: Dec 2016 
	 * Parameters: 
	 * Test Case :
	 */

	@Test(priority=1)
	public void BuyPointsFromMenu() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Buy Points")).click();
		Thread.sleep(3000);
		BuyPackageSelectPoints();
	}
	public void BuyPackageSelectPoints() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// click SMALL GROUP
		driver.findElement(By.xpath("//span[contains(.,'SMALL GROUP')]")).click();
		//	driver.findElement(By.linkText("SMALL GROUP")).click();
		// Get content of each package box
	
		for (int i = 1; i < 5; i++) {
//			String packageContent = driver.findElement(By.xpath("(//*[contains(@class,'package_bg')])["+i+"]")).getText();
			String packageContent = driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[4]/div/div/div[3]/div/form/div[1]/div["+i+"]/div")).getText();
			System.out.println("\nPackage Content " + packageContent);
		}
		// Click 10 PTS
		driver.findElement(By.xpath("(//*[contains(@class,'package_bg')])[1]")).click();
		
		// Click Buy Points
		driver.findElement(By.xpath("//*[@id='search']")).click();

		// Pay with my PayPal account
		driver.findElement(By.xpath("//*[@id='loadLogin']")).click();

		// Login to Paypal use email id and password
		driver.findElement(By.xpath("//*[@id='login_email']")).clear();
		driver.findElement(By.xpath("//*[@id='login_email']")).sendKeys("finance-buyer@gymgo.com"); // Constant
		// always
		// Enter password
		driver.findElement(By.xpath("//*[@id='login_password']")).clear();
		driver.findElement(By.xpath("//*[@id='login_password']")).sendKeys("GymG0T3st");// Constant
		// always
		// Click Paypal Login
		driver.findElement(By.xpath("//*[@id='submitLogin']")).click();

		// Click PayNow button
		driver.findElement(By.xpath("//*[@id='continue_abovefold']")).click();

		// Check successfully purchased window displayed

		WebElement successfullyPurchased = (new WebDriverWait(driver, 20)).until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@id='merchantredirectform']/div/div/div[2]/h2")));
		System.out.println("successfullyPurchased " + successfullyPurchased);
	}// BuyPackageNow
}
