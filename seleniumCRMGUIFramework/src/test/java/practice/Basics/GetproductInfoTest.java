package practice.Basics;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetproductInfoTest {

	@Test(dataProvider = "getdata")
	public void getProductInfoTest(String brand, String product) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand, Keys.ENTER);
		String x = "//span[text()='" + product
				+ "']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);

		driver.quit();
	}

	@DataProvider
	public Object[][] getdata() throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\gulsh\\OneDrive\\Desktop\\data\\testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("products");
		int rowCount = sh.getLastRowNum();
		int cell = sh.getRow(0).getLastCellNum();

		Object[][] objArr = new Object[rowCount][cell];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < cell; j++) {
				objArr[i - 1][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return objArr;
	}

}