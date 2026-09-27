package Sangam.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Sangam.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;
	
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> CartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checOutButton;
	
	
	

	public List<WebElement> getCartProducts() 
	{
		return CartProducts;
		
	}
	
	public boolean verifyCartProducts(String productName) 
	{
		
		boolean match = getCartProducts().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goTocheckOut() 
	{
		checOutButton.click();
		return new CheckOutPage(driver);
	}
	
	
	
	
}
