package pageObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class base {
	//Initializing driver
			public WebDriver driver;
			public WebDriver intializedriver() throws IOException 
			{
			//String browsername =System.getProperty("browser");// for parameterizing from jenkins
			String browsername = "chrome";
			if(browsername.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "E:\\sel\\chromedriver.exe");
				ChromeOptions options= new ChromeOptions();
				if(browsername.contains("headless"))
				options.addArguments("headless");
				driver = new ChromeDriver(options);
			}
			else if(browsername.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\sel\\geckodriver.exe");
				FirefoxOptions options= new FirefoxOptions();
				if(browsername.contains("headless"))
				options.addArguments("headless");
				driver = new FirefoxDriver(options);
			}
			else if(browsername.equals("IE"))
			{
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\sel\\IEDriverServer.exe");
				InternetExplorerOptions options= new InternetExplorerOptions();
				if(browsername.contains("headless"))
				options.addCommandSwitches("headless");
				driver = new InternetExplorerDriver(options);
			}
			else if(browsername.equals("edge"))
			{
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\sel\\msedgedriver.exe");
				EdgeOptions options= new EdgeOptions();
				if(browsername.contains("headless"))
				options.setPageLoadStrategy("headless");
				driver = new EdgeDriver(options);
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			return driver;
			}
			public String getScreenshotPath(String testCaseName, WebDriver driver) throws IOException
			{
				TakesScreenshot st= (TakesScreenshot) driver;
				File source = st.getScreenshotAs(OutputType.FILE);
				String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
				FileUtils.copyFile(source, new File(destinationFile));
				return destinationFile;
			}
		
			public String getWindows()
			{
				Set<String> windows=driver.getWindowHandles();//[parentId,childId]
				Iterator<String> it = windows.iterator();
				String parentId = it.next();
				String childId = it.next();
				driver.switchTo().window(childId);
				String childurl = driver.getCurrentUrl();
				driver.close();
				driver.switchTo().window(parentId);
				return childurl;
			}

}
