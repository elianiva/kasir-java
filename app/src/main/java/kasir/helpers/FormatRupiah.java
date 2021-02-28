package kasir.helpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatRupiah {
	public static String format(long num) {
		DecimalFormat kurs = (DecimalFormat) DecimalFormat.getInstance();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();

		symbols.setMonetaryDecimalSeparator(',');
		symbols.setGroupingSeparator('.');

		kurs.setDecimalFormatSymbols(symbols);

		return kurs.format(num);
	}
}
