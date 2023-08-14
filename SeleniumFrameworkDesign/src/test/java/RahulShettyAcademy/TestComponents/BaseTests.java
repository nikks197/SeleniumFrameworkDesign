package RahulShettyAcademy.TestComponents;

import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import RahulShettyAcademy.pageobjects.LandingPage;

public class BaseTests {
	
	private static final boolean True = false;
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver () throws IOException
	{   
		// Properties Class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream (System.getProperty("user.dir")+"//src//main//java//RahulShettyAcademy//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		
		 //WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Hp\\Downloads\\chromedriver_win32\\"
				+ "chromedriver.exe");
		 driver = new ChromeDriver();
		

		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//Firefox
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			//Edge
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\Hp\\Downloads\\chromedriver_win32\\"
					+ "edge.exe");
			 driver = new EdgeDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}
	
	@BeforeMethod (alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		// to wright any no of test cases i have to hit this landing page and hit the url first that is why it is in base test
		driver = initializeDriver();
	    landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod  (alwaysRun=true)
	public void tearDown()
	{   
		driver.close();
	
	}
	
	/*@AfterTest
	public void tearDown() {
	    if (driver == null) {
	        driver.quit();
	    }
	}*/

}
