package Project;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class homePage {
	public WebDriver driver;
	private By logo = By.cssSelector("[class*='greenLogo']");
	private By colour = By.cssSelector("[class*='greenLogo'] span");
	private By search = By.cssSelector("[placeholder='Search for Vegetables and Fruits']");
	private By items = By.xpath("//*[@class='products-wrapper']/div/div/h4");
	private By itemsprice = By.cssSelector("p[class='product-price']");
	private By message = By.cssSelector("//div[@class='no-results']/h2");
	private By freeAccess = By.xpath("//a[contains(text(),'Free Access')]");
	private By flightBooking = By.xpath("//a[contains(text(),'Flight Booking')]");
	private By increament = By.cssSelector("a.increment");
	private By decreament = By.cssSelector("a.decrement");
	private By quant = By.cssSelector("input.quantity");
	private By addtocartB = By.cssSelector("[class='product-action'] button");
	private By cartB= By.cssSelector("[alt='Cart']");
	private By itemsincart = By.cssSelector("[class='cart-preview active'] [class='product-name']");
	private By itemsincartprice = By.cssSelector("[class='cart-preview active'] [class='product-price']");
	private By itemsincartquant = By.cssSelector("[class='cart-preview active'] [class='quantity']");
	private By itemsincartamount = By.cssSelector("[class='cart-preview active'] [class='amount']");
	private By totalprice = By.xpath("//*[@class='cart-info']/table/tbody/tr[2]/td[3]");
	private By proceed2checkB = By.xpath("//*[contains(text(),'PROCEED TO CHECKOUT')]");
		public homePage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver = driver;
		}
		public WebElement getLogo()
		{
			return driver.findElement(logo);
		}
		public WebElement getLogocolour()
		{
			return driver.findElement(colour);
		}
		public WebElement getSearch()
		{
			return driver.findElement(search);
		}
		public List<WebElement> getItems()
		{
			return driver.findElements(items);
		}
		public List<WebElement> getItemsPrice()
		{
			return driver.findElements(itemsprice);
		}
		public WebElement getmessage()
		{
			return driver.findElement(message);
		}
		public WebElement getFreeAccess()
		{
			return driver.findElement(freeAccess);
		}
		public WebElement getFlightBooking()
		{
			return driver.findElement(flightBooking);
		}
		public List<WebElement> getIncrementButton()
		{
			return driver.findElements(increament);
		}
		public List<WebElement> getDecrementButton()
		{
			return driver.findElements(decreament);
		}
		public List<WebElement> getQuantityValue()
		{
			return driver.findElements(quant);
		}
		public List<WebElement> geta2cButton() {
			
			return driver.findElements(addtocartB);
		}
		public WebElement getCart()
		{
			return driver.findElement(cartB);
		}
		public List<WebElement> getCartItemsName()
		{
			return driver.findElements(itemsincart);
		}
		public List<WebElement> getCartItemsPrice()
		{
			return driver.findElements(itemsincartprice);
		}
		public List<WebElement> getCartItemsQuantity()
		{
			return driver.findElements(itemsincartquant);
		}
		public List<WebElement> getCartItemsAmount()
		{
			return driver.findElements(itemsincartamount);
		}
		public WebElement getTotalPrice()
		{
			return driver.findElement(totalprice);
		}
		public WebElement getProceedToCheckoutButton()
		{
			return driver.findElement(proceed2checkB);
		}
}
