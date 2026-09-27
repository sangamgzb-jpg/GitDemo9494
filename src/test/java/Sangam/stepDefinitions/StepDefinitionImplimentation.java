package Sangam.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Sangam.PageObject.CartPage;
import Sangam.PageObject.CheckOutPage;
import Sangam.PageObject.ConfirmationPage;
import Sangam.PageObject.LandingPage;
import Sangam.PageObject.ProductCatalogue;
import Sangam.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplimentation extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	
	
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_Page() throws IOException 
	{
		//code
		landingPage=launchApplication();
	}
	
	@Given ("^logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String name,String pass) 
	{
		
		productCatalogue = landingPage.loginApplication(name, pass);
	}
	
	@When ("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName) 
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyCartProducts(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goTocheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then ("{string} message is displayed on confimationPage")
	public void message_displayed_on_confimationPage(String string) 
	{
		String confirmmsg = confirmationPage.getConfirmationMsg();
		Assert.assertEquals(confirmmsg, string);
		driver.close();
		
	}
	
	@Then ("{string} message is displayed")
	public void error_message_displayed_on_confimationPage(String string) 
	{
		
		Assert.assertEquals(string,landingPage.getErrorMsg());
		driver.close();
		
	}
	
}
