package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RahulShettyAcademy.AsbstractComponents.AbstractComponent;




public class ProductCatalogue extends AbstractComponent {

	
		// TODO Auto-generated method stub
		 WebDriver driver;
		 
		 public ProductCatalogue(WebDriver driver)
		 {    
			 super(driver);
			 //initialization
			 this.driver = driver;
			 PageFactory.initElements(driver, this);
		 }
		
	
		
		 //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		 @FindBy(css=".mb-3")
		 List<WebElement> products;
		 
		 By productsBy = By.cssSelector(".mb-3");
		 By addToCart = By.cssSelector(".card-body button:last-of-type");
		 By toastMessage = By.cssSelector("#toast-container");
		 By spinner = By.cssSelector(".ng-animating");
		 public List<WebElement> getProductList()
		 {
			 waitElementToAppear(productsBy);
			 return products;
			 
		 }
		 
		 public void getProductByName (String productName)
		 {
			 WebElement prod = products.stream()
						.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
						.orElse(null);
			 
				prod.findElement(addToCart).click(); 
				waitElementToAppear(toastMessage);
				
				waitElementToDisappear (spinner);
				
			 
		 }
		 
		 
		 
	}


