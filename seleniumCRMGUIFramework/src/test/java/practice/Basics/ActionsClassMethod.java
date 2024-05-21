package practice.Basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class ActionsClassMethod {

	@Test
	public void rightClickOnPage() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.flipkart.com");
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	@Test
	public void scroll() throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.zomato.com/india");
		
		Actions act = new Actions(driver);
		for(int i = 0; i<=5;i++) {
			act.scrollByAmount(0, 100).perform();
		}
		Thread.sleep(2000);
		driver.quit();		
		
	}
}
