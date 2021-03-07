package kasir.helpers;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides getter and setter for the table value
 */
public class OrderTable {
	private List<List<Object>> rows;

	public OrderTable() {
		this.rows = new ArrayList<List<Object>>();
	}

	/**
	 * Get rows
	 * @return rows
	 */
	public List<List<Object>> getRows() {
		return rows;
	}

	/**
	 * Append row
	 * @return rows
	 */
	public void addRow(List<Object> row) {
		rows.add(row);
	}

	/**
	 * Set rows
	 * @param newRows
	 */
	public void setRows(List<List<Object>> newRows) {
		this.rows = newRows;
	}
}
