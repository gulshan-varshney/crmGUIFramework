package practice.DataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class sampleDataDrivenTesting {

	public static void main(String[] args) throws IOException, InterruptedException {
		Random r = new Random();
		int rno = r.nextInt(1000);
		
		// convert physical file into java object 
		FileInputStream fis = new FileInputStream("C:\\Users\\gulsh\\OneDrive\\Desktop\\data\\CommonData.properties");
		
		// create object for properties class
		Properties prop = new Properties();
		// load all the keys present in property file
		prop.load(fis);
		
		// fetching data from property file
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
		
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver= new ChromeDriver();
		}else if (BROWSER.equals("firefox")) {
			driver= new FirefoxDriver();
		}else {
			System.out.println("invalid browser");
		}
		
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("qspider"+rno);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String orgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		
		if (orgName.contains("qspider")) {
			System.out.println("orgName varification successful");
		}
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		WebElement profile = driver.findElement(By.xpath("//td[@class='small']/descendant::img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(profile).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
