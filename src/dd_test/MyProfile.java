package dd_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dd_core.testCore;

public class MyProfile extends testCore {

	
	/*
	 * Description:  View past Classes and Profile view by Client
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */
	
	@Test
	public void profileDetails() throws InterruptedException {
		Thread.sleep(2000);
		// Click the My Profile tab
		driver.findElement(By.linkText("My Profile")).click();
		
		//get name
		System.out.println("Name= "+driver.findElement(By.xpath("//*[@id='profile']/div[3]/div/div/div/div[1]/div[1]/div[2]")).getText());
		// Personal Information
		System.out.println(" Bio ="+driver.findElement(By.xpath("//*[@id='profile']/div[3]/div/div/div/div[1]/div[2]/div[1]")).getText());
		
		// Get Order Details
		int orderDetails=driver.findElements(By.xpath("//*[@id='profile']/div[3]/div/div/div/div[2]/div/div[2]/div[1]")).size();
		System.out.println("orderDetails Visible?= "+orderDetails);
		//Click Edit Profile Button
		
		
	}// profileDetails

	
	public void editProfile()
	{
		//First name
		//*[@id='fname']
		
		// Last Name
		//*[@id='lname']
		
		//Email address
		//*[@id='email']
		
		//DOB
		//*[@id='page-top']/div[1]/div/div/div[2]/div/div/div/div/div/div/form/div[2]/div[2]/div[2]/div/input
		
		//Height Feet
		//*[@id='page-top']/div[1]/div/div/div[2]/div/div/div/div/div/div/form/div[2]/div[1]/div[3]/div[1]/div/select
		
		//Height Inch
		//*[@id='page-top']/div[1]/div/div/div[2]/div/div/div/div/div/div/form/div[2]/div[1]/div[3]/div[2]/div/select
		
		//Weight 
		//*[@id='page-top']/div[1]/div/div/div[2]/div/div/div/div/div/div/form/div[2]/div[2]/div[3]/div/input
		
		//Update button
		//*[@id='page-top']/div[1]/div/div/div[2]/div/div/div/div/div/div/form/div[2]/div[3]/div[2]/button
	}
} // MyProfile
