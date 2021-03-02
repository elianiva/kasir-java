package kasir.models;

import java.sql.Date;

/**
 * User model
 * self reminder: similar to Mongoose model
 */
public class Order {
	private String userID;
	private String orderID;
	private int tableNumber;
	private String details;
	private String status;
	// TODO: figure out this datatype
	private Date date;

	/**
	 * Get the user ID
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Set the user ID
	 * @param newID
	 */
	public void setUserID(String newID) {
		this.userID = newID;
	}

	/**
	 * Get the order ID
	 * @return orderID
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Set the order ID
	 * @param newID
	 */
	public void setOrderID(String newID) {
		this.orderID = newID;
	}

	/**
	 * Get the details
	 * @return details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Set the order details
	 * @param newDetails
	 */
	public void setDetails(String newDetails) {
		this.details = newDetails;
	}

	/**
	 * Get the status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status
	 * @param newStatus
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * Get the table number
	 * @return tableNumber
	 */
	public int getTableNumber() {
		return tableNumber;
	}

	/**
	 * Set the table number
	 * @param newNumber
	 */
	public void setTableNumber(int newNumber) {
		this.tableNumber = newNumber;
	}

	/**
	 * Get the order date
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the user ID
	 * @param newDate
	 */
	public void setDate(Date newDate) {
		this.date = newDate;
	}
}
