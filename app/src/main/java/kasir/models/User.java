package kasir.models;

/**
 * User model
 * self reminder: similar to Mongoose model
 */
public class User {
	private String userID;
	private long levelID;
	private String username;
	private String password;
	private String name;

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
	 * Get the name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Get the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the user name
	 * @param newName
	 */
	public void setUsername(String newName) {
		this.username = newName;
	}

	/**
	 * Get the password
	 * @return username
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password
	 * @param newPassword
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	/**
	 * Get the levelID
	 * @return levelID
	 */
	public long getLevelID() {
		return levelID;
	}

	/**
	 * Set the level ID
	 * @param newID
	 */
	public void setLevelID(long newID) {
		this.levelID = newID;
	}
}
