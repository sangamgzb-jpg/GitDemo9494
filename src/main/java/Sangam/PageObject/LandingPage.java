package Sangam.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sangam.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.id("userPassword")).sendKeys("Shaggy@1234");
	//driver.findElement(By.id("login")).click();
	
	@FindBy(id="userEmail")
	WebElement Email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMsg;
	
	// div[aria-label='Incorrect email or password.']
			//div[@class='ng-tns-c4-7 toast-message ng-star-inserted']
	
	public String getErrorMsg() 
	{
		waitForwebElementToAppear(errorMsg);
		errorMsg.getText();
		return errorMsg.getText();
	}
	
	
	public ProductCatalogue loginApplication(String email,String pass) 
	{
		Email.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		return new ProductCatalogue(driver);
		
		
	}
	
	public void goTo() 
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
	
	
	
	
	
}
