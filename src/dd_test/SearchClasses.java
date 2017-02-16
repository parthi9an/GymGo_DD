package dd_test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class SearchClasses extends testCore {

	/*
	 * Description: Search the required classes and reserve it , if No credits
	 * available then Buy package. Author: Shrini Pandhare Date of creation:
	 * Parameters: Test Case :
	 */
	@Test
	public void searchForClasses() throws InterruptedException, IOException {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("SearchForClasses");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {

			// Call search by categories method
			SearchbyCategories();
			// Call search by date
			SearchbyDate();
			// Call Search by Time
			SearchByTime();
			// Call SearchBy Trainer
			SearchbyTrainer();
			// Get Trainer and session details
			SessionDetailAndTrainerProfile();
			// Reserve the spot
			ReserveSpot();

		} // if
	} // searchForClasses

	/*
	 * Method to search the session by Category
	 * 
	 * 
	 */
	public void SearchbyCategories() throws InterruptedException {
		// Click Search Sessions tab on the menu
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Search Sessions")).click();
		Thread.sleep(2000);

		// Traverse through each category
		for (int i = 0; i < 11; i++) {
			Select categories = new Select(driver.findElement(By.xpath(object.getProperty("allCategories"))));
			categories.selectByIndex(i);
			Thread.sleep(2000);
			// Click Search button
			driver.findElement(By.xpath("//*[@class='search-btn m-t-24']")).click();
		}
		// Reset category to AllCategories
		Select categories = new Select(driver.findElement(By.xpath(object.getProperty("allCategories"))));
		categories.selectByIndex(0);

		// Check if any session box is available.
		int sessionAvailable = driver
				.findElements(By.xpath("//*[contains(@class,'search-sessionbox')]")).size();
		
	}// SearchbyCategories

	public void SearchbyTrainer() throws InterruptedException {
		// Enter Trainer name and click Search
		driver.findElement(By.xpath("//*[@name='trainerName']")).sendKeys("Sara");
		Thread.sleep(2000);
		// Click Search button
		driver.findElement(By.xpath("//*[@class='search-btn m-t-24']")).click();

		// Set null value and click search
		driver.findElement(By.xpath("//*[@name='trainerName']")).clear();
		// Click Search button
		driver.findElement(By.xpath("//*[@class='search-btn m-t-24']")).click();
	}// SearchbyTrainer

	public void SearchbyDate() throws InterruptedException {
		// Select start and end date range click Search
		// Get System Date
		LocalDate localDate = LocalDate.now();
		String todayDate = DateTimeFormatter.ofPattern("MM/dd/YYYY").format(localDate);
		String fromdate = todayDate;

		// String todate=
		// localDate.getMonthValue()+"/"+Integet(localDate.getDayOfMonth())+2+"/"+localDate.getYear();

		/*
		 * // From Date
		 * driver.findElement(By.xpath("//*[@id='startDate']")).clear();
		 * driver.findElement(By.xpath("//*[@id='startDate']")).sendKeys(
		 * fromdate);
		 * 
		 * // To Date
		 * driver.findElement(By.xpath("//*[@id='endDate']")).clear();
		 * driver.findElement(By.xpath("//*[@id='endDate']")).sendKeys(todate);
		 * Thread.sleep(2000);
		 */

	}// SearchbyDate

	/*
	 * To select the time range from drop down 12am - 6am, 6am - 9am,9am -
	 * 12pm,12pm - 3pm,3pm - 6pm,6pm - 9pm,9pm - 12am
	 * 
	 */
	public void SearchByTime() throws InterruptedException {
		// Time Range
		for (int i = 0; i <= 7; i++) {
			Select timeRange = new Select(driver.findElement(By.xpath("//*[@id='timeRange']")));
			timeRange.selectByIndex(i);
			Thread.sleep(2000);
			// Click Search button
			driver.findElement(By.xpath("//*[@class='search-btn m-t-24']")).click();
		}
		// Reset to ANY option
		Select timeRange = new Select(driver.findElement(By.xpath("//*[@id='timeRange']")));
		timeRange.selectByIndex(0);
	}

	/*
	 * To click Reserve Spot button, then confirm purchase, when no credit
	 * available then buy Credits
	 */
	public void ReserveSpot() throws InterruptedException {

		// Click RESERVE SPOT button
		// Check whether sessions are visible
		int reserveSpotVisible = driver.findElements(By.xpath("//*[contains(@class,'search-sessionbox')]")).size();
		if (reserveSpotVisible != 0) {
			// Click First available Reserve Spot button
			driver.findElement(By.xpath("(//*[contains(@class,'searchsessionbox-joinclassbtn')])[1]")).click();
			Thread.sleep(3000);

			// Confirm Reservation, Click Yes, Reserve It button
			driver.findElement(By.xpath("//button[contains(text(),'Yes, reserve it!')]")).click();

			// Check for pop up, Insufficient Balance, If insufficientWindow is

			int InsufficientPoint = driver.findElements(By.xpath(object.getProperty("InsufficientPoint"))).size();
			System.out.println("InsufficientPoint =" + InsufficientPoint);
			// Insufficient Points window is displaying
			Thread.sleep(4000);
			if (InsufficientPoint != 0) {
				// Click Buy Points button
				driver.findElement(By.xpath("//button[contains(text(), 'Buy Points')]")).click();
				Thread.sleep(2000);
				// Call Buy Points
				BuyPoints buyPoints = new BuyPoints();
				buyPoints.BuyPackageSelectPoints();
			} // end if (InsufficientPoint != 0)

		} /// (reserveSpotVisible != 0)
	}// ReserveSpot

	// Cancel Reserved Spot
	public void CancelReservedSpot() {
		// Get the Caption of button RESERVE SPOT or Cancel
		String reserveSpotCancelButton = driver.findElement(By.xpath(object.getProperty("RESERVESPOTorCancel")))
				.getText();
		if (reserveSpotCancelButton.equals("Cancel")) {
		} // if
	}// CancelReservedSpot

	// Get Trainer Profile and Session Detail
	public void SessionDetailAndTrainerProfile() {
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
	    // Click To Get Session Detail

//		driver.findElement(By.xpath("((//*[contains(@class,'searchsessionbox-classdescriptiontext')])[1])")).click();
		// Click Back to All Sessions
//		driver.findElement(By.xpath("//a[text()='Back to All Sessions ']")).click();
		// Get Trainer Profile
//		driver.findElement(By.xpath("(//*[contains(@class,'showClickable')])[1]")).click();
		// Click Back to All Sessions
//		driver.findElement(By.xpath("//a[text()='Back to All Sessions ']")).click();
	}
}// SearchForClasses
