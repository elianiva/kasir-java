package kasir.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kasir.controllers.FoodSource;
import kasir.controllers.OrderSource;
import kasir.helpers.FormatRupiah;
import kasir.models.Food;
import kasir.models.Order;

public class TransactionPopup extends javax.swing.JFrame {
	private DefaultTableModel orderTableModel;
	private String transactionID;

	/**
	 * Creates new form OrderPopup
	 */
	public TransactionPopup() {
	}
	public TransactionPopup(String id) {
		this.setLocationRelativeTo(null); // center the window
		initComponents();
		initTableModel();

		// the order matters
		transactionID = id;
		populateData();
	}

	/**
	 * Set the desired table model
	 */
	private void initTableModel() {
		String[] columns = new String[]{
			"ID", "No Meja", "Nama Masakan", "Jumlah Masakan", "Total Harga"
		};
		orderTableModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		orderTable.setModel(orderTableModel);
		orderTable.getTableHeader().setReorderingAllowed(false); // prevent from table re-ordering
	}

	/**
	 * Populate table with initial data from database
	 */
	private void populateData() {
		try {
			List<Order> orders = OrderSource.findByTransactionID(transactionID);

			for (Order order : orders) {
				Food food = FoodSource.findByID(order.getFoodID());
				orderTableModel.addRow(new Object[] {
					order.getOrderID(),
					order.getStatus(),
					food.getName(),
					order.getFoodAmount(),
					FormatRupiah.format(order.getFoodPrice()),
				});
			}
		} catch (SQLException ex) {
			Logger.getLogger(TransactionPopup.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Tidak dapat memuat data!");
			ex.printStackTrace();
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        windowTitle = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();

        orderTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Makanan", "Harga", "Stok", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(orderTable);

        windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        windowTitle.setText("Detail Transaksi");

        backButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        backButton.setText("Kembali");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(242, 242, 242)
                            .addComponent(windowTitle))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(19, 19, 19)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(windowTitle)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TransactionPopup().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTable orderTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel windowTitle;
    // End of variables declaration//GEN-END:variables
}
