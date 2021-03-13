package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.models.Food;
import kasir.database.ConnectionHelper;

/**
 * A class which takes care of any CRUD operation for the `Food` model
 */
public class FoodSource {
	private Food food;

	/**
	 * Initialise the `user` instance for this class
	 * @param user - The User instance
	 */
	public FoodSource(Food food) {
		this.food = food;
	}

	/**
	 * Save the passed `food` instance to the database
	 * @param food - The `Food` model
	 * @throws java.sql.SQLException
	 */
	public void save() throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO masakan (id_masakan, nama_masakan, harga, stok, status_masakan) VALUES (?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("nama_masakan", "1000", 1, "Habis")
		stmt.setString(1, food.getFoodID());
		stmt.setString(2, food.getName());
		stmt.setLong(3, food.getPrice());
		stmt.setLong(4, food.getStock());
		stmt.setString(5, food.getStatus());

		stmt.executeUpdate();
	}

	/**
	 * Update `masakan`
	 * @param food - `Food` model
	 * @throws java.sql.SQLException
	 */
	public void update() throws SQLException {
		String sql = "UPDATE masakan SET nama_masakan=?, harga=?, stok=?, status_masakan=? WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, food.getName());
		stmt.setLong(2, food.getPrice());
		stmt.setLong(3, food.getStock());
		stmt.setString(4, food.getStatus());
		stmt.setString(5, food.getFoodID());

		stmt.executeUpdate();
	}

	/**
	 * Delete a food
	 * @param food - `Food` model
	 * @throws java.sql.SQLException
	 */
	public void delete() throws SQLException {
		String sql = "DELETE FROM masakan WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, food.getFoodID());

		stmt.executeUpdate();
	}

	/**
	 * Get all foods from the database
	 * @return List<Masakan>
	 */
	public static List<Food> findAll() throws SQLException {
		// this function can be static because we don't need the `User`
		// instance here
		String sql = "SELECT * FROM masakan";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet result = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Food> foodList = new ArrayList<Food>();

		// iterate through the available data and add the result to `foodList`
		while(result.next()) {
			Food food = new Food();
			food.setFoodID(result.getString("id_masakan"));
			food.setName(result.getString("nama_masakan"));
			food.setPrice(result.getLong("harga"));
			food.setStock(result.getLong("stok"));
			foodList.add(food);
		}

		return foodList;
	}

	/**
	 * Returns food if it exists
	 * @return Food
	 */
	public Food find() throws SQLException {
		Food foodResult = new Food();
		String sql = "SELECT * FROM masakan WHERE id_masakan=? OR nama_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, food.getFoodID());
		stmt.setString(2, food.getName());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			foodResult.setFoodID(rs.getString("id_masakan"));
			foodResult.setName(rs.getString("nama_masakan"));
			foodResult.setPrice(rs.getLong("harga"));
			foodResult.setStock(rs.getLong("stok"));

			return foodResult;
		}

		return null;
	}

	/**
	 * Find a food using an ID
	 * @return Food
	 */
	public static Food findByID(String id) throws SQLException {
		Food foodResult = new Food();
		String sql = "SELECT * FROM masakan WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, id);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			foodResult.setFoodID(rs.getString("id_masakan"));
			foodResult.setName(rs.getString("nama_masakan"));
			foodResult.setPrice(rs.getLong("harga"));
			foodResult.setStock(rs.getLong("stok"));

			return foodResult;
		}

		return null;
	}
}
