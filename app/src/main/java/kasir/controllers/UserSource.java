package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.models.User;
import kasir.database.ConnectionHelper;

/**
 * A class which takes care of any CRUD operation for the `User` model
 */
public class UserSource {
	/**
	 * Save the passed `user` instance to the database
	 * @param user - The `User` model
	 * @throws java.sql.SQLException
	 */
	public void save(User user) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO user(username, password, id_level) VALUES (?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("username", "password", 1)
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setLong(3, user.getLevelID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Update a user
	 * @param book - book model
	 * @throws java.sql.SQLException
	 */
	public void update(User user) throws SQLException {
		String sql = "UPDATE user SET username=?, password=?, id_level=? WHERE id_user=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setLong(3, user.getLevelID());
		preparedStatement.setLong(4, user.getUserID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete a user
	 * @param book - book model
	 * @throws java.sql.SQLException
	 */
	public void delete(User user) throws SQLException {
		String sql = "DELETE FROM user WHERE id_user=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setLong(1, user.getUserID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Get all users from the database
	 * @return List<User>
	 */
	public List<User> findAll() throws SQLException {
		String sql = "SELECT * FROM user";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet result = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<User> bookList = new ArrayList<User>();

		// iterate through the available data and add the result to `bookList`
		while(result.next()) {
			User book = new User();
			book.setUserID(result.getLong("id_user"));
			book.setUsername(result.getString("username"));
			book.setPassword(result.getString("password"));
			book.setLevelID(result.getLong("id_level"));
			bookList.add(book);
		}

		return bookList;
	}

	/**
	 * Returns user if they exists
	 * @return User
	 */
	public User find(User user) throws SQLException {
		User userResult = new User();
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			userResult.setUserID(rs.getLong("id_user"));
			userResult.setLevelID(rs.getLong("id_level"));
			userResult.setUsername(rs.getString("username"));

			return userResult;
		}

		return null;
	}
}
