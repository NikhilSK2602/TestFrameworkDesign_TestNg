package website.website;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.BasePage;
import base.ExtentManager;
import base.Hooks;
import pageObject.Homepage;
import pageObject.OrderFormDelivery;
import pageObject.OrderForPayment;
import pageObject.OrderFormPersInfo;
import pageObject.OrderFormShippingMethod;
import pageObject.ShopContentPanel;
import pageObject.ShopHomepage;
import pageObject.ShopProductPage;
import pageObject.ShoppingCart;
@Listeners(resources.Listeners.class)
public class OrderCompleteTest extends Hooks {

	public OrderCompleteTest() throws IOException {
		super();
	}



	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		ExtentManager.log("Starting EndToEndOrderPlace");
		// creating an object of the automationtesting.co.uk webpage
		Homepage home = new Homepage();

		//handles cookie prompt
		home.getCookie().click();
		
		home.getTestStoreLink().click();

		// creating an object of the test storehomepage
		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("ECommerce HomePage is displayed");
		shopHome.getProdOne().click();

		// creating an object of the shop products page (when a product has been selected)
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
		Thread.sleep(3000);
		cPanel.getCheckoutBtn().click();
		ExtentManager.pass("User is able to Checkout successfully");
		// creating an object of the shopping cart page (all items selected)
		ShoppingCart cart = new ShoppingCart();
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();

		// creating an object of the order personal information page
		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("Nikhil");
		pInfo.getLastnameField().sendKeys("SK");
		pInfo.getEmailField().sendKeys("nikhil@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		ExtentManager.pass("User is able to provide the mandatory fields");
		pInfo.getContinueBtn().click();

		// creating an object of the order delivery info page
		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("Brindavan");
		orderDelivery.getCityField().sendKeys("Hebbal");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("58965");
		ExtentManager.pass("User is able to enter the delivery address successfully");
		orderDelivery.getContinueBtn().click();

		// creating an object of the shipping method page
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("Please call me if i am not available");
		shipMethod.getContinueBtn().click();

		// creating an object of the payment options page
		OrderForPayment orderPay= new OrderForPayment();
	
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("User is able to place the order successfully");
	}

}
