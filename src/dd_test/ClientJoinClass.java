package dd_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import dd_core.testCore;

public class ClientJoinClass extends testCore{

	/*
	 * Description:  This is a common class to Join the session, this can be called from My class or from Search classes
	 * Author: Shrini Pandhare
	 * Date of creation: Dec 2016
	 * Parameters:
	 * Test Case : 
	 */

	@Test
	public void JoinTheClass()
	{
	//Join is clicked already in the calling class
	
		
	// Trainer video is dispalyed
	int trainerVideoSize=driver.findElements(By.xpath("//*[@id='trainer']")).size();
	System.out.println("trainerVideoSize "+trainerVideoSize);
	
	//Trainername
	String trainerName=driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[1]/div/div/div/div[2]/div/div[1]/span")).getText();
	System.out.println("trainerName "+trainerName);
	
	//TrainerHeartRate
	String trainerHeartRate = driver.findElement(By.xpath("//*[@id='trainerheartrate']")).getText();
	System.out.println("trainerHeartRate "+trainerHeartRate);
	
	//Session Title
	String sessionTitle = driver.findElement(By.xpath("//*[@id='sessiontitle']")).getText();
	System.out.println("sessionTitle "+sessionTitle);
	
	//Display Time
	String timeDisplayed = driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div/div[1]/div[3]")).getText();
	System.out.println("timeDisplayed "+timeDisplayed);
	
	//Client Video
	
//	WebElement clientVideo =(new WebDriverWait(driver, 20))
//			.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='OT_ec635d0f-837e-4ec8-a7f6-ba4e8c39caed']/div[1]/video")));
//
//	System.out.println("Client Video displayed? ="+ clientVideo.isDisplayed());

	int clientVideoSize = driver.findElements(By.xpath("//*[@id='OT_512548f5-2802-40b3-a391-c549bd09a9b1']/div[1]/video")).size();
	System.out.println("clientVideoSize "+clientVideoSize);
	
	//Client Name
	String clientName=driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[1]/span")).getText();
	System.out.println("clientName "+clientName);
	
	//Wearable connected
	String werableConnected = driver.findElement(By.xpath("//*[@id='nowearablemsg']/h3")).getText();
	System.out.println("werableConnected "+werableConnected);
	
	//Exit Session
	driver.findElement(By.xpath("html/body/div[1]/div/div/div/div[2]/div/div[1]/button")).click();
	
	
	}//JoinTheClass
}
