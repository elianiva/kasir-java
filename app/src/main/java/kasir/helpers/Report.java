package kasir.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Report {

	/**
	 * Export the given data to an excel file
	 * @param data - The data you want to export
	 */
	public static void getReport(List<List<String>> data) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet Name");
		XSSFRow row;

		// setup column widths
		sheet.setColumnWidth(0, 15 * 256);
		sheet.setColumnWidth(1, 20 * 256);
		sheet.setColumnWidth(2, 20 * 256);
		sheet.setColumnWidth(3, 20 * 256);
		sheet.setColumnWidth(4, 20 * 256);
		sheet.setColumnWidth(5, 20 * 256);

		int rowID = 0;
		for (List<String> d : data) { // this loop creates a new row
			row = sheet.createRow(rowID++);
			int cellID = 0;

			for (String value : d) { // this loop fills every cell for each row
				XSSFCell cell = row.createCell(cellID++);
				cell.setCellValue(value);
			}
		}

		// write to the xlsx file
		try {
			// use the current timestamp in milisecond as a filename to make sure it's unique everytime
			FileOutputStream outputFile = new FileOutputStream(
				new File("laporan-"+System.currentTimeMillis()+".xlsx")
			);

			// here's where the write process happens
			workbook.write(outputFile);
			outputFile.close();

			JOptionPane.showMessageDialog(null, "Berhasil mencetak laporan!");
		} catch (IOException ex) {
			Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Gagal mencetak laporan!");
			ex.printStackTrace();
		}
	}
}
