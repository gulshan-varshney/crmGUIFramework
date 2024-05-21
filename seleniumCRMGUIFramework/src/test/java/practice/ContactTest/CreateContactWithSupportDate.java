package practice.ContactTest;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable {

		Random r = new Random();
		int rNum = r.nextInt(100);

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
		Sheet sh = wb.getSheet("Contact");
		Row row = sh.getRow(4);
		String lastName = row.getCell(2).toString() + rNum;
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
		// login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// create contact
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		Date date = new Date();

		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = simpleDate.format(date);

		Calendar cal = simpleDate.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String lastdate = simpleDate.format(cal.getTime());

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		WebElement startDate = driver.findElement(By.name("support_start_date"));
		startDate.clear();
		startDate.sendKeys(currentDate);
		WebElement endDate = driver.findElement(By.name("support_end_date"));
		endDate.clear();
		endDate.sendKeys(lastdate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify header msg expected result
		String actContact = driver.findElement(By.className("dvHeaderText")).getText();
		if (actContact.contains(lastName)) {
			System.out.println(lastName + " is created===>PASS");
		} else {
			System.out.println(lastName + " is not created===>FAIL");
		}

		// verify start & end date
		String actSatrtDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if (actSatrtDate.equals(currentDate)) {
			System.out.println(currentDate + " information is verified===>PASS");
		} else {
			System.out.println(currentDate + " information is not verified===>PASS");
		}
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if (actEndDate.equals(lastdate)) {
			System.out.println(lastdate + " information is verified===>PASS");
		} else {
			System.out.println(lastdate + " information is not verified===>PASS");
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
