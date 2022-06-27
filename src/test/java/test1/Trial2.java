package test1;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Trial2 {
	
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException{

	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://demoqa.com/elements");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//*[@stroke='currentColor' and @viewBox='0 0 448 512']")).click();
//	forms();
//	windows();
//	alerts();
//	modals();
//	frames();
	nestedFrames();
	}
	
	public static void forms()
	{
    	driver.navigate().to("https://demoqa.com/automation-practice-form");
    	driver.findElement(By.id("firstName")).sendKeys("Muhammed");
    	driver.findElement(By.id("lastName")).sendKeys("Gori");
    	driver.findElement(By.id("userEmail")).sendKeys("Gori@laura.com");
    	driver.findElement(By.xpath("//*[@for='gender-radio-3' ]")).click();
    	driver.findElement(By.id("userNumber")).sendKeys("8899001122");
    	driver.findElement(By.id("dateOfBirthInput")).click();
    	Select Month = new Select(driver.findElement(By.className("react-datepicker__month-select")));
    	Month.selectByVisibleText("July");
    	Select Year = new Select(driver.findElement(By.className("react-datepicker__year-select")));
    	Year.selectByVisibleText("1989");
    	driver.findElement(By.xpath("//*[@aria-label='Choose Sunday, July 16th, 1989' ]")).click();
/*    	driver.findElement(By.xpath("//*[contains(@class,'is-multi css-1hwfws3') ]")).click();
    	driver.findElement(By.xpath("//*[contains(@class,'is-multi css-1hwfws3') ]")).click();
    	driver.findElement(By.xpath("//*[contains(@class,'is-multi css-1hwfws3') ]")).sendKeys("Eng");
    	driver.findElement(By.xpath("//*[contains(@class,'is-multi css-1hwfws3') ]")).sendKeys(Keys.TAB);
*/
    	driver.findElement(By.xpath("//*[@for='hobbies-checkbox-1' ]")).click();
    	driver.findElement(By.xpath("//*[@for='hobbies-checkbox-2' ]")).click();
    	driver.findElement(By.xpath("//*[@for='hobbies-checkbox-3' ]")).click();
	}

	public static void windows() throws InterruptedException
	{
		driver.navigate().to("https://demoqa.com/browser-windows");
		
		String mWindow = driver.getWindowHandle();
		driver.findElement(By.id("tabButton")).click();
		
		Thread.sleep(70);
		
		Set<String> windowList = driver.getWindowHandles();
		Iterator<String> i1 = windowList.iterator();
		
		while(i1.hasNext())
		{
			String cWindow = i1.next();
			if(!(mWindow.equalsIgnoreCase(cWindow)))
			{
				driver.switchTo().window(cWindow);
				driver.close();
			}
		}
		
		driver.switchTo().window(mWindow);
		
		
		
		
		Thread.sleep(70);
				
		driver.findElement(By.id("windowButton")).click();	
		
		Thread.sleep(70);
		
		Set<String> windowList2 = driver.getWindowHandles();
		Iterator<String> i2 = windowList2.iterator();
		
		while(i2.hasNext())
		{
			String cWindow2 = i2.next();
			if(!(mWindow.equalsIgnoreCase(cWindow2)))
			{
				Thread.sleep(70);
				driver.switchTo().window(cWindow2);
				Thread.sleep(70);
				driver.close();
			}
		}
		
		driver.switchTo().window(mWindow);
		
	}

	public static void alerts() throws InterruptedException
	{
	
		driver.navigate().to("https://demoqa.com/alerts");
     	driver.findElement(By.id("alertButton")).click();
		Thread.sleep(70);
		driver.switchTo().alert().accept();
		
		Thread.sleep(70);
		
		driver.findElement(By.id("confirmButton")).click();
		Thread.sleep(70);
		driver.switchTo().alert().dismiss();
		
		Thread.sleep(70);

		driver.findElement(By.id("promtButton")).click();
		Thread.sleep(70);
		driver.switchTo().alert().accept();
//		driver.switchTo().alert().sendKeys("Joy Maa Kaali");
//		driver.switchTo().alert().accept();
	}

	public static void modals() throws InterruptedException
	{
		driver.navigate().to("https://demoqa.com/modal-dialogs");
     	driver.findElement(By.id("showSmallModal")).click();
		Thread.sleep(70);
		System.out.println(driver.findElement(By.xpath("//div[@class='modal-body']")).getText());
		driver.findElement(By.id("closeSmallModal")).click();
	}

	public static void frames() throws InterruptedException
	{
		driver.navigate().to("https://demoqa.com/frames");
		driver.switchTo().frame("frame1");
		System.out.println(driver.findElement(By.id("sampleHeading")).getText());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
		System.out.println(driver.findElement(By.id("sampleHeading")).getText());
	}

	public static void nestedFrames()
	{
		driver.navigate().to("https://demoqa.com/nestedframes");
		driver.switchTo().frame("frame1");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@srcdoc,'Child Iframe')]")));
	}
	}
