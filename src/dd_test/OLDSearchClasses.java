package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class OLDSearchClasses extends testCore {

	/*
	 * Description: Search the required classes and reserve it , if No credits
	 * available then Buy package. Author: Shrini Pandhare Date of creation:
	 * Parameters: Test Case :
	 */
	@Test
	public void searchForClasses() throws InterruptedException, IOException {
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("SearchForClasses");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {

			// Call search by categories method
			SearchbyCategories();

			// Get the Caption of button RESERVE SPOT or Cancel
			String reserveSpotCancelButton = driver.findElement(By.xpath(object.getProperty("RESERVESPOTorCancel")))
					.getText();
			// Check if RESERVE SPOT is available
			if (reserveSpotCancelButton.equals("RESERVE SPOT")) {
				ReserveSpot(); // Call method
			} // if
		} // if
	} // searchForClasses

	public void SearchbyCategories() throws InterruptedException {
		// Click Search Classes tab on the menu
		driver.findElement(By.linkText("Search Classes")).click();
		Thread.sleep(2000);
		//		 Traverse through each categories, Move next and click the each categories
		 for (int i=2;i<17;++i)
		 {
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//*[@id='owl-demo2']/div[1]/div/div["+i+"]/div/a/div[1]/span")).click();
		
		 //click Next
		 driver.findElement(By.xpath("//*[@id='owl-demo2']/div[2]/div[2]")).click();
		 }// i
		
		 // Previous categories
		 for (int p=15;p>1;p--)
		 {
		 //click Prev button
		 driver.findElement(By.xpath("//*[@id='owl-demo2']/div[2]/div[1]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//*[@id='owl-demo2']/div[1]/div/div["+p+"]/div/a/div[1]/span")).click();
		 }// p
	}// SearchbyCategories

	public void SearchbyTrainer() {
		// Enter Trainer name and click Search
		driver.findElement(By.xpath("//*[@id='trainer']/div[1]/div/div/form/input")).sendKeys("Trainer Shrini");
		driver.findElement(By.xpath("//*[@id='search']")).click();
	}// SearchbyTrainer

	public void SearchbyDate() {
		// Select start and end date range click Search
		// from date
		driver.findElement(By.xpath("//*[@id='pickerMinSelector']")).sendKeys("12/05/2016");

		// To date
		driver.findElement(By
				.xpath("//*[@id='page-top']/div[1]/div/div/div[4]/div/div[1]/div/div/div/div[1]/div[1]/form/div[2]/div/div/input"))
				.sendKeys("12/06/2016");
		// Click Search button
		driver.findElement(By
				.xpath("//*[@id='page-top']/div[1]/div/div/div[4]/div/div[1]/div/div/div/div[1]/div[1]/form/div[3]/button"))
				.click();
	}// SearchbyDate

	/*
	 * To click Reserve Spot button, then confirm purchase, when no credit
	 * available then buy Credits
	 */
	public void ReserveSpot() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);

		// Click RESERVE SPOT button
		driver.findElement(By.xpath(object.getProperty("RESERVESPOTorCancel"))).click();
		Thread.sleep(3000);

		// click Yes Reserve It! button
		driver.findElement(By.xpath("//*[@id='page-top']/div[4]/div[7]/button[2]")).click();

		// Check for pop up, Insufficient Balance, If insufficientWindow is Displayed then click YES
		int yesSize = driver.findElements(By.xpath(object.getProperty("YesInsufficientWindow"))).size();
		System.out.println("yesSize =" + yesSize);
		if (yesSize != 0)
		{
			// Click Yes button
			driver.findElement(By.xpath(object.getProperty("YesInsufficientWindow"))).click();

			// Call Buy package
			BuyPoints buyPackage = new BuyPoints();
			buyPackage.BuyPackageSelectPoints();

			// After successful purchase... Click Search Classes tab
			driver.findElement(By.linkText("Search Classes")).click();
			Thread.sleep(3000);
			driver.findElement(By.linkText("SEARCH BY CATEGORIES")).click();
			Thread.sleep(2000);
			// Click Reserve Spot
			driver.findElement(By.xpath(object.getProperty("RESERVESPOTorCancel"))).click();
			// click Yes Reserve It! button
			driver.findElement(By.xpath("//*[@id='page-top']/div[4]/div[7]/button[2]")).click();
			// Click OK button in Successfully registered
			driver.findElement(By.xpath("//*[@id='page-top']/div[4]/div[7]/button[2]")).click();
			System.out.println(driver.findElement(By.xpath("//*[@id='owl-demo2']")).getText());
		} // end if
		
		else// YES button is not available, hence click OK
		{
			// click Yes Reserve It! button
//			driver.findElement(By.xpath("//*[@id='page-top']/div[4]/div[7]/button[2]")).click();
			Thread.sleep(3000);
			// OK button after reservation
			driver.findElement(By.xpath("//*[@id='page-top']/div[4]/div[7]/button[2]")).click();
		}
	}// ReserveSpot

	// Cancel Reserved Spot
	public void CancelReservedSpot() {
		// Get the Caption of button RESERVE SPOT or Cancel
		String reserveSpotCancelButton = driver.findElement(By.xpath(object.getProperty("RESERVESPOTorCancel")))
				.getText();
		if (reserveSpotCancelButton.equals("Cancel")) {
			// *[@id='allTrackersTable']/tbody/tr[1]/td[7]/a
		} // if
	}// CancelReservedSpot
	
	
}// SearchForClasses
