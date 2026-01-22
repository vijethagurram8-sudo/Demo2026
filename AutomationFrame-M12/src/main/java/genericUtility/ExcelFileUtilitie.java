package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * this
 */

public class ExcelFileUtilitie {
	/**
	 * 
	 * @param sheet
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String toReadDataFromExcelFile(String sheet,int row,int cell ) throws EncryptedDocumentException, IOException {
	FileInputStream fisw =new FileInputStream(".\\src\\test\\resources\\TestDataAdvance.xlsx");
	Workbook wb=WorkbookFactory.create(fisw);
	String value=wb.getSheet(sheet).getRow(row).getCell(cell).toString();
	return value;
	}

}


