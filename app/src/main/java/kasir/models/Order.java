package kasir.models;

import java.sql.Date;

/**
 * User model
 * self reminder: similar to Mongoose model
 */
public class Order {
	private String orderID;
	private String transactionID;
	private String userID;
	private String foodID;
	private long foodAmount;
	private long foodPrice;
	private long tableNumber;
	private String details;
	private String status;
	private Date date;

	/**
	 * Get the order ID
	 * @return orderID
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Set the order ID
	 * @param newID - The order ID
	 */
	public void setOrderID(String newID) {
		this.orderID = newID;
	}

	/**
	 * Get the transaction ID
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
	 * Get the food ID
	 * @return foodID
	 */
	public String getFoodID() {
		return foodID;
	}

	/**
	 * Set the food ID
	 * @param newID - The new food ID
	 */
	public void setFoodID(String newID) {
		this.foodID = newID;
	}

	/**
	 * Get the food amount
	 * @return foodAmount
	 */
	public long getFoodAmount() {
		return foodAmount;
	}

	/**
	 * Set the food amount
	 * @param newAmount - The new food amount
	 */
	public void setFoodAmount(long newAmount) {
		this.foodAmount = newAmount;
	}

	/**
	 * Get the food price
	 * @return foodAmount
	 */
	public long getFoodPrice() {
		return foodPrice;
	}

	/**
	 * Set the food price
	 * @param newPrice - The new food price
	 */
	public void setFoodPrice(long newPrice) {
		this.foodPrice = newPrice;
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
	 * @param newDetails - The new detail
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
	 * @param newStatus - The new status
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * Get the table number
	 * @return tableNumber
	 */
	public long getTableNumber() {
		return tableNumber;
	}

	/**
	 * Set the table number
	 * @param newNumber - The new table number
	 */
	public void setTableNumber(long newNumber) {
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
	 * @param newDate - The new order date
	 */
	public void setDate(Date newDate) {
		this.date = newDate;
	}
}
