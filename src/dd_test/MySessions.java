package dd_test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.Test;

import dd_core.testCore;
import dd_utils.testUtils;

public class MySessions extends testCore {

	/*
	 * Description: My Classes- to view MY UPCOMING CLASSES and MY COMPLETED
	 * CLASSES Author: Shrini Pandhare Date of creation: Dec 2016 Parameters:
	 * Test Case :
	 */
	@Test
	public void MyUpComingClasses() throws InterruptedException, IOException {

		// Pass test case name and check whether RunMode is Yes.
		int rowfromisExcec = testUtils.isExecutableReturnRowNum("ClientLoginTest");
		// If test run mode is Yes then execute the test.
		if (rowfromisExcec > 0) {

			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
			driver.findElement(By.linkText("My Sessions")).click();
			driver.findElement(By.linkText("UPCOMING")).click();

			// You have no Upcoming Sessions message
			int nosessionMessage = driver.findElements(By.xpath(object.getProperty("YouhavenoUpcomingSessions"))).size();

			// If message is not displayed then sessions exists , otherwise click Completed
			if (nosessionMessage == 0) {

				// Get SESSION DETAIL , open it see the details and click Back
				// To All Sessions
				driver.findElement(By.xpath(object.getProperty("SessionDetail"))).click();
				// Get Class and Level
				System.out.println("Get Class and Level  "
						+ driver.findElement(By.xpath(object.getProperty("ClassLevel"))).getText());

				// Get Session Name and Service Type
				System.out.println("Session Name and Service Type "
						+ driver.findElement(By.xpath(object.getProperty("SessionNameAndServiceType"))).getText());

				// Get Trainer Name
				System.out.println("Get Trainer Name "
						+ driver.findElement(By.xpath(object.getProperty("TrainerName"))).getText());

				// Date Time Duration
				System.out.println("Date Time Duration "
						+ driver.findElement(By.xpath(object.getProperty("DateAndTime"))).getText());

				// Click Back To All Sessions
				driver.findElement(By.xpath(object.getProperty("BackToAllSessions"))).click();

				Thread.sleep(3000);

				// Join the class and perform System Test
				// If JOIN is visible then call ClientJoinClass class
				int joinSize = driver.findElements(By.xpath(object.getProperty("JOINButton"))).size();

				if (joinSize != 0) {
					// Click the Join button
					driver.findElement(By.xpath(object.getProperty("JOINButton"))).click();
					ClientJoinClass clientJoin = new ClientJoinClass();
					clientJoin.JoinTheClass();
				}

				// If System Test visible then call System Test Class
				int systemTestSize = driver.findElements(By.xpath(object.getProperty("SystemTestButton"))).size();
				System.out.println("systemTestSize " + systemTestSize);

				// Cancel
				int cancelSize = driver.findElements(By.xpath(object.getProperty("CancelButton"))).size();
				System.out.println("cancelSize " + cancelSize);
			} // if(nosessionMessage!=0)
			else
			{
				System.out.println("You have no Upcoming Sessions...");
			}
		} else
			throw new SkipException("Testcase Skipped....");
	}// MyUpComingClasses

	@Test
	public void MyCompletedClasses() throws InterruptedException {
		
		driver.findElement(By.linkText("My Sessions")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("COMPLETED")).click();

		// Check the message You have no Upcoming Sessions is displayed
		// If message displayed then no need of clicking Load More button
		int noUpcomingMsg = driver.findElements(By.xpath(object.getProperty("NoUpcomingSessions"))).size();
		if (noUpcomingMsg != 0) {
			// Click Load more
			driver.findElement(By.xpath(object.getProperty("LoadMoreButton"))).click();
			Thread.sleep(2000);
		}
		// Count completed classes
	}// MyCompletedClasses
}
