package celebrationAcademy;

import testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import celebrationAcademy.pageObjects.CartPage;
import celebrationAcademy.pageObjects.ProductCatalogue;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=testComponents.Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("celebration@gmail.com", "123qweASsD");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() 
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("celebration@gmail.com", "123qweASD");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}
	
	//to verify zara coat 3 is displaying in orders page
	
	

}
