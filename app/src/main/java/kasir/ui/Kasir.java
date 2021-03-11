package kasir.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import kasir.controllers.FoodSource;
import kasir.controllers.OrderSource;
import kasir.controllers.TransactionSource;
import kasir.helpers.FormatRupiah;
import kasir.helpers.OrderTable;
import kasir.helpers.Popup;
import kasir.models.Food;
import kasir.models.Transaction;
import kasir.models.User;

public class Kasir extends javax.swing.JFrame {
	public OrderTable tableData;
	private User user;
	private DefaultTableModel foodTableModel;
	private long price = 0, amount = 0;

	/**
	 * Creates new form Order
	 */
	public Kasir() {
	}
	public Kasir(User currentUser) {
		// center the window
		this.setLocationRelativeTo(null);
		user = currentUser;

		// override the close handler
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (foodTable.getRowCount() != 0) {
					JOptionPane.showMessageDialog(null, "Anda masih punya item tersisa!");
					return;
				}
				System.exit(0);
			}
		});

		// init the component and populate it with data
		initComponents();
		initTableModel();
		tableData = new OrderTable();

		// hide back button if the current user is not an admin
		if (user.getLevelID() != 1) {
			backButton.setVisible(false);
		}

		// update the exchange amount on each `payAmountField` keypress
		payAmountField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				setExchangeAmount();
			}
			public void removeUpdate(DocumentEvent e) {
				setExchangeAmount();
			}
			public void insertUpdate(DocumentEvent e) {
				setExchangeAmount();
			}
		});
	}

	private void setExchangeAmount() {
		// don't do anything if we don't have any items
		if (totalPriceField.getText().isEmpty() || payAmountField.getText().isEmpty()) {
			return;
		}

		String priceNumber = FormatRupiah.normalise(totalPriceField.getText());
		long totalPrice = Long.parseLong(priceNumber);
		long payAmount = Long.parseLong(payAmountField.getText());
		long exchange = totalPrice - payAmount;

		// handle a case when the paid amount is insufficient
		if (exchange < 0 && String.valueOf(exchange) != "") {
			exchangeField.setText(FormatRupiah.format(exchange).replace("-", ""));
		} else {
			exchangeField.setText("Pembayaran belum cukup");
		}
	}

	/**
	 * Set the desired table model
	 */
	private void initTableModel() {
		String[] columns = new String[]{
			"ID", "Nama Masakan", "Jumlah Pesanan", "Harga"
		};
		foodTableModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		foodTable.setModel(foodTableModel);
		foodTable.getTableHeader().setReorderingAllowed(false); // prevent from table re-ordering
	}

	/**
	 * Set the table data
	 */
	public void setTableData(List<List<Object>> data) {
		// reset the table before adding a new one
		foodTableModel.setRowCount(0);
		foodTable.clearSelection();
		foodTable.validate();

		// populate the table
		for (List<Object> row : data) {
			foodTableModel.addRow(new Object[] {
				row.get(0),
				row.get(1),
				row.get(2),
				FormatRupiah.format((long)row.get(3)),
			});
		}
		tableData.setRows(data);

		// takes care of the details
		for (List<Object> row : data) amount += (long)row.get(2);
		foodAmountField.setText(String.valueOf(amount));

		for (List<Object> row : data) price += (long)row.get(3);
		totalPriceField.setText(FormatRupiah.format(price));
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		scrollPane = new javax.swing.JScrollPane();
		foodTable = new javax.swing.JTable();
		foodAmountField = new javax.swing.JTextField();
		foodAmountLabel = new javax.swing.JLabel();
		totalPriceLabel = new javax.swing.JLabel();
		totalPriceField = new javax.swing.JTextField();
		payAmountField = new javax.swing.JTextField();
		payAmountLabel = new javax.swing.JLabel();
		exchangeField = new javax.swing.JTextField();
		exchangeLabel = new javax.swing.JLabel();
		addItemButton = new javax.swing.JButton();
		removeButton = new javax.swing.JButton();
		orderTitle = new javax.swing.JLabel();
		logoutButton = new javax.swing.JButton();
		confirmButton = new javax.swing.JButton();
		tableNumberField = new javax.swing.JTextField();
		tableNumberLabel = new javax.swing.JLabel();
		backButton = new javax.swing.JButton();

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 100, Short.MAX_VALUE)
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGap(0, 100, Short.MAX_VALUE)
		);

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		scrollPane.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

		foodTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		foodTable.setModel(new javax.swing.table.DefaultTableModel(
			new Object [][] {

			},
			new String [] {
				"ID", "Nama Masakan", "Jumlah Pesanan", "Harga"
			}
		) {
				Class[] types = new Class [] {
					java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
				};
				boolean[] canEdit = new boolean [] {
					false, false, false, false
				};

				public Class getColumnClass(int columnIndex) {
					return types [columnIndex];
				}

				public boolean isCellEditable(int rowIndex, int columnIndex) {
					return canEdit [columnIndex];
				}
			});
		scrollPane.setViewportView(foodTable);

		foodAmountField.setEditable(false);

		foodAmountLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		foodAmountLabel.setText("Jumlah Makanan");

		totalPriceLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		totalPriceLabel.setText("Total Harga");

		totalPriceField.setEditable(false);

		payAmountLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		payAmountLabel.setText("Jumlah Bayar");

		exchangeField.setEditable(false);

		exchangeLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		exchangeLabel.setText("Kembalian");

		addItemButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		addItemButton.setText("Tambah Item");
		addItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addItemButtonActionPerformed(evt);
			}
		});

		removeButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		removeButton.setText("Hapus Item");
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeButtonActionPerformed(evt);
			}
		});

		orderTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
		orderTitle.setText("Order Masakan");

		logoutButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		logoutButton.setText("Logout");
		logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		logoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});

		confirmButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		confirmButton.setText("Konfirmasi Order");
		confirmButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmButtonActionPerformed(evt);
			}
		});

		tableNumberLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		tableNumberLabel.setText("Nomor Meja");

		backButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		backButton.setText("Kembali");
		backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addContainerGap(21, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(logoutButton)
						.addGap(234, 234, 234)
						.addComponent(orderTitle)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(tableNumberLabel)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(backButton)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
									.addComponent(foodAmountField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addComponent(foodAmountLabel)
									.addComponent(totalPriceLabel)
									.addComponent(totalPriceField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addComponent(payAmountField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addComponent(payAmountLabel)
									.addComponent(exchangeLabel)
									.addComponent(exchangeField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
									.addComponent(removeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(addItemButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(confirmButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(tableNumberField))))
						.addGap(18, 18, 18))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGap(17, 17, 17)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(orderTitle)
					.addComponent(logoutButton)
					.addComponent(backButton))
				.addGap(35, 35, 35)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(tableNumberLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(tableNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(foodAmountLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(foodAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addComponent(totalPriceLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(totalPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(payAmountLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(payAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(exchangeLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(exchangeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(addItemButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(removeButton)
						.addGap(13, 13, 13)
						.addComponent(confirmButton))
					.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
				.addContainerGap(19, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
		int row = foodTable.getSelectedRow();

		String id = foodTable.getValueAt(row, 0).toString();
		long amount = Long.parseLong(foodTable.getValueAt(row, 2).toString());

		try {
			// we need to do this because we want the detail of the selected item
			// from the database so we can calculate how many stocks there are
			Food filter = new Food();
			filter.setFoodID(id);
			Food food = new FoodSource(filter).find();

			// add the existing stock with the selected stock. What we want is
			// if we have 2 items in the database and 1 item in the foodTable
			// we want to update the one in the database to 3 items
			food.setStock(food.getStock() + amount);
			new FoodSource(food).update();

			// remove the item from the table
			foodTableModel.removeRow(row);

			JOptionPane.showMessageDialog(this, "Berhasil menghapus data!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Gagal menghapus data!");
			ex.printStackTrace();
		}
	}//GEN-LAST:event_removeButtonActionPerformed

	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
		// don't let user logout if they have an item on the table
		if (foodTable.getRowCount() != 0) {
			JOptionPane.showMessageDialog(null, "Anda masih punya item tersisa!");
			return;
		}
		Popup.<Login>open(new Login(), "Login Aplikasi Kasir");
		this.dispose();
	}//GEN-LAST:event_logoutButtonActionPerformed

	private String randHelper(Random rand, int upperBound) {
		int randomNumber = rand.nextInt(upperBound);

		if (randomNumber < 10) return "00" + randomNumber;
		if (randomNumber < 100) return "0" + randomNumber;
		return String.valueOf(randomNumber);
	}

	private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
		int rowCount = foodTableModel.getRowCount();

		if (rowCount == 0) {
			JOptionPane.showMessageDialog(this, "Tidak ada item yang akan dibeli!");
			return;
		}

		if (payAmountField.getText().isEmpty() || tableNumberField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Harap isi semua field!");
			return;
		}

		try {
			// need to specifically say this one is the order model
			// because we also have `kasir.ui.Order`
			kasir.models.Order order = new kasir.models.Order();
			Random rand = new Random();
			Date date = new Date(new java.util.Date().getTime());

			// these are the values that we need to get once
			String transactionID = "T"+randHelper(rand, 999);
			String userID = user.getUserID();
			long tableNumber = Long.parseLong(tableNumberField.getText());
			long totalPaid = Long.parseLong(payAmountField.getText().toString());
			long totalPrice = Long.parseLong(FormatRupiah.normalise(totalPriceField.getText().toString()));
			long exchange = Long.parseLong(FormatRupiah.normalise(exchangeField.getText().toString()));

			// this feels kinda wrong
			Transaction transaction = new Transaction();
			transaction.setTransactionID(transactionID);
			transaction.setUserID(userID);
			transaction.setDate(date);
			transaction.setTotalPrice(totalPrice);
			transaction.setTotalPaid(totalPaid);
			transaction.setExchange(exchange);

			// create the transaction item
			new TransactionSource(transaction).save();

			for (int i = 0; i < rowCount; i++) {
				// and these are the ones that needs to be different for each
				// iteration
				String id = randHelper(rand, 999);
				long amount = Long.parseLong(foodTableModel.getValueAt(i, 2).toString());
				long price = Long.parseLong(FormatRupiah.normalise(foodTableModel.getValueAt(i, 3).toString()));

				// find the food ID based on its name
				// I think this can be optimised but let's forget about that for now
				// I'm not sure about the performance implication of running a
				// query inside a for loop
				Food food = new Food();
				food.setName(foodTableModel.getValueAt(i, 1).toString());
				String foodID = new FoodSource(food).find().getFoodID();

				// this also feels kinda wrong
				// man, I really don't enjoy writing Java :p
				order.setOrderID("OR" + id);
				order.setTransactionID(transactionID);
				order.setUserID(userID);
				order.setDate(date);
				order.setTableNumber(tableNumber);
				order.setFoodAmount(amount);
				order.setFoodPrice(price);
				order.setFoodID(foodID);
				order.setDetails("TODO");
				order.setStatus("Lunas");

				new OrderSource(order).save();
			}

			// cleanup for the fields and table
			foodTableModel.setRowCount(0);
			foodTable.clearSelection();
			foodTable.validate();
			tableNumberField.setText("");
			payAmountField.setText("");
			exchangeField.setText("");
			totalPriceField.setText("");
			foodAmountField.setText("");

			// are ya winning son? yah, I'm winning
			// TODO: implement print using jasperthingamabob, i can't remember the name
			JOptionPane.showConfirmDialog(this, "Transaksi berhasil disimpan! Cetak nota?", "Cetak Nota", JOptionPane.YES_NO_OPTION);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Transaksi gagal disimpan!");
			ex.printStackTrace();
		}
	}//GEN-LAST:event_confirmButtonActionPerformed

	private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
		int rowCount = foodTableModel.getRowCount();

		// we want to add the existing items when we have them
		if (rowCount != 0) {
			for (int i = 0; i < rowCount; i++) {
				List<Object> row = new ArrayList<Object>();
				row.add(foodTableModel.getValueAt(i, 0));
				row.add(foodTableModel.getValueAt(i, 1));
				row.add(foodTableModel.getValueAt(i, 2));
				row.add(foodTableModel.getValueAt(i, 3));
				tableData.addRow(row);
			}
		}
		Popup.<KasirPopup>open(new KasirPopup(this), "Tambah Item");
	}//GEN-LAST:event_addItemButtonActionPerformed

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
		Popup.<Admin>open(new Admin(), "Halaman Admin");
		this.dispose();
	}//GEN-LAST:event_backButtonActionPerformed

	/**
	 * @param args the command line arguments
	*/
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Kasir().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton addItemButton;
	private javax.swing.JButton backButton;
	private javax.swing.JButton removeButton;
	private javax.swing.JButton confirmButton;
	private javax.swing.JTextField tableNumberField;
	private javax.swing.JLabel tableNumberLabel;
	private javax.swing.JTextField exchangeField;
	private javax.swing.JLabel exchangeLabel;
	private javax.swing.JTextField foodAmountField;
	private javax.swing.JLabel foodAmountLabel;
	private javax.swing.JTable foodTable;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JButton logoutButton;
	private javax.swing.JLabel orderTitle;
	private javax.swing.JTextField payAmountField;
	private javax.swing.JLabel payAmountLabel;
	private javax.swing.JTextField totalPriceField;
	private javax.swing.JLabel totalPriceLabel;
	// End of variables declaration//GEN-END:variables
}
