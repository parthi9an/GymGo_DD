package dd_test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import dd_core.testCore;

public class Transactions extends testCore {
	/*
	 * Description:  View the Transactions and Buy credits
	 * Author: Shrini Pandhare
	 * Date of creation:
	 * Parameters:
	 * Test Case : 
	 */
	@Test
	public void transaction() {
		// Click Transactions Tab
		driver.findElement(By.xpath("// *[@class='dfd-icon-clipboard_money']")).click();
	}// transaction

}// Transactions
