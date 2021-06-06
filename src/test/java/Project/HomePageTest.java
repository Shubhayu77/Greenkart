package Project;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.base;



public class HomePageTest extends base {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(base.class.getName());
	private homePage hp;
	private String selectLinkOpeninNewTab = Keys.chord(Keys.SHIFT,Keys.CONTROL,Keys.ENTER);
	private List<WebElement> items ;
	private List<WebElement> itemsPrice; 
	private List<WebElement> increament; 
	private List<WebElement> decreament;
	private List<WebElement> quant;
	private List<WebElement> addtocartButton; 
	private int itemsSize;
	private String childUrl;
	private Object itemdetails[][] ;
	private List<WebElement> itemsincart ;
	private List<WebElement> itemsincartprice; 
	private List<WebElement> itemsincartquant;
	private List<WebElement> itemsincartamount;
	@BeforeTest
	public void intialization() throws IOException
	{
		driver = intializedriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		log.info("Successfully opened Greenkart for Test1.");
	}
	
	
	@Test
	public void testcase1()
	{
		hp= new homePage(driver);
		String testValue=hp.getLogocolour().getCssValue("color");
		String expectedValue="rgba(255, 165, 0, 1)";
		if(hp.getLogo().getText().equalsIgnoreCase("GREENKART") && expectedValue.equalsIgnoreCase(testValue))
		{
			Assert.assertTrue(true);
			log.info("Logo match");
		}
		else
		{
			log.error("Logo does not match");
			Assert.assertTrue(false);	
		}
	}
	
	
	@Test
	public void testcase2() throws InterruptedException 
	{
		//String searchname =System.getProperty("Search By");
		String searchname = "ca";
		int noofitems =0;
		String searchname1 = searchname.toLowerCase();
		int size1 = searchname.length();
		hp.getSearch().sendKeys(searchname);
		Thread.sleep(3000);
		items = hp.getItems();
		itemsSize= items.size();
			for(int i=0;i<items.size();i++)
			{
				String[] names=items.get(i).getText().split("-");
				String name= names[0].trim().toLowerCase();
				for(int j=0;j<size1;j++)
				{
					char c= searchname1.charAt(j);
					CharSequence s1 = Character.toString(c);
					if(name.contains(s1))
					{
						noofitems++;
					}
				}
			}
			if(searchname.length()*itemsSize == noofitems)
			{
				log.info("Search option is working properly");
				hp.getSearch().sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
			}
			else if(hp.getmessage().getText().contains("Sorry, no products matched your search!"))
			{	
				log.info("Search option is working properly with irregular input");
				hp.getSearch().sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
			}
			else
			{
				log.error("Search option is not working properly");
				Assert.assertTrue(false);	
			}
		}
	
	
	
	@Test(dataProvider="getDatabase")
	public void testcase3(String itemName,String itemQuantity,String itemPrice) throws InterruptedException
	{
		items = hp.getItems();
		itemsSize= items.size();
		itemsPrice = hp.getItemsPrice();
		itemdetails = new Object[itemsSize][3];
		for(int i=0;i<itemsSize;i++)
		{
			if(items.get(i).getText().split("-")[0].trim().isBlank())
			{
				itemdetails[i][0]="No name is provided";
			}
			else
			{
				itemdetails[i][0]=items.get(i).getText().split("-")[0].trim();	
				if(!items.get(i).getText().contains("Kg"))
				{
					itemdetails[i][1]="quantity is not provided";
				}
				else
				{
					itemdetails[i][1]=items.get(i).getText().split("-")[1].trim();	
				}
				
				if(itemsPrice.get(i).getText().isBlank())
				{
					itemdetails[i][2]="Price is not provided";
				}
				else
				{
					itemdetails[i][2]=itemsPrice.get(i).getText();
				}
			}
		}
		for(int j=0;j<itemsSize;j++)
		{
			if(itemName.equals(itemdetails[j][0]))
				{
					if(!itemQuantity.equals(itemdetails[j][1]))
					{
						log.error(itemName+" "+itemdetails[j][1]+". Check and Update");
					}
					if(!itemPrice.equals(itemdetails[j][2]))
					{
						log.error(itemName+" has different price. Check and Update");
					}
					break;
				}
		}
					
}
	
