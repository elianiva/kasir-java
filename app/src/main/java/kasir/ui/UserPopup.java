package kasir.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import kasir.controllers.LevelSource;
import kasir.controllers.UserSource;
import kasir.models.Level;
import kasir.models.User;

public class UserPopup extends javax.swing.JFrame {
	private User currentUser = new User();
	private String currentLevel;
	private UserManager parentWindow;
	private String mode = "add";

	/**
	 * Creates new form MenuPopup
	 */
	public UserPopup() {
	}
	public UserPopup(UserManager parent) {
		this.setLocationRelativeTo(null);
		initComponents();

		parentWindow = parent;
	}
	public UserPopup(UserManager parent, User user, String level) {
		this.setLocationRelativeTo(null);
		initComponents();

		currentUser = user;
		currentLevel = level;
		parentWindow = parent;
		mode = "edit";
		idField.setEditable(false);

		initComboboxModel();
		populateData();
	}

	private void populateData() {
		idField.setText(currentUser.getUserID().toString());
		nameField.setText(currentUser.getName().toString());
		usernameField.setText(currentUser.getUsername().toString());
		passwordField.setText(currentUser.getPassword().toString());
		levelComboBox.setSelectedItem(currentLevel);
	}

	private void initComboboxModel() {
		Level level = new Level();
		level.setLevelName(currentLevel);

		try {
			List<Level> allLevels = LevelSource.findAll();
			Vector<String> levelNames = new Vector<String>();
			allLevels.stream().forEach(item -> levelNames.add(item.getLevelName()));

			levelComboBox.setModel(new DefaultComboBoxModel<>(levelNames));
		} catch (SQLException ex) {
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
		idLabel = new javax.swing.JLabel();
		idField = new javax.swing.JTextField();
		usernameField = new javax.swing.JTextField();
		usernameLabel = new javax.swing.JLabel();
		nameField = new javax.swing.JTextField();
		nameLabel = new javax.swing.JLabel();
		passwordField = new javax.swing.JTextField();
		passwordLabel = new javax.swing.JLabel();
		saveButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		levelComboBox = new javax.swing.JComboBox<>();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
		windowTitle.setText("Tambah User");

		idLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		idLabel.setText("ID User");

		idField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N

		usernameField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N

		usernameLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		usernameLabel.setText("Username");

		nameField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N

		nameLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		nameLabel.setText("Nama User");

		passwordField.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N

		passwordLabel.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		passwordLabel.setText("Password");

		saveButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		saveButton.setText("Simpan");
		saveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveButtonActionPerformed(evt);
			}
		});

		cancelButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		cancelButton.setText("Batal");
		cancelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelButtonActionPerformed(evt);
			}
		});

		levelComboBox.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		levelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Kasir", "Owner", "Pelanggan" }));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(221, 221, 221)
							.addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(12, 12, 12)
							.addComponent(saveButton))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
							.addGap(16, 16, 16)
							.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(levelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(idLabel)
										.addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(usernameLabel))
									.addGap(18, 18, 18)
									.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(nameLabel)
										.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(passwordLabel))))))
					.addGroup(layout.createSequentialGroup()
						.addGap(152, 152, 152)
						.addComponent(windowTitle)))
				.addContainerGap(21, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
				.addGap(16, 16, 16)
				.addComponent(windowTitle)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
					.addGroup(layout.createSequentialGroup()
						.addComponent(idLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(usernameLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
					.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(passwordLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addGap(18, 18, 18)
				.addComponent(levelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(saveButton)
					.addComponent(cancelButton))
				.addGap(23, 23, 23))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
		this.dispose();
	}//GEN-LAST:event_resetButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
		long currentLevelID = 0;
		String id = idField.getText();
		String name = nameField.getText();
		String username = usernameField.getText();
		String password = passwordField.getText();

		if (id.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Semua field wajib diisi!");
			return;
		}

		try {
			// this is a bit of a hack, but again, I don't care
			Level level = new Level();
			level.setLevelName(levelComboBox.getSelectedItem().toString());
			currentLevelID = new LevelSource(level).find().getLevelID();

			// reset the user if we want to add them
			if (mode == "add") {
				currentUser = new User();
			}

			// set the current user details
			currentUser.setUserID(id);
			currentUser.setName(name);
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			currentUser.setLevelID(currentLevelID);

			// initialise the controller so we can do CRUD operations with the
			// User model
			UserSource userSource = new UserSource(currentUser);

			// do action based on current `popup-mode`
			if (mode == "add") {
				userSource.save();
			} else {
				userSource.update();
			}

			// we need this here to refresh the parent table view
			parentWindow.populateData();

			JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
			ex.printStackTrace();
		}

		this.dispose();
	}//GEN-LAST:event_saveButtonActionPerformed

	/**
	 * @param args the command line arguments
	*/
	public static void main(String args[]) {
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new UserPopup().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cancelButton;
	private javax.swing.JTextField idField;
	private javax.swing.JLabel idLabel;
	private javax.swing.JComboBox<String> levelComboBox;
	private javax.swing.JTextField usernameField;
	private javax.swing.JLabel usernameLabel;
	private javax.swing.JTextField nameField;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JButton saveButton;
	private javax.swing.JTextField passwordField;
	private javax.swing.JLabel passwordLabel;
	private javax.swing.JLabel windowTitle;
	// End of variables declaration//GEN-END:variables
}
