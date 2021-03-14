package kasir.models;

/**
 * User model
 * self reminder: similar to Mongoose model
 */
public class Food {
	private String foodID;
	private String name;
	private long price;
	private long stock;
	private String status;

	/**
	 * Get the food ID
	 * @return food ID
	 */
	public String getFoodID() {
		return foodID;
	}

	/**
	 * Set the food ID
	 * @param id - The food ID
	 */
	public void setFoodID(String id) {
		this.foodID = id;
	}

	/**
	 * Get the food name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the food name
	 * @param newName - The new food name
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Get the food stock
	 * @return stock
	 */
	public long getStock() {
		return stock;
	}

	/**
	 * Set the food stock
	 * @param newStock - The new food stock
	 */
	public void setStock(long newStock) {
		this.stock = newStock;
		this.setStatus(stock > 0 ? "Tersedia" : "Habis");
	}

	/**
	 * Get the food price
	 * @return price
	 */
	public long getPrice() {
		return price;
	}

	/**
	 * Set the food price
	 * @param newPrice - The new food price
	 */
	public void setPrice(long newPrice) {
		this.price = newPrice;
	}

	/**
	 * Get the food status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the food status
	 * @param newStatus - The new food status
	 */
	private void setStatus(String newStatus) {
		this.status = newStatus;
	}
}
