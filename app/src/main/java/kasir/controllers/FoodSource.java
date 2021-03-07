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
	/**
	 * Save the passed `food` instance to the database
	 * @param food - The `Food` model
	 * @throws java.sql.SQLException
	 */
	public void save(Food food) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO masakan (id_masakan, nama_masakan, harga, stok, status_masakan) VALUES (?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("nama_masakan", "1000", 1, "Habis")
		preparedStatement.setString(1, food.getFoodID());
		preparedStatement.setString(2, food.getName());
		preparedStatement.setLong(3, food.getPrice());
		preparedStatement.setLong(4, food.getStock());
		preparedStatement.setString(5, food.getStatus());

		preparedStatement.executeUpdate();
	}

	/**
	 * Update `masakan`
	 * @param food - `Food` model
	 * @throws java.sql.SQLException
	 */
	public void update(Food food) throws SQLException {
		String sql = "UPDATE masakan SET nama_masakan=?, harga=?, stok=?, status_masakan=? WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, food.getName());
		preparedStatement.setLong(2, food.getPrice());
		preparedStatement.setLong(3, food.getStock());
		preparedStatement.setString(4, food.getStatus());
		preparedStatement.setString(5, food.getFoodID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete a food
	 * @param food - `Food` model
	 * @throws java.sql.SQLException
	 */
	public void delete(Food food) throws SQLException {
		String sql = "DELETE FROM masakan WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, food.getFoodID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Get all foods from the database
	 * @return List<Masakan>
	 */
	public List<Food> findAll() throws SQLException {
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
	public Food find(Food food) throws SQLException {
		Food foodResult = new Food();
		String sql = "SELECT * FROM masakan WHERE id_masakan=? AND nama_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, food.getFoodID());
		preparedStatement.setString(2, food.getName());

		ResultSet rs = preparedStatement.executeQuery();

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
