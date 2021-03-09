package kasir.helpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * A helper class to format the given number to Rupiah
 */
public class FormatRupiah {
	/**
	 * Format the given number to an appropriate Rupiah format.
	 * @param num - The number you want to format
	 * @return formattedNumber
	 */
	public static String format(long num) {
		DecimalFormat kurs = (DecimalFormat) DecimalFormat.getInstance();
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();

		symbols.setMonetaryDecimalSeparator(',');
		symbols.setGroupingSeparator('.');

		kurs.setDecimalFormatSymbols(symbols);

		return "Rp. " + kurs.format(num);
	}

	/**
	 * Normalise the formatted number
	 * @param str - The string you want to normalise
	 * @return normalisedString
	 */
	public static String normalise(String str) {
		return str.replaceAll("[a-zA-Z\\.\\s]", "");
	}
}
