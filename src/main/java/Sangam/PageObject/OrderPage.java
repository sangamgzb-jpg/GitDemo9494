package Sangam.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Sangam.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderdProducts;
	
	
	
	
	

	public List<WebElement> getOrderdProducts() 
	{
		return OrderdProducts;
		
	}
	
	public boolean verifyOrderdProducts(String productName) 
	{
		
		boolean match = getOrderdProducts().stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
}
