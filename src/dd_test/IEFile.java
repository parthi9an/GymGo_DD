package dd_test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class IEFile {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		WebDriver driver=new InternetExplorerDriver();
		//WebDriver driver=new FirefoxDriver();
		driver.get("https://qa.gymgoapp.com");
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		

		
		//User id
		driver.findElement(By.xpath("//*[@id='exampleInputEmail1']")).sendKeys("shripand@yahoo.com");
		
		driver.findElement(By.xpath("//*[@id='exampleInputpwd1']")).sendKeys("saturn");
		
		driver.findElement(By.xpath("//*[@class='loginpage-loginbtn waves-effect waves-light']")).click();
		

	}

}
