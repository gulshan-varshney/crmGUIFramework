package practice.DataDrivenTesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcelTest {

	
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		
		// Get excel file location and java object
		FileInputStream fis = new FileInputStream("C:\\Users\\gulsh\\OneDrive\\Desktop\\data\\testScriptData.xlsx");
		
		// open workbook in read mode
		Workbook wb = WorkbookFactory.create(fis);
		
		//get the control of sheet by sheet name
		Sheet sh = wb.getSheet("org");
		
		//get the control of 1st row
		Row row = sh.getRow(1);
		
		//get the control of required cell
		Cell cell = row.getCell(3);
		String data = cell.getStringCellValue();
		System.out.println(data);
		
		wb.close();
		
		
		
	}
}
