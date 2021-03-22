package kasir.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import kasir.controllers.LevelSource;
import kasir.controllers.UserSource;
import kasir.helpers.Popup;
import kasir.models.Level;
import kasir.models.User;

public class UserManager extends javax.swing.JFrame {
	private DefaultTableModel userTableModel;
	private Admin parentWindow;
	private List<User> allUsers;
	private	User currentUser;

	// This is a bit hack-ish but I don't care lmao, I don't enjoy writing java
	private String currentLevel = "";

	/**
	 * Creates new form UserManagement
	 */
	public UserManager() {
		initComponents();
	}
	public UserManager(Admin parent, User user) {
		this.setLocationRelativeTo(null);
		initComponents();
		initTableModel();
		populateData();
		attachOnSelectionEvent();
		parentWindow = parent;
		currentUser = user;
	}

	/**
	 * Set the desired table model
	 */
	private void initTableModel() {
		String[] columns = new String[]{
			"ID", "Nama User", "Username", "Password", "Role"
		};
		userTableModel = new DefaultTableModel(columns, 0) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		};
		userTable.setModel(userTableModel);
		userTable.getTableHeader().setReorderingAllowed(false); // prevent from table re-ordering
	}

	private void attachOnSelectionEvent() {
		userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				int selectedRow = userTable.getSelectedRow();

				// we need to guard this here because when the row is cleared,
				// nothing gets selected
				if (selectedRow == -1) return;

				// set details for text fields
				idField.setText(userTable.getValueAt(selectedRow, 0).toString());
				userField.setText(userTable.getValueAt(selectedRow, 1).toString());
				usernameField.setText(userTable.getValueAt(selectedRow, 2).toString());
				passwordField.setText(userTable.getValueAt(selectedRow, 3).toString());
				levelField.setText(userTable.getValueAt(selectedRow, 4).toString());

				// change the current user
				currentUser.setUserID(userTable.getValueAt(selectedRow, 0).toString());
				currentUser.setName(userTable.getValueAt(selectedRow, 1).toString());
				currentUser.setUsername(userTable.getValueAt(selectedRow, 2).toString());
				currentUser.setPassword(userTable.getValueAt(selectedRow, 3).toString());
				currentLevel = userTable.getValueAt(selectedRow, 4).toString();
			}
		});
	}

	public void populateData() {
		// always reset the table first before filling it
		userTable.clearSelection();
		userTableModel.setRowCount(0);
		userTable.revalidate();

		try {
			allUsers = UserSource.findAll();
			Level level = new Level();

			for (User user : allUsers) {
				level.setLevelID(user.getLevelID());
				Level foundLevel = new LevelSource(level).find();

				userTableModel.addRow(new Object[] {
					user.getUserID(),
					user.getName(),
					user.getUsername(),
					user.getPassword(),
					foundLevel.getLevelName(),
				});
			}
		} catch (SQLException ex) {
			// this conflicts with our Level class so we use the full path
			Logger.getLogger(UserManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Gagal menambahkan pengguna!");
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

		windowTitle = new javax.swing.JLabel();
		deleteButton = new javax.swing.JButton();
		scrollPane = new javax.swing.JScrollPane();
		userTable = new javax.swing.JTable();
		levelField = new javax.swing.JTextField();
		usernameField = new javax.swing.JTextField();
		userField = new javax.swing.JTextField();
		usernameLabel = new javax.swing.JLabel();
		passwordLabel = new javax.swing.JLabel();
		userLabel = new javax.swing.JLabel();
		idField = new javax.swing.JTextField();
		idLabel = new javax.swing.JLabel();
		backButton = new javax.swing.JButton();
		logoutButton = new javax.swing.JButton();
		editButton = new javax.swing.JButton();
		levelLabel = new javax.swing.JLabel();
		addButton = new javax.swing.JButton();
		passwordField = new javax.swing.JTextField();


		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
		windowTitle.setText("Manajemen User");

		deleteButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		deleteButton.setText("Hapus User");
		deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		deleteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteButtonActionPerformed(evt);
			}
		});

		scrollPane.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N

		userTable.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		userTable.setModel(new javax.swing.table.DefaultTableModel(
			new Object [][] {

			},
			new String [] {
				"ID", "Nama User", "Username", "Password", "Role"
			}
		) {
				Class[] types = new Class [] {
					java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
		scrollPane.setViewportView(userTable);

		levelField.setEditable(false);

		usernameField.setEditable(false);

		userField.setEditable(false);

		usernameLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		usernameLabel.setText("Username User");

		passwordLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		passwordLabel.setText("Password User");

		userLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		userLabel.setText("Nama User");

		idField.setEditable(false);

		idLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		idLabel.setText("ID User");

		backButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		backButton.setText("Kembali");
		backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		backButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backButtonActionPerformed(evt);
			}
		});

		logoutButton.setFont(new java.awt.Font("Inter", 0, 14)); // NOI18N
		logoutButton.setText("Logout");
		logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		logoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});

		editButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		editButton.setText("Edit User");
		editButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editButtonActionPerformed(evt);
			}
		});

		levelLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		levelLabel.setText("Level User");

		addButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		addButton.setText("Tambah User");
		addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addButtonActionPerformed(evt);
			}
		});

		passwordField.setEditable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap(16, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18))
					.addGroup(layout.createSequentialGroup()
						.addComponent(logoutButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(windowTitle)
						.addGap(81, 81, 81)))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addComponent(idField, javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(idLabel)
					.addComponent(userLabel)
					.addComponent(userField, javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(usernameField, javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(usernameLabel)
					.addComponent(passwordLabel)
					.addComponent(levelField, javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(levelLabel)
					.addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
					.addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(passwordField))
				.addGap(14, 14, 14))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGap(17, 17, 17)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					.addComponent(windowTitle)
					.addComponent(logoutButton)
					.addComponent(backButton))
				.addGap(23, 23, 23)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addGroup(layout.createSequentialGroup()
						.addComponent(idLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(userLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(usernameLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(passwordLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addComponent(levelLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(levelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(19, 19, 19)
						.addComponent(editButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(addButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(deleteButton))
					.addComponent(scrollPane))
				.addContainerGap(17, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
		User user = new User();
		int selectedRow = userTable.getSelectedRow();
		user.setUserID(userTable.getValueAt(selectedRow, 0).toString());

		// initialise using the above user instance
		UserSource userData = new UserSource(user);

		try {
			userData.delete();
			JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
		} catch (SQLException ex) {
			// use full path, if not, it will conflict with the other one
			Logger.getLogger(UserManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(this, "Data gagal dihapus!");
			ex.printStackTrace();
		}

		// we need to refresh the table; hence this function call
		populateData();
	}//GEN-LAST:event_deleteButtonActionPerformed

	private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
		Popup.<Admin>open(new Admin(currentUser), "Halaman Admin");
		this.dispose();
	}//GEN-LAST:event_backButtonActionPerformed

	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
		Popup.<Login>open(new Login(), "Login Aplikasi Kasir");
		this.dispose();
		parentWindow.dispose();
	}//GEN-LAST:event_logoutButtonActionPerformed

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
		Popup.<UserPopup>open(new UserPopup(this), "Tambah User");
	}//GEN-LAST:event_addButtonActionPerformed

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
		if (userTable.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Tidak ada user yang dipilih!");
			return;
		}

		Popup.<UserPopup>open(new UserPopup(this, currentUser, currentLevel), "Edit User");
	}//GEN-LAST:event_editButtonActionPerformed

	/**
	 * @param args the command line arguments
	*/
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserManager().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton addButton;
	private javax.swing.JButton backButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JButton editButton;
	private javax.swing.JTable userTable;
	private javax.swing.JTextField idField;
	private javax.swing.JLabel idLabel;
	private javax.swing.JLabel levelLabel;
	private javax.swing.JButton logoutButton;
	private javax.swing.JTextField levelField;
	private javax.swing.JTextField passwordField;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JTextField userField;
	private javax.swing.JLabel userLabel;
	private javax.swing.JTextField usernameField;
	private javax.swing.JLabel usernameLabel;
	private javax.swing.JLabel windowTitle;
	// End of variables declaration//GEN-END:variables
}
