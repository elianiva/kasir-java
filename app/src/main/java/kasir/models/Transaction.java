package kasir.models;

import java.sql.Date;

/**
 * Transaction model
 * self reminder: similar to Mongoose model
 */
public class Transaction {
	private String userID;
	private String transactionID;
	private Date date;
	private long totalPrice;
	private long totalPaid;
	private long exchange;

	/**
	 * Get the user ID
	 * @return userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * Set the user ID
	 * @param newID - The new user ID
	 */
	public void setUserID(String newID) {
		this.userID = newID;
	}

	/**
	 * Get the exchange
	 * @return exchange
	 */
	public long getExchange() {
		return exchange;
	}

	/**
	 * Set the exchange
	 * @param newExchange - The new exchange amount
	 */
	public void setExchange(long newExchange) {
		this.exchange = newExchange;
	}

	/**
	 * Get the date
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Set the date
	 * @param newDate - The new date
	 */
	public void setDate(Date newDate) {
		this.date = newDate;
	}

	/**
	 * Get the total paid
	 * @return totalPaid
	 */
	public long getTotalPaid() {
		return totalPaid;
	}

	/**
	 * Set the total paid
	 * @param newAmount - The new total paid amount
	 */
	public void setTotalPaid(long newAmount) {
		this.totalPaid = newAmount;
	}

	/**
	 * Get the total price
	 * @return totalPrice
	 */
	public long getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Set the total price
	 * @param newAmount - The new total price amount
	 */
	public void setTotalPrice(long newAmount) {
		this.totalPrice = newAmount;
	}

	/**
	 * Get the transactionID
	 * @return transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * Set the transaction ID
	 * @param newID - The new transaction ID
	 */
	public void setTransactionID(String newID) {
		this.transactionID = newID;
	}
}
