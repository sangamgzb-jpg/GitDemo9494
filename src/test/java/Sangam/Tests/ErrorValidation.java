package Sangam.Tests;

import Sangam.TestComponents.Retry;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Sangam.PageObject.CartPage;
import Sangam.PageObject.CheckOutPage;
import Sangam.PageObject.ConfirmationPage;
import Sangam.PageObject.ProductCatalogue;
import Sangam.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"errorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		
	    landingPage.loginApplication("shaggy@gmail.com", "@Shaggy@1234");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMsg());
		
	
	}
	
	@Test
	public void orderErrorValidation() throws IOException, InterruptedException {

		String productName = "ADIDAS ORIGINAL";
		ProductCatalogue productCatalogue=landingPage.loginApplication("singh01@gmail.com", "Shaggy@12345");
		
		
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		boolean match=cartPage.verifyCartProducts("ADIDAS");
		Assert.assertFalse(match);
		
	
	
	
	}	
}
