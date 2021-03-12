package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.models.Transaction;
import kasir.database.ConnectionHelper;

/**
 * A class which takes care of any CRUD operation for the `Transaction` model
 */
public class TransactionSource {
	private Transaction transaction;

	/**
	 * Initialise the `user` instance for this class
	 * @param user - The User instance
	 */
	public TransactionSource(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * Save the passed `Transaction` instance to the database
	 * @param transaction - The `Transaction` model
	 * @throws java.sql.SQLException
	 */
	public void save() throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO transaksi (id_transaksi, id_user, tanggal, total_harga, total_bayar, kembalian) "
					+"VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		stmt.setString(1, transaction.getTransactionID());
		stmt.setString(2, transaction.getUserID());
		stmt.setDate(3, transaction.getDate());
		stmt.setLong(4, transaction.getTotalPrice());
		stmt.setLong(5, transaction.getTotalPaid());
		stmt.setLong(6, transaction.getExchange());

		stmt.executeUpdate();
	}

	/**
	 * Update an Transaction
	 * @param order - order model
	 * @throws java.sql.SQLException
	 */
	public void update() throws SQLException {
		String sql = "UPDATE order SET id_user=?, tanggal=?, total_harga=?, total_bayar=?, kembalian=? WHERE id_transaksi=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, transaction.getUserID());
		stmt.setDate(2, transaction.getDate());
		stmt.setLong(3, transaction.getTotalPrice());
		stmt.setLong(4, transaction.getTotalPaid());
		stmt.setLong(5, transaction.getExchange());
		stmt.setString(6, transaction.getTransactionID());

		stmt.executeUpdate();
	}

	/**
	 * Delete a Transaction
	 * @param transaction - transaction model
	 * @throws java.sql.SQLException
	 */
	public void delete() throws SQLException {
		String sql = "DELETE FROM transaksi WHERE id_transaksi=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, transaction.getTransactionID());

		stmt.executeUpdate();
	}

	/**
	 * Get all Transactions from the database
	 * @return List<Transaction>
	 */
	public static List<Transaction> findAll() throws SQLException {
		String sql = "SELECT * FROM transaksi";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Transaction> transactionList = new ArrayList<Transaction>();

		// iterate through the available data and add the result to `orderList`
		while(rs.next()) {
			Transaction transaction = new Transaction();
			transaction.setUserID(rs.getString("id_user"));
			transaction.setTransactionID(rs.getString("id_transaksi"));
			transaction.setDate(rs.getDate("tanggal"));
			transaction.setTotalPrice(rs.getLong("total_harga"));
			transaction.setTotalPaid(rs.getLong("total_bayar"));
			transaction.setExchange(rs.getLong("kembalian"));
		}

		return transactionList;
	}

	/**
	 * Returns Transaction if they exists
	 * @return Transaction
	 */
	public Transaction find() throws SQLException {
		Transaction transaction = new Transaction();
		String sql = "SELECT * FROM order WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, transaction.getTransactionID());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			transaction.setUserID(rs.getString("id_user"));
			transaction.setTransactionID(rs.getString("id_transaksi"));
			transaction.setDate(rs.getDate("tanggal"));
			transaction.setTotalPrice(rs.getLong("total_harga"));
			transaction.setTotalPaid(rs.getLong("total_bayar"));
			transaction.setExchange(rs.getLong("kembalian"));
			return transaction;
		}

		return null;
	}
}

