package RahulShettyAcademy.tests;
import java. util.*;
import java.sql.DriverAction;
import java. time.Duration;
import java. util.List;
import org.testng.Assert;

import RahulShettyAcademy.pageobjects.LandingPage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import dev.failsafe.internal.util.Assert;
//import org.testng.Assert;

import RahulShettyAcademy.pageobjects.LandingPage;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	
	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		// WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Hp\\Downloads\\chromedriver_win32\\"
				+ "chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingpage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("nikku123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abcd@1234");
		driver.findElement(By.id("login")).click();
		
		// now i want to add ZARA COAT 3 into carrt so first we will grab all items
		// showing on page then iterate over and select the desiired one.
		// so find a common element applicable for all products showing.

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// now will convert this list intp Streams that helps us to iterate over each
		// and every product
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating")));
        //or wait.until(ExpectedConditions.invisibilityOf(driver.FindElement(By.cssSelector("ng-animating"))));
	

		// String message = driver.findElement(By.cssSelector("#toast-container"))
		// Assert.assertEquals(message,"Product Added To Cart" );
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		//cartProducts.stream().filter(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		// here we donot want to capture webelement but to only check the desired
		// product is addded into cart or not so instead of using "filter" we will use
		// "AnyMatch" that returns boolean value.
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();
        
        Actions a = new Actions(driver);
       a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        //same thing we can do without Actions class by direct using driver.......sendkeys method
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        // cssSelector for 2nd option to select india is ".ta-item:nth-of-type(2)"
        //X-path for same is "//button[contains(@class,'ta-item')][2]"
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
       
       driver.findElement(By.xpath("//*[contains(text(),'Shipping Information')]")).click();



        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
        
        Thread.sleep(2000);
        WebElement placrOrder = driver.findElement(By.xpath("//a[text()='Place Order ']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", placrOrder);
	    
        
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        confirmMessage.equalsIgnoreCase("Thankyou for the order.");
        //Assert.assertEquals(confirmMessage, "ThankYou For The Order")
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
       
        
        //driver.close();
        
        
	}

}
