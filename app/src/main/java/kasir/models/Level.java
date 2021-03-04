package kasir.models;

public class Level {
	private long levelID = 0;
	private String levelName = "";

	/**
	 * Get the level ID
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

	/**
	 * Get the level name
	 * @return levelName
	 */
	public String getLevelName() {
		return levelName;
	}

	/**
	 * Set the level name
	 * @param newName
	 */
	public void setLevelName(String newName) {
		this.levelName = newName;
	}
}
