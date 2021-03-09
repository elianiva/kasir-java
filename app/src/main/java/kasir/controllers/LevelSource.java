package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.database.ConnectionHelper;
import kasir.models.Level;

public class LevelSource {
	/**
	 * Returns a level if it exists
	 * @return Level
	 */
	public Level find(Level level) throws SQLException {
		Level levelResult = new Level();
		String sql = "SELECT * FROM level WHERE id_level=? OR nama_level=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setLong(1, level.getLevelID());
		stmt.setString(2, level.getLevelName());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			levelResult.setLevelID(rs.getLong("id_level"));
			levelResult.setLevelName(rs.getString("nama_level"));

			return levelResult;
		}

		return null;
	}

	/**
	 * Get all levels from the database
	 * @return List<Level>
	 */
	public List<Level> findAll() throws SQLException {
		String sql = "SELECT * FROM level";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet result = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Level> levelList = new ArrayList<Level>();

		// iterate through the available data and add the result to `levelList`
		while(result.next()) {
			Level level = new Level();
			level.setLevelID(result.getLong("id_level"));
			level.setLevelName(result.getString("nama_level"));
			levelList.add(level);
		}

		return levelList;
	}
}
