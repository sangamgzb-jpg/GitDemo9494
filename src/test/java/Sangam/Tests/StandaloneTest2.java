package Sangam.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Sangam.PageObject.CartPage;
import Sangam.PageObject.CheckOutPage;
import Sangam.PageObject.ConfirmationPage;
import Sangam.PageObject.LandingPage;
import Sangam.PageObject.OrderPage;
import Sangam.PageObject.ProductCatalogue;
import Sangam.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest2 extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void StandAlone(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("pass"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		boolean match = cartPage.verifyCartProducts(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goTocheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmmsg = confirmationPage.getConfirmationMsg();
		Assert.assertEquals(confirmmsg, "THANKYOU FOR THE ORDER.");

	}

	@Test(dependsOnMethods = { "StandAlone" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("shaggy@gmail.com", "Shaggy@1234");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderdProducts(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
	

		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Sangam\\Data\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}
	
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][]{{"shaggy@gmail.com","Shaggy@1234"},{"singh01@gmail.com","Shaggy@12345","ZARA COAT 3"}};
//	}
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData() throws IOException {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "shaggy@gmail.com");
//		map.put("pass", "Shaggy@1234");
//		map.put("product", ,"ADIDAS ORIGINAL");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "singh01@gmail.com");
//		map1.put("pass", "Shaggy@12345");
//		map1.put("product", "ZARA COAT 3");

	
	
	
	
	

}
