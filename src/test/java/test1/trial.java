package test1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class trial {
	
	private static WebDriver driver;

	public static void main(String[] args) {

	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	driver.manage().window().maximize();

	radio();
	check();
	staticDropDown();
//	dynamicDropDown();
	alerts();
	alerts2();
	multipleWindows();
	multipleTabs();
	visibility();
    //tearDown();
	webTable2();
	webTable();
	waiting();
	mouseHover();

	refresh();
	scroll();
	}

	public static void radio()
	{
	driver.findElement(By.xpath("//input[@value='radio2']")).click();
	driver.findElement(By.xpath("//input[@value='radio1']")).click();
	driver.findElement(By.xpath("//input[@value='radio3']")).click();
	driver.findElement(By.xpath("//input[@value='radio1']")).click();
	}
	
	public static void check()
	{
	driver.findElement(By.xpath("//input[@value='option1']")).click();
	driver.findElement(By.xpath("//input[@value='option2']")).click();
	driver.findElement(By.xpath("//input[@value='option3']")).click();
	driver.findElement(By.xpath("//input[@value='option2']")).click();
	}

	public static void staticDropDown()
	{
	Select sttic = new Select(driver.findElement(By.xpath("//select[@id='dropdown-class-example']")));
	sttic.selectByIndex(1);
	sttic.selectByIndex(2);
	sttic.selectByIndex(3);
	}

	public static void dynamicDropDown()
	{
	driver.findElement(By.xpath("//*[@id='autocomplete']")).sendKeys("Ind");
	driver.findElement(By.xpath("//div[text()='India' and @class='ui-menu-item-wrapper']")).click();
	}
	
	public static void alerts()
	{
		driver.findElement(By.xpath("//*[@id= 'alertbtn']")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
	}
	
	public static void alerts2()
	{
		driver.findElement(By.xpath("//*[@id= 'confirmbtn']")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

	public static void alerts3()
	{
		//This method clicks on the 'OK' button of the alert box.
		driver.switchTo( ).alert( ).accept();
		
		//We use this method when the 'Cancel' button clicks in the alert box.
		driver.switchTo( ).alert( ).dismiss();
		
		//This method captures the message from the alert box.
		driver.switchTo().alert().getText();
		
		//This method sends data to the alert box.
		driver.switchTo().alert().sendKeys("Text");
	}
	
	public static void multipleWindows()
	{
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id= 'openwindow']")).click();
		Set<String> windows = driver.getWindowHandles();
		
		Iterator<String> i1 = windows.iterator();
		
		while(i1.hasNext())
		{
			String childWindow = i1.next();
			if(!mainWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		
		driver.switchTo().window(mainWindow);
	}
	
	public static void multipleTabs()
	{
		String mainWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//*[@id= 'opentab']")).click();
		Set<String> windows = driver.getWindowHandles();
        Iterator<String> i1 = windows.iterator();
		
		while(i1.hasNext())
		{
			String childWindow = i1.next();
			if(!mainWindow.equalsIgnoreCase(childWindow))
			{
				driver.switchTo().window(childWindow);
				driver.close();
			}
		}
		
		driver.switchTo().window(mainWindow);
	}

	public static void visibility()
	{
		System.out.println(driver.findElement(By.xpath("//*[@id= 'displayed-text']")).isDisplayed());
		driver.findElement(By.xpath("//*[@id= 'hide-textbox']")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id= 'displayed-text']")).isDisplayed());
		driver.findElement(By.xpath("//*[@id= 'show-textbox']")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id= 'displayed-text']")).isDisplayed());
	}

	public static void webTable()
	{
		int sum=0;
		
		List<WebElement> courseCost = driver.findElements(By.xpath("//*[text()='Rahul Shetty']/following::td[2]"));
		
		for(int i=0;i<courseCost.size();i++)
		{
		//	System.out.println(courseCost.get(i));
			System.out.println(courseCost.get(i).getText());
			sum=sum+Integer.parseInt(courseCost.get(i).getText());
		}
		System.out.println("Total Sum "+sum);
	}

	public static void webTable2()
	{
         int sum2=0;
		
         List<WebElement> sal = driver.findElements(By.xpath("//*[text()='Amount']/following::td"));
         
         for(int i=0;i<37;i++)
 		{
        
		if(i%4==3)
		{
			System.out.println(sal.get(i).getText());
			sum2=sum2+Integer.parseInt(sal.get(i).getText());
		}
        }
         
         System.out.println("Total Sum "+sum2); 
	}

	public static void waiting()
	{
		//pageLoad
		driver.manage().timeouts().pageLoadTimeout(16, TimeUnit.SECONDS);
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS);
		
		
		
		//fluent wait
		FluentWait wait2 = new FluentWait(driver)
					.ignoring(NoSuchElementException.class);
		
		//explicit wait
		wait2.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("locator"))));
		
		//setScriptTimeout
		driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	}

	public static void mouseHover()
	{
		driver.get("https://demoqa.com/elements");
		
		driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
		
		Actions actions = new Actions(driver);
	//	actions.moveToElement(driver.findElement(By.xpath("//a[@href='https://demoqa.com']"))).click().perform();
		actions.moveToElement(driver.findElement(By.xpath("(//*[@stroke='currentColor' and @viewBox=\"0 0 24 24\" and @xmlns='http://www.w3.org/2000/svg'])[1]"))).click().perform();
	}

	public static void refresh()
	{
		driver.navigate().to(driver.getCurrentUrl());
		System.out.println("1");
		driver.navigate().refresh();
		System.out.println("2");
		driver.get(driver.getCurrentUrl());
		System.out.println("3");
		driver.findElement(By.xpath("//*[@id='name']")).sendKeys(Keys.F5);
		System.out.println("4");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("history.go(0)");
		System.out.println("5");
	}
	
	public static void scroll()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,900)");
		js.executeScript("window.scrollBy(0,-700)");
	}
}


