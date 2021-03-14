package kasir.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kasir.models.Order;
import kasir.database.ConnectionHelper;

/**
 * A class which takes care of any CRUD operation for the `Order` model
 */
public class OrderSource {
	private Order order;

	/**
	 * Initialise the `order` instance for this class
	 * @param order - The Order instance
	 */
	public OrderSource(Order order) {
		this.order = order;
	}

	/**
	 * Save the passed `Order` instance to the database
	 * @throws java.sql.SQLException - Error
	 */
	public void save() throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO `order` (id_order, id_transaksi, id_user, no_meja, tanggal, id_masakan, jumlah_masakan, total_harga, keterangan, status_order) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		stmt.setString(1, order.getOrderID());
		stmt.setString(2, order.getTransactionID());
		stmt.setString(3, order.getUserID());
		stmt.setLong(4, order.getTableNumber());
		stmt.setDate(5, order.getDate());
		stmt.setString(6, order.getFoodID());
		stmt.setLong(7, order.getFoodAmount());
		stmt.setLong(8, order.getFoodPrice());
		stmt.setString(9, order.getDetails());
		stmt.setString(10, order.getStatus());

		stmt.executeUpdate();
	}

	/**
	 * Update an Order
	 * @throws java.sql.SQLException - Error
	 */
	public void update() throws SQLException {
		String sql = "UPDATE `order` SET id_transaksi=?, id_user=?, no_meja=?, tanggal=?, id_masakan=?, jumlah_masakan=?, total_harga=?, keterangan=?, status_order=? WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, order.getTransactionID());
		stmt.setString(2, order.getUserID());
		stmt.setLong(3, order.getTableNumber());
		stmt.setDate(4, order.getDate());
		stmt.setString(5, order.getFoodID());
		stmt.setLong(6, order.getFoodAmount());
		stmt.setLong(7, order.getFoodPrice());
		stmt.setString(8, order.getDetails());
		stmt.setString(9, order.getStatus());
		stmt.setString(10, order.getOrderID());

		stmt.executeUpdate();
	}

	/**
	 * Delete an Order
	 * @throws java.sql.SQLException - Error
	 */
	public void delete() throws SQLException {
		String sql = "DELETE FROM `order` WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, order.getOrderID());

		stmt.executeUpdate();
	}

	/**
	 * Get all Orders from the database
	 * @return orderList
	 * @throws java.sql.SQLException - Error
	 */
	public static List<Order> findAll() throws SQLException {
		// this function can be static because we don't need the `User`
		// instance here
		String sql = "SELECT * FROM `order`";
		Connection connection = ConnectionHelper.getConnection();
		ResultSet rs = connection.createStatement().executeQuery(sql);

		// we use List instead of Vector because it's the recommended way
		List<Order> orderList = new ArrayList<Order>();

		// iterate through the available data and add the result to `orderList`
		while(rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getString("id_order"));
			order.setTableNumber(rs.getInt("no_meja"));
			order.setDate(rs.getDate("tanggal"));
			order.setUserID(rs.getString("id_user"));
			order.setDetails(rs.getString("keterangan"));
			order.setStatus(rs.getString("status_order"));
			orderList.add(order);
		}

		return orderList;
	}

	/**
	 * Returns Order if they exists
	 * @return Order
	 * @throws java.sql.SQLException - Error
	 */
	public Order find() throws SQLException {
		Order orderResult = new Order();
		String sql = "SELECT * FROM `order` WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, order.getOrderID());

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			orderResult.setOrderID(rs.getString("id_order"));
			orderResult.setFoodID(rs.getString("id_masakan"));
			orderResult.setFoodAmount(rs.getLong("jumlah_masakan"));
			orderResult.setFoodPrice(rs.getLong("total_harga"));
			orderResult.setTableNumber(rs.getInt("no_meja"));
			orderResult.setDate(rs.getDate("tanggal"));
			orderResult.setUserID(rs.getString("id_user"));
			orderResult.setDetails(rs.getString("keterangan"));
			orderResult.setStatus(rs.getString("status_order"));
			return orderResult;
		}

		return null;
	}

	/**
	 * Find Orders that has the same transaction ID
	 * @param transactionID - The transaction ID
	 * @return Order
	 * @throws java.sql.SQLException - Error
	 */
	public static List<Order> findByTransactionID(String transactionID) throws SQLException {
		List<Order> orderResult = new ArrayList<Order>();
		String sql = "SELECT * FROM `order` WHERE id_transaksi=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement stmt = connection.prepareStatement(sql);

		stmt.setString(1, transactionID);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Order order = new Order();
			order.setOrderID(rs.getString("id_order"));
			order.setFoodID(rs.getString("id_masakan"));
			order.setFoodAmount(rs.getLong("jumlah_masakan"));
			order.setFoodPrice(rs.getLong("total_harga"));
			order.setTableNumber(rs.getInt("no_meja"));
			order.setDate(rs.getDate("tanggal"));
			order.setUserID(rs.getString("id_user"));
			order.setDetails(rs.getString("keterangan"));
			order.setStatus(rs.getString("status_order"));
			orderResult.add(order);
		}

		if (orderResult.size() != 0) {
			return orderResult;
		}

		return null;
	}
}

