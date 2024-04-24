package celebrationAcademy;

import testComponents.BaseTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import celebrationAcademy.pageObjects.CartPage;
import celebrationAcademy.pageObjects.CheckoutPage;
import celebrationAcademy.pageObjects.ConfirmationPage;
import celebrationAcademy.pageObjects.OrderPage;
import celebrationAcademy.pageObjects.ProductCatalogue;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException
	{

		//login
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		
		//click product
		productCatalogue.addProductToCart(input.get("product"));
		
		//click cart
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Cart
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		
		//Select Country
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		//Get success message
		confirmationPage.getConfirmationMessage();
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("celebration@gmail.com", "123qweASD");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "celebration@gmail.com");
//	map.put("password", "123qweASD");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "celebration1@gmail.com");
//	map1.put("password", "123qweASD");
//	map1.put("product", "ADIDAS ORIGINAL");

}
