package Sangam.PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Sangam.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {

	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}


	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-item:nth-child(3)")
	WebElement select;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By ele = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) 
	{
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(ele);
		select.click();
		
	}
	
	public ConfirmationPage submitOrder() 
	{
		
		    clickWhenReady(submit);
		    return new ConfirmationPage(driver);
		

		    
	}
	
	
	
	
	
}
