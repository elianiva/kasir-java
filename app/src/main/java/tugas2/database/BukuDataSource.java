package tugas2.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tugas2.models.Buku;

/**
* A class that handles all CRUD operations of the book
*/
public class BookDataSource {
	/**
	 * Save the passed `book` instance to the database
	 * @param buku - book model
	 * @throws java.sql.SQLException
	 */
	public void save(Book buku) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO buku(judul, pengarang) VALUES (?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?) will turn into ("title", "author")
		preparedStatement.setString(1, buku.getBookTitle());
		preparedStatement.setString(2, buku.getBookAuthor());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete books based on the passed `id` from the database
	 * @param buku - book model
	 * @throws java.sql.SQLException
	 */
	public void update(Book buku) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "UPDATE buku SET judul=?, pengarang=?, WHERE id_buku=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?) will turn into ("title", "author")
		preparedStatement.setString(1, buku.getBookTitle());
		preparedStatement.setString(2, buku.getBookAuthor());
		preparedStatement.setLong(3, buku.getBookID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete books based on the passed `id` from the database
	 * @param buku book model with the corresponding Id
	 * @return void
	 */
	public void delete(Book buku) throws SQLException {
		String sql = "DELETE FROM buku WHERE id=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setLong(1, buku.getBookID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Get all books from the database
	 * @return List<Buku>
	 */
	public List<Book> findAll() throws SQLException {
		String sql = "SELECT * FROM buku";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet result = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Book> bookList = new ArrayList<Book>();

		// iterate through the available data and add the result to `bookList`
		while(result.next()) {
			Book buku = new Book();
			buku.setBookID(result.getLong("id_buku"));
			buku.setBookTitle(result.getString("judul"));
			buku.setBookAuthor(result.getString("pengarang"));
			bookList.add(buku);
		}

		return bookList;
	}
}
