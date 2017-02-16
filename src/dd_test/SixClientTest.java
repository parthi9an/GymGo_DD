package dd_test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dd_core.testCore;

public class SixClientTest extends testCore{


		
		/*
		 * Description:  This is to test for Six Clients, this has to be executed independently
		 * Author: Shrini Pandhare
		 * Date of creation: Jan 2017
		 * Parameters:
		 * Test Case : 
		 */

	@Test
	public void SixClientTestAtOneTime() throws Exception
	{
		
		// Get current time
		Calendar calender = new GregorianCalendar();
		int hour=calender.get(Calendar.HOUR);
		int minute=calender.get(Calendar.MINUTE);
		int AMPM=calender.get(Calendar.AM_PM);
		String AM_PM="";
		int sessionTimeHr=0;
		int sessionTimemin=0;
		
		// 0 is AM 1 is PM
		if (AMPM==0)
			AM_PM="AM";
		else
			AM_PM="PM";
		
		
		// Ceil to Nearest time
		if((minute >=01) &&  (minute<20))	
		{	sessionTimeHr=hour;
			sessionTimemin=30;
		}	
		else if ((minute >=20) &&  (minute<50))	
			sessionTimeHr=hour+1;
		else
		{
			sessionTimeHr=hour+1;
			sessionTimemin=30;
		}	
		// Login as Trainer
		TrainerLogin();
		TrainerLogout();
		// Create A session based on the current time
		//System.out.println("session time is "+sessionTimeHr+" : "+sessionTimemin);
		//createSession();
		
		
		//Login as each user , search for the newly created spot and Reserve Spot and Logout
		
		ClientLogin("shrini+1@netesenz.com", "saturn");
		Thread.sleep(5000);
		driver.close();
		ClientLogin("shrini+2@netesenz.com", "saturn");
		//Login as Trainer and start the session
		
		//Login as each user and join the session
		
	}	


	public static void TrainerLogin() throws Exception
	{
		//Login as Trainer
		driver.get(config.getProperty("GymGoTestSite"));
		Thread.sleep(3000);

		// Application flow steps
		driver.findElement(By.linkText("TRAINER")).click();Thread.sleep(3000);
		driver.findElement(By.xpath(object.getProperty("trainerEmailID"))).sendKeys("shrini@netesenz.com");
		driver.findElement(By.xpath(object.getProperty("trainerPassword"))).sendKeys("saturn");
		driver.findElement(By.xpath(object.getProperty("trainerLogin"))).click();
	}

	public static void createSession() throws InterruptedException {
		// Click Create Session button after Login
		WebElement createsessionBtn = (new WebDriverWait(driver, 20)).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='sessionbox-createclassbtn']"))); // *[@id='page-top']/div[1]/div/div/div[4]/div/div/div[2]/a")));
		createsessionBtn.click();

		// Enter all the required fields
	
		driver.findElement(By.xpath("//*[@id='ses_name']")).sendKeys("6 Client Session");

		driver.findElement(By.xpath("//*[@id='ses_msg']")).sendKeys("To test the six(6) client login to a session, check for the visibility of all the 6 clients video");

		// Category
	
		Select sessionCategory = new Select(driver.findElement(By.xpath(object.getProperty("SessionCategory"))));
		sessionCategory.selectByVisibleText("Nutrition");

		// Beginner Intermediate Advanced, CLASS LEVEL
		Select sessionLevel = new Select(driver.findElement(By.xpath(object.getProperty("SessionLevel"))));
		sessionLevel.selectByVisibleText("Intermediate");

		// Service Type, 0 =1 to 1, 1=Small Group
		Select serviceType = new Select(driver.findElement(By.xpath(object.getProperty("ServiceType"))));
		
		serviceType.selectByVisibleText("SMALL GROUP PT");

		// Class Date , Clear before entry MM/DD/YYYY
		//driver.findElement(By.xpath(object.getProperty("SessionDate"))).clear();
		//driver.findElement(By.xpath(object.getProperty("SessionDate"))).sendKeys();

		// Session length
		Select classLengthSelect = new Select(driver.findElement(By.xpath(object.getProperty("SessionLength"))));
		classLengthSelect.selectByVisibleText("30 Minutes");

		// Session Time HH
		Select classTimeHHSelect = new Select(driver.findElement(By.xpath(object.getProperty("SessionTimeHH"))));
		//classTimeHHSelect.selectByVisibleText(classTimeHH);
		// Class time MM
		Select classTimeMMSelect = new Select(driver.findElement(By.xpath(object.getProperty("SessionTimeMM"))));
		//classTimeMMSelect.selectByVisibleText(classTimeMM);

		Select classTimeAMPMSelect = new Select(driver.findElement(By.xpath(object.getProperty("SessionTimeAMPM"))));
		//classTimeAMPMSelect.selectByIndex(0);

		// Click SUBMIT button
		driver.findElement(By.xpath(object.getProperty("SubmitButton"))).click();

		driver.findElement(By.xpath(object.getProperty("OKButton"))).click();
		TrainerLogout();
		
	}

	public static void TrainerLogout() throws InterruptedException
	{
		Thread.sleep(3000);
		// click userName and then click Logout
		driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[1]/div/div/div/div[2]/div/div[3]/p")).click();
		Thread.sleep(3000);
		//Click Logout
		driver.findElement(By.xpath("//*[@id='page-top']/div[1]/div/div/div[1]/div/div/div/div[2]/div/div[3]/div/a")).click();

	}
	
	public static void ClientLogin(String clientEmailID,String clientPassword) throws InterruptedException
	{
		// Go to test site every time... a fresh opening
		Thread.sleep(5000);
		driver.get(config.getProperty("GymGoTestSite"));

		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);

		driver.findElement(By.xpath(object.getProperty("clientemailIdText"))).sendKeys(clientEmailID);
		driver.findElement(By.xpath(object.getProperty("clientPassword"))).sendKeys(clientPassword);
		driver.findElement(By.xpath(object.getProperty("clientLoginButton"))).click();

	}
	
	
}
