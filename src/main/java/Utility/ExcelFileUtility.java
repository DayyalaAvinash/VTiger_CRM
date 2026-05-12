package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author AVINASH D This Class Is Used To Fetch Data From Excel And Write Back
 *         Data To Excel
 */
public class ExcelFileUtility {

	Workbook book = null;

	/**
	 * This Is Method Is Used To Fetch Data From Excel
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String fetchDataFromExcelFile(String sheetname, int Rindex, int Cindex)
			throws EncryptedDocumentException, IOException {

		// fetch data from excel file
		FileInputStream efis = new FileInputStream("./src/test/resources/vtiger.xlsx");
		book = WorkbookFactory.create(efis);
		String data = book.getSheet(sheetname).getRow(Rindex).getCell(Cindex).toString();
		return data;

	}

	/**
	 * This Method Is Used To Write Back Data To Excel
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcelFile(String sheetname, int Rindex, int Cindex, String data)
			throws EncryptedDocumentException, IOException {

		// write back data from excel file
		FileInputStream efis = new FileInputStream("./src/test/resources/vtiger.xlsx");
		book = WorkbookFactory.create(efis);
		book.getSheet(sheetname).createRow(Rindex).createCell(Cindex).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/vtiger.xlsx");
		book.write(fos);

	}

	/**
	 * This Method Is Used To Write Back Data To Excel File Which Is In Already
	 * Existing Row
	 * 
	 * @param sheetname
	 * @param Rindex
	 * @param Cindex
	 * @param data
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeBackDataToExcelFile_ExistingRow(String sheetname, int Rindex, int Cindex, String data)
			throws EncryptedDocumentException, IOException {

		// write back data from excel file
		FileInputStream efis = new FileInputStream("./src/test/resources/vtiger.xlsx");
		book = WorkbookFactory.create(efis);
		book.getSheet(sheetname).getRow(Rindex).createCell(Cindex).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("./src/test/resources/vtiger.xlsx");
		book.write(fos);
	}

	/**
	 * This Method Is Used To Disconneted With Excel
	 * 
	 * @throws IOException
	 */
	public void closeExcel() throws IOException {
		book.close();
	}
}
