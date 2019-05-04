package Cart;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;
import com.sun.glass.events.KeyEvent;
import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@SuppressWarnings("restriction")
public class Add_Product {

	static WebDriver driver;
	String Product_Details;
	
	@BeforeTest
	public void launch_browser()
	{
		System.setProperty("webdriver.chrome.driver","D:\\Himanshu_458769\\Himanshu_Sharma_458769_01\\Java_Selenium\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//Opens Home page
		driver.get("https://us.asos.com");
		
		//Maximizes the window
		driver.manage().window().maximize();  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void Select_Add_Product() throws AWTException, InterruptedException
	{
		//Clicks on Button Men
		driver.findElement(By.xpath("//*[@id=\"chrome-sticky-header\"]/div[1]/div/ul[1]/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		//Navigates to Clothing > Jeans
		driver.findElement(By.xpath("//*[@id=\"chrome-sticky-header\"]/div[2]/div[2]/nav/div/div[1]/button[2]/span/span")).click();
		driver.findElement(By.xpath("//*[@id=\"chrome-sticky-header\"]/div[2]/div[2]/nav/div/div[3]/div/div[2]/div/section[1]/div[2]/ul[1]/li[5]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//Selects a Product
		driver.findElement(By.xpath("//*[@id=\"product-11469606\"]/a/div[1]/img")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
		//Selects Product Size
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"product-size\"]/section/div/div[1]/div[2]/select")));
		dropdown.selectByVisibleText("W40 L30");
		driver.findElement(By.xpath("//*[@id=\"product-colour\"]/section/div/div/span")).click();
		
		//Using Robot Class to automate Page Down key for Scrolling down
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(2000);
		
		// Adds Product to the Shopping Cart
		driver.findElement(By.className("add-button")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"miniBagDropdown\"]/a")).click();
		Thread.sleep(2000);
				
	}
	
	@Test
	public void View_Cart()
	{
		//Opens Shopping Cart
		driver.findElement(By.xpath("//*[@id=\"minibag-dropdown\"]/div/div/div[3]/div/div[3]/div[1]/a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
		//Checking if product added to Shopping Cart
		WebElement cart_Item = driver.findElement(By.xpath("//*[@id=\"bagApp\"]/div/div/div[1]/div[1]/bag-item-list/ul/li/div/div/div/bag-item-product/div/div/p[1]/a"));
		Product_Details = cart_Item.getText(); 
				
		if(cart_Item.equals(null))
		{
			System.out.println("Product not aadded to the Shopping Cart");
				}
		else
		{
			System.out.println("Product added to The Cart with Product Details : " + Product_Details);
		}
	}
	
	@AfterTest
	public void Close_Browser()
	{
		driver.close();
	}
}
	