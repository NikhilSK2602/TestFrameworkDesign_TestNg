package website.website;

import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObject.Homepage;
import pageObject.ShopContentPanel;
import pageObject.ShopHomepage;
import pageObject.ShopProductPage;
import pageObject.ShoppingCart;

import base.BasePage;
@Listeners(resources.Listeners.class)
public class AddRemoveItems extends Hooks {

	public AddRemoveItems() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Test
	public void addRemoveItem() throws IOException  {
		ExtentManager.log("Starting AddRemoveCartItems...");
		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();
		//handles cookie prompt
		home.getCookie().click();
		
		home.getTestStoreLink().click();

		// creating an object of the test store homepage
		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("ECommerce HomePage is displayed");
		shopHome.getProdOne().click();

		// creating an object of the shop products page (when a product has been
		// selected)
		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("ECommerce ProductPage is displayed");
		Select option = new Select(shopProd.getSizeOption());
		ExtentManager.pass("Product size is selected successfully");
		option.selectByVisibleText("M");
		ExtentManager.pass("Product size is selected successfully");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Product Quantity is increased successfully");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Product Quantity is decreased successfully");

		// creating an object of the cart content panel (once an item was added)
		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		ExtentManager.pass("User is able to click on Continue Shopping");
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		ExtentManager.pass("User is able to add multiple products into cart");
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		ExtentManager.pass("User is able to Checkout successfully");

		// creating an object for the shopping cart page and deleting item 2
		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		ExtentManager.pass("User is able to delete procts from cart");

		// using a wait to ensure the deletion has taken place
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		//wait.until(ExpectedConditions.invisibilityOf(cart.getDeleteItemTwo()));
		waitForElementInvisible(cart.getDeleteItemTwo(),Duration.ofSeconds(10));

		// printing the total amount to console
		System.out.println(cart.getTotalAmount().getText());
		
		// using an assertion to make sure the total amount is what we are expecting
		try {
			// using an assertion to make sure the total amount is what we are expecting
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
			ExtentManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found" + cart.getTotalAmount().getText() +
					" expected $45.23");
			ExtentManager.fail("The total amount did not match the expected amount.");
		}

	}
}