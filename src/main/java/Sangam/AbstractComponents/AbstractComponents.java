package Sangam.AbstractComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Sangam.PageObject.CartPage;
import Sangam.PageObject.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver)
	{
		this.driver=driver;
		
	}

	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderButton;
	
	
	public void waitForElementToAppear(By FindBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}

	public void waitForwebElementToAppear(WebElement Ele) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Ele));
	}
	
	public void waitForElementDisappear(WebElement msg) throws InterruptedException 
	{
		Thread.sleep(1000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(msg));
	}
	
	public void clickWhenReady(WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    wait.until(ExpectedConditions.visibilityOf(element));

	    ((JavascriptExecutor) driver).executeScript(
	        "arguments[0].scrollIntoView({block: 'center'});", element);

	    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public CartPage goToCartPage() 
	{
		cartButton.click();
		return new CartPage(driver);
	
	}
	
	public OrderPage goToOrderPage() 
	{
		orderButton.click();
		return new OrderPage(driver);
	
	}
	
	
	
	
}
