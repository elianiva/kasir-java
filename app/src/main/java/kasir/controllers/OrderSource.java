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
	/**
	 * Save the passed `Order` instance to the database
	 * @param Order - The `Order` model
	 * @throws java.sql.SQLException
	 */
	public void save(Order Order) throws SQLException {
		// we should use prepared statement to prevent
		// SQL injection
		String sql = "INSERT INTO order (id_order, no_meja, tanggal, id_user, keterangan, status_order) VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		// this code replaces the `?` from the `sql` string above
		// e.g (?, ?, ?) will turn into ("Ordername", "password", 1)
		preparedStatement.setString(1, Order.getOrderID());
		preparedStatement.setInt(2, Order.getTableNumber());
		preparedStatement.setDate(3, Order.getDate());
		preparedStatement.setString(4, Order.getUserID());
		preparedStatement.setString(5, Order.getDetails());
		preparedStatement.setString(6, Order.getStatus());

		preparedStatement.executeUpdate();
	}

	/**
	 * Update an Order
	 * @param order - order model
	 * @throws java.sql.SQLException
	 */
	public void update(Order order) throws SQLException {
		String sql = "UPDATE order SET no_meja=?, tanggal=?, id_user=?, keterangan=?, status_order=? WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setInt(1, order.getTableNumber());
		preparedStatement.setDate(2, order.getDate());
		preparedStatement.setString(3, order.getUserID());
		preparedStatement.setString(4, order.getDetails());
		preparedStatement.setString(4, order.getStatus());

		preparedStatement.executeUpdate();
	}

	/**
	 * Delete an Order
	 * @param order - order model
	 * @throws java.sql.SQLException
	 */
	public void delete(Order order) throws SQLException {
		String sql = "DELETE FROM Order WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, order.getOrderID());

		preparedStatement.executeUpdate();
	}

	/**
	 * Get all Orders from the database
	 * @return List<Order>
	 */
	public List<Order> findAll() throws SQLException {
		String sql = "SELECT * FROM Order";
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
	 */
	public Order find(Order Order) throws SQLException {
		Order orderResult = new Order();
		String sql = "SELECT * FROM Order WHERE id_order=?";
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql);

		preparedStatement.setString(1, Order.getOrderID());

		ResultSet rs = preparedStatement.executeQuery();

		if (rs.next()) {
			orderResult.setOrderID(rs.getString("id_order"));
			orderResult.setTableNumber(rs.getInt("no_meja"));
			orderResult.setDate(rs.getDate("tanggal"));
			orderResult.setUserID(rs.getString("id_user"));
			orderResult.setDetails(rs.getString("keterangan"));
			orderResult.setStatus(rs.getString("status_order"));
			return orderResult;
		}

		return null;
	}
}