	@Test
	public void testcase4()
	{	
		items = hp.getItems();
		itemsSize= items.size();
		increament = hp.getIncrementButton();
		decreament = hp.getDecrementButton();
		quant = hp.getQuantityValue();
		addtocartButton = hp.geta2cButton();
		itemdetails = new Object[itemsSize][3];
		for(int i=0;i<itemsSize;i++)
		{
			itemdetails[i][0]=items.get(i).getText().split("-")[0].trim();	
			for(int x=1;x<11;x++)
			{
				int value=Integer.parseInt(quant.get(i).getAttribute("value"));
				if(x==value)
				{
					increament.get(i).click();
				}
				else
				{
					System.out.println(itemdetails[i][0]+" quantity increament is not changing accordingly");
				}	
			}
			addtocartButton.get(i).click();
			if(addtocartButton.get(i).getText().contains("ADDED"))
			{
				Assert.assertTrue(true);
			}
			else
			{
				System.out.println("Add to Cart button of" + itemdetails[i][0] + "is not working");
			}
			for(int x=11;x>1;x--)
			{
				itemdetails[i][0]=items.get(i).getText().split("-")[0].trim();
				int value=Integer.parseInt(quant.get(i).getAttribute("value"));
				if(x==value)
				{
					decreament.get(i).click();
				}
				else
				{
					System.out.println(itemdetails[i][0]+" quantity decreament is not changing accordingly");
				}		
			}
		}
	}
			
	
		
	
	

	@Test
	public void testcase5()
	{
		items = hp.getItems();
		itemsSize= items.size();
		itemsPrice = hp.getItemsPrice();
		hp.getCart().click();
		itemsincart = hp.getCartItemsName();
		itemsincartprice = hp.getCartItemsPrice();
		itemsincartquant = hp.getCartItemsQuantity();
		itemsincartamount = hp.getCartItemsAmount();
		Object itemsincartdetails[][] = new Object[itemsSize][4];
		int expamount=1;
		int sum=0;
		for(int i=0;i<itemsSize;i++)	
		{
			if(itemsincart.get(i).getText().isBlank())
			{
				((JavascriptExecutor) driver).executeScript(
			            "arguments[0].scrollIntoView();", itemsincart.get(i));
			}
			itemsincartdetails[i][0]=itemsincart.get(i).getText().split("-")[0].trim();
			itemsincartdetails[i][1]=itemsincartprice.get(i).getText();
			itemsincartdetails[i][2]=itemsincartquant.get(i).getText().split(" ")[0].trim();
			itemsincartdetails[i][3]=itemsincartamount.get(i).getText();
			int amount = Integer.parseInt(itemsincartdetails[i][3].toString());
			expamount = Integer.parseInt(itemsincartdetails[i][1].toString()) * Integer.parseInt(itemsincartdetails[i][2].toString());
			if(amount == expamount )
			{
				sum = sum + expamount;
			}
			else
			{
				System.out.println();
			}
		}
		String totalprice = hp.getTotalPrice().getText();
		if(sum == Integer.parseInt(totalprice))
		{
			log.info("Cart is working properly");
		}
		else
		{
			log.error("");
		}
	}
	
	
	@Test
	public void testcase6()
	{
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 hp.getProceedToCheckoutButton().click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Place Order')]")));
		 String currentURL=driver.getCurrentUrl();
		if(currentURL.equalsIgnoreCase("https://rahulshettyacademy.com/seleniumPractise/#/cart"))
		{
			
			log.info("Proceed to Checkout button is working properly");
		}
		else
		{
			log.info("Proceed to Checkout button is not working properly");
		}
		driver.navigate().back();
	}
	
	

	@Test
	public void testcase7()
	{
		hp.getFreeAccess().sendKeys(selectLinkOpeninNewTab);
		childUrl = getWindows();
		if(childUrl.equalsIgnoreCase("https://rahulshettyacademy.com/#/documents-request"))
		{
			
			log.info("Free Access Link is working properly");
		}
		else
		{
			log.info("Free Access Link is not working properly");
		}
	}
	
	@Test
	public void testcase8()
	{
		hp.getFlightBooking().sendKeys(selectLinkOpeninNewTab);
		childUrl = getWindows();
		if(childUrl.equalsIgnoreCase("https://rahulshettyacademy.com/dropdownsPractise/"))
		{
			
			log.info("Flight Booking Link is working properly");
		}
		else
		{
			log.info("Flight Booking Link is not working properly");
		}
	}
	
	
		@DataProvider
		public Object[][] getDatabase() throws SQLException
		{
			String host ="localhost";
			String port ="3307";
			Connection con =DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/greenkart", "root", "root123");
			Statement s=  con.createStatement();
			ResultSet rs= s.executeQuery("select * from Credentials");
			Object a[][] = new Object[30][3];
		    int row=0;
			while(rs.next())
		    {
			a[row][0]=rs.getString("itemName");
			a[row][1]=rs.getString("itemQuantity");
			a[row][2]=rs.getString("itemAmount");
			row++;
		    }
			return a;
		}

	@AfterTest
	public void close()
	{
		driver.close();	
		log.info("Successfully closed Greenkart for Test1.");
	}
}
