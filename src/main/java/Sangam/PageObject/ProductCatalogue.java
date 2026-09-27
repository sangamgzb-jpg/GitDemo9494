package Sangam.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Sangam.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By prod=By.cssSelector(".mb-3");
	By addTocart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList() 
	{
		waitForElementToAppear(prod);
		return products;
	}
	
	public WebElement getProductByName(String productName) 
	{
		WebElement reqProducts = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		return reqProducts;
	}
	
	public void addProductToCart(String productName) throws InterruptedException 
	{
		WebElement reqProducts=getProductByName(productName);
		reqProducts.findElement(addTocart).click();
		waitForElementToAppear(toastMessage);
		waitForElementDisappear(spinner);
		
		
	}
	
	
	
}
