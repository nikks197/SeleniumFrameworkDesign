package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AsbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	 
		// TODO Auto-generated method stub
		 WebDriver driver;
		 
		 public LandingPage(WebDriver driver)
		 {    
			 super(driver);
			 //initialization
			 this.driver = driver;
			 PageFactory.initElements(driver, this);
		 }
		
	
		//driver.findElement(By.id("userEmail")).sendKeys("nikku123@gmail.com");
	//	driver.findElement(By.id("userPassword")).sendKeys("Abcd@1234");
		//driver.findElement(By.id("login")).click();
         
		 // WebElement userEmail = driver.findElement(By.id("userEmail")); 
		 //PageFactory
		 
		 @FindBy(id="userEmail")
		 WebElement userEmail;
		 
		 @FindBy(id="userPassword")
		 WebElement PasswordEle;
		 
		 @FindBy(id="login")
		 WebElement Submit;
		 
		 @FindBy(css="[class*='flyInOut']")
		 WebElement errorMessage;
		 
		 
		 public ProductCatalogue loginApplication( String email, String password)  // method created for actions on above elements.
		 {   
			 userEmail.sendKeys(email);
			 PasswordEle.sendKeys(password);
			 Submit.click();
			 
			 ProductCatalogue productCatalogue = new ProductCatalogue (driver);
			 return productCatalogue;
			 
		 }
		 
		 public String getErrorMessage()
		 {
			 waitForWebelementToAppear(errorMessage);
			 errorMessage.getText();
			 return errorMessage.getText();
			 
		 }
		 
		 public void goTo()
		 {
			 driver.get("https://rahulshettyacademy.com/client");
				
		 }
		 
	}


