package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.models.Masakan;
import kasir.database.ConnectionHelper;

/**
 * A class which takes care of any CRUD operation for the `Masakan` model
 */
public class MasakanSource {
	/**
	 * Save the passed `food` instance to the database
	 * @param food - The `Masakan` model
	 * @throws java.sql.SQLException
	 */
	public void save(Masakan food) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO food (nama_masakan, harga, stok, status) VALUES (?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("nama_masakan", "1000", 1, "Habis")
		preparedStatement.setString(1, food.getName());
		preparedStatement.setLong(2, food.getPrice());
		preparedStatement.setLong(3, food.getStock());
		preparedStatement.setString(4, food.getStatus());

		preparedStatement.executeUpdate();
	}

	/**
	 * Update `masakan`
	 * @param food - `Masakan` model
	 * @throws java.sql.SQLException
	 */
	public void update(Masakan food) throws SQLException {
		String sql = "UPDATE user SET nama_masakan=?, harga=?, stok=?, status=? WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, food.getName());
		preparedStatement.setLong(2, food.getPrice());
		preparedStatement.setLong(3, food.getStock());
		preparedStatement.setString(4, food.getStatus());
		preparedStatement.setLong(5, food.getFoodID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete a food
	 * @param food - `Masakan` model
	 * @throws java.sql.SQLException
	 */
	public void delete(Masakan food) throws SQLException {
		String sql = "DELETE FROM masakan WHERE id_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setLong(1, food.getFoodID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Get all foods from the database
	 * @return List<Masakan>
	 */
	public List<Masakan> findAll() throws SQLException {
		String sql = "SELECT * FROM user";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet result = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Masakan> foodList = new ArrayList<Masakan>();

		// iterate through the available data and add the result to `bookList`
		while(result.next()) {
			Masakan food = new Masakan();
			food.setFoodID(result.getLong("id_masakan"));
			food.setName(result.getString("nama_masakan"));
			food.setPrice(result.getLong("harga"));
			food.setStock(result.getLong("stock"));
			food.setStatus(result.getString("status"));
			foodList.add(food);
		}

		return foodList;
	}

	/**
	 * Returns food if it exists
	 * @return Masakan
	 */
	public Masakan find(Masakan food) throws SQLException {
		Masakan foodResult = new Masakan();
		String sql = "SELECT * FROM masakan WHERE id_masakan=? AND nama_masakan=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setLong(1, food.getFoodID());
		preparedStatement.setString(2, food.getName());

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			foodResult.setFoodID(rs.getLong("id_masakan"));
			foodResult.setName(rs.getString("nama_masakan"));
			foodResult.setPrice(rs.getLong("harga"));
			foodResult.setStock(rs.getLong("stock"));
			foodResult.setStatus(rs.getString("status"));

			return foodResult;
		}

		return null;
	}
}
