package practice.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustryTest {

	public static void main(String[] args) throws IOException {

		Random r = new Random();
		int rNum = r.nextInt(1000);

		// read data from property file
		FileInputStream fis = new FileInputStream("C:\\Users\\gulsh\\OneDrive\\Desktop\\data\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		// read data from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\gulsh\\OneDrive\\Desktop\\data\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(4);
		String orgName = row.getCell(2).toString() + rNum;
		String industry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
		wb.close();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("invalid browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement indusDD = driver.findElement(By.name("industry"));
		Select s1 = new Select(indusDD);
		s1.selectByValue(industry);

		WebElement accType = driver.findElement(By.name("accounttype"));
		Select s2 = new Select(accType);
		s2.selectByValue(type);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();

		// verify the industry and type
		String actIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndustry.equals(industry)) {
			System.out.println(industry + " information is verified");
		} else {
			System.out.println(industry + " information is not verified");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified");
		} else {
			System.out.println(type + " information is not verified");
		}
		// logout
		Actions act = new Actions(driver);
		WebElement profile = driver
				.findElement(By.xpath("//td[@class='small']/descendant::img[@src='themes/softed/images/user.PNG']"));
		act.moveToElement(profile).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
