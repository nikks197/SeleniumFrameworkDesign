package RahulShettyAcademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTests;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.ProductCatalogue;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTests {

     @Test (groups = {"ErrorHandling"})
     public void loginErrorValidation() throws IOException, InterruptedException
     {
	
		String productName = "ZARA COAT 3";
		

		ProductCatalogue productCatalogue = landingPage.loginApplication("nikku123@gmail.com", "Abcd1234");
       // password is wrong
		landingPage.getErrorMessage();
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
		//Assert.assertTrue(landingPage.getErrorMessage().equalsIgnoreCase("Incorrect email or Password."));
     }
     
     @Test
     public void productErrorValidation() throws IOException, InterruptedException
     {    	 String productName = "ZARA COAT 3";
		
		

		ProductCatalogue productCatalogue = landingPage.loginApplication("nikku1234@gmail.com", "Abcd@1234");

		
		List<WebElement> products = productCatalogue.getProductList();

		productCatalogue.getProductByName(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		
		Boolean match = cartPage.verifyProductDisplay("ZARA Coat 33");
		Assert.assertFalse(match);
     }
}  

	
