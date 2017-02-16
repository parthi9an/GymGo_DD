package dd_core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import dd_utils.ExcelReadWrite;

public class testCore {

/* Initialize, load properties file,initialize webdriver, generate logs.
 *  	
 */

	public static Properties config = new Properties();
	public static Properties object = new Properties();
	
	//Copy XLS Reader
	public static ExcelReadWrite excelRead=null;
	public static WebDriver driver =null;
	
	
	// Initialize all the variables
	@BeforeSuite
	public static void initAll() throws IOException
	{
		if (driver==null)
		{
			//Load the config properties file
			FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"\\src\\dd_properties\\config.properties"));
			config.load(fis);
			
			//Load the object repository file
			fis = new FileInputStream((System.getProperty("user.dir")+"\\src\\dd_properties\\object.properties"));
			object.load(fis);
			
			// Load GymGo test data Excel File and provide location
			excelRead=new ExcelReadWrite((System.getProperty("user.dir")+"\\src\\dd_properties\\GymGoTestData.xlsx"));
			
			//Browser Check
			if (config.getProperty("browser").equals("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if (config.getProperty("browser").equals("ie"))
			{
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if (config.getProperty("browser").equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		driver.manage().timeouts().implicitlyWait(25L, TimeUnit.SECONDS);
		driver.get(config.getProperty("GymGoTestSite"));
		driver.manage().window().maximize();
		
	}//initAll
	
	
	@AfterSuite
	public static void QuitDriver() throws InterruptedException
	{
		Thread.sleep(10);
		//driver.quit();
	}
	
	
}
