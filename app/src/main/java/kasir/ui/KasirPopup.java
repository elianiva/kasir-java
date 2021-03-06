package kasir.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kasir.controllers.MasakanSource;
import kasir.helpers.OrderTable;
import kasir.models.Masakan;

public class KasirPopup extends javax.swing.JFrame {
	private DefaultTableModel foodTableModel;
	private OrderTable tableData;
	private Kasir parentWindow;

	/**
	 * Creates new form OrderPopup
	 */
	public KasirPopup() {
	}
	public KasirPopup(OrderTable data, Kasir parent) {
		this.setLocationRelativeTo(null); // center the window
		initComponents();
		initTableModel();
		populateData();
		tableData = data;
		parentWindow = parent;
	}

	/**
	 * Set the desired table model
	 */
	private void initTableModel() {
		String[] columns = new String[]{
			"ID", "Nama Masakan", "Harga", "Stok", "Status"
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
	 * Populate table with initial data from database
	 */
	private void populateData() {
		MasakanSource foodSource = new MasakanSource();

		try {
			List<Masakan> foods = foodSource.findAll();

			for (Masakan food : foods) {
				foodTableModel.addRow(new Object[] {
					food.getFoodID(),
					food.getName(),
					food.getPrice(),
					food.getStock(),
					food.getStatus(),
				});
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Tidak dapat memuat data!");
			ex.printStackTrace();
		}
	}

	/**
	 * Set parent with selected row
	 */
	private void setParentData() {
		if (amountField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Jumlah masakan tidak boleh kosong!");
			return;
		}

		int row = foodTable.getSelectedRow();

		if ((long)foodTable.getValueAt(row, 3) == 0) {
			JOptionPane.showMessageDialog(this, "Stok masakan sedang kosong!");
			return;
		}

		List<List<Object>> allItems = new ArrayList<List<Object>>();
		allItems.addAll(tableData.getRows());

		// create a row
		List<Object> item = new ArrayList<Object>();
		item.add(foodTable.getValueAt(row, 0));
		item.add(foodTable.getValueAt(row, 1));
		long amount = Long.parseLong(amountField.getText());
		item.add(amount);
		item.add((long)foodTable.getValueAt(row, 2) * amount);

		allItems.add(item);

		// set parent table data
		parentWindow.setTableData(allItems);

		// cleanup, reset to initial state
		foodTable.clearSelection();
		amountField.setText("");
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
        foodTable = new javax.swing.JTable();
        windowTitle = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        amountField = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();

        foodTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
        foodTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(foodTable);

        windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
        windowTitle.setText("Tambah Item");

        addButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        addButton.setText("Tambah Item");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        cancelButton.setText("Batalkan");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        amountField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N

        amountLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
        amountLabel.setText("Jumlah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(windowTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(amountLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addButton))
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
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(cancelButton)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        setParentData();
    }//GEN-LAST:event_addButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new KasirPopup().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTable foodTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel windowTitle;
    // End of variables declaration//GEN-END:variables
}
