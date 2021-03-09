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
		String sql = "INSERT INTO user(id_user, name, username, password, id_level) VALUES (?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("username", "password", 1) etc
		stmt.setString(1, user.getUserID());
		stmt.setString(2, user.getName());
		stmt.setString(3, user.getUsername());
		stmt.setString(4, user.getPassword());
		stmt.setLong(5, user.getLevelID());

		stmt.executeUpdate();
	}

	/**
	 * Update a user
	 * @param user - user model
	 * @throws java.sql.SQLException
	 */
	public void update(User user) throws SQLException {
		String sql = "UPDATE user SET username=?, password=?, id_level=? WHERE id_user=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setLong(3, user.getLevelID());
		stmt.setString(4, user.getUserID());

		stmt.executeUpdate();
	}

	/**
	 * Delete a user
	 * @param user - user model
	 * @throws java.sql.SQLException
	 */
	public void delete(User user) throws SQLException {
		String sql = "DELETE FROM user WHERE id_user=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, user.getUserID());

		stmt.executeUpdate();
	}

	/**
	 * Get all users from the database
	 * @return List<User>
	 */
	public List<User> findAll() throws SQLException {
		String sql = "SELECT * FROM user";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<User> userList = new ArrayList<User>();

		// iterate through the available data and add the result to `userList`
		while(rs.next()) {
			User user = new User();
			user.setUserID(rs.getString("id_user"));
			user.setName(rs.getString("name"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setLevelID(rs.getLong("id_level"));
			userList.add(user);
		}

		return userList;
	}

	/**
	 * Returns a user if it exists
	 * @return User
	 */
	public User find(User user) throws SQLException {
		User userResult = new User();
		String sql = "SELECT * FROM user WHERE username=? AND password=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			userResult.setUserID(rs.getString("id_user"));
			userResult.setLevelID(rs.getLong("id_level"));
			userResult.setName(rs.getString("name"));
			userResult.setUsername(rs.getString("username"));

			return userResult;
		}

		return null;
	}
}
