package kasir.models;

/**
 * User model
 * self reminder: similar to Mongoose model
 */
public class User {
	private long userID;
	private long levelID;
	private String username;
	private String password;

	/**
	 * Get a user ID
	 * @return userID
	 */
	public long getUserID() {
		return userID;
	}

	/**
	 * Set a user ID
	 * @param userID
	 */
	public void setUserID(long id) {
		this.userID = id;
	}

	/**
	 * Get a username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set a user ID
	 * @param name
	 */
	public void setUsername(String name) {
		this.username = name;
	}

	/**
	 * Get a password
	 * @return username
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set a password
	 * @param pass
	 */
	public void setPassword(String pass) {
		this.password = pass;
	}

	/**
	 * Get a levelID
	 * @return levelID
	 */
	public long getLevelID() {
		return levelID;
	}

	/**
	 * Set a level ID
	 * @param id
	 */
	public void setLevelID(long id) {
		this.levelID = id;
	}
}
