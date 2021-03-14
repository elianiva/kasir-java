package kasir.helpers;

import java.awt.Dialog;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import kasir.database.ConnectionHelper;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Receipt {

	/**
	 * Method to get a receipt of order based on the passed transaction id
	 * @param transactionID - The transaction ID
	 */
	public static void getReceipt(String transactionID) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_transaksi", transactionID);

		try {
			// get the connection to our database, this is needed because jasper
			// will execute the `queryString` inside the jrxml
			Connection conn = ConnectionHelper.getConnection();

			// load the template file using `File` constructor
			File template = new File("src/main/java/kasir/reports/struk_pembayaran.jrxml");

			// prepare the jasper design by loading the template
			JasperDesign design = JRXmlLoader.load(template);

			// compile the template to jasper format
			JasperReport report = JasperCompileManager.compileReport(design);

			// fill the template, we execute the `queryString` here
			JasperPrint result = JasperFillManager.fillReport(report, params, conn);

			// display the result in a JDialog modal window
			JasperViewer view = new JasperViewer(result, false);
			JDialog dialog = new JDialog();
			dialog.setContentPane(view.getContentPane());
			dialog.setSize(view.getSize());
			dialog.setTitle("STRUK PEMBAYARAN");
			dialog.setAlwaysOnTop(true);
			dialog.setModalityType(Dialog.ModalityType.MODELESS);
			dialog.setModal(true);
			dialog.setLocationRelativeTo(null); // center the dialog
			dialog.setVisible(true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Gagal mendapatkan koneksi!");
			ex.printStackTrace();
		} catch (JRException ex) {
			JOptionPane.showMessageDialog(null, "Gagal mencetak struk!");
			ex.printStackTrace();
		}
	}
}
