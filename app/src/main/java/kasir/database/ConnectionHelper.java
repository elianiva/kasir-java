package kasir.database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class to connect to the database, used for the entire lifetime
 * of the app so we don't repeat ourself.
 */
public class ConnectionHelper {
	// these are the details which we can change according to our needs
	private static final String DB_NAME = "kasir";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "2003";
	private static final String DB_URL = "jdbc:mysql://localhost/"+DB_NAME+"?useSSL=false";

	/**
	 * Method that returns connection to the database which we can then
	 * re-use over and over
	 * @return Connection - The connection instance
	 * @throws java.sql.SQLException
	 */
	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		return connection;
	}
}
