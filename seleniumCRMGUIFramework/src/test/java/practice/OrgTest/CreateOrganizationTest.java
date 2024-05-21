package practice.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationTest {

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
		String orgName = sh.getRow(1).getCell(2).toString() + rNum;
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
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();

		// verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created ==> PASS");
		} else {
			System.out.println(orgName + " is not created ==> FAIL");
		}

		// verify header orgNameinfo expected result
		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " is created ==> PASS");
		} else {
			System.out.println(orgName + " is not created ==> FAIL");
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
