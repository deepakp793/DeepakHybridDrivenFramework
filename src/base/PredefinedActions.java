package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PredefinedActions {
	protected static WebDriver driver;
	
	public static void start(String URL) {
		System.setProperty("WebDriver.chrome.driver", "D:\\Automation Class-TechnoCredit\\TechnoHybridFramework\\drivers\\chromedriver_106.exe");
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
	}
	
	public static void closeBrowser() {
		driver.close();
	}
}
