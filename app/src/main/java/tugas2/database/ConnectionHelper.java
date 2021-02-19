package tugas2.database;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// this class is used for getting connection so we don't repeat ourself
public class ConnectionHelper {
	// these are the details which we can change according to our needs
	private static final String DATABASE_NAME = "perpustakaan";
	private static final String USER = "elianiva";
	private static final String PASSWORD = "2003";
	private static final String URL = "jdbc:mysql://localhost/"+DATABASE_NAME+"?useSSL=false";

	/**
	 * Method that returns connection to the database which we can then use
	 * later on
	 * @return Connection - The connection instance
	 * @throws java.sql.SQLException
	 */
	public static Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new Driver());
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}
}
