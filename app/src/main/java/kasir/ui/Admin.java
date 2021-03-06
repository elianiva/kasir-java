package kasir.ui;

import kasir.helpers.Popup;

public class Admin extends javax.swing.JFrame {

	/**
	 * Creates new form Admin
	 */
	public Admin() {
		this.setLocationRelativeTo(null); // center the window
		initComponents();
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
		manageUsersButton = new javax.swing.JButton();
		manageMenusButton = new javax.swing.JButton();
		manageReferenceButton = new javax.swing.JButton();
		manageTransactionsButton = new javax.swing.JButton();
		logoutButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		windowTitle.setFont(new java.awt.Font("Inter", 1, 18)); // NOI18N
		windowTitle.setText("Halaman Admin");

		manageUsersButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		manageUsersButton.setText("Manajemen User");
		manageUsersButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		manageUsersButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				manageUsersButtonActionPerformed(evt);
			}
		});

		manageMenusButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		manageMenusButton.setText("Manajemen Menu");
		manageMenusButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		manageMenusButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				manageMenusButtonActionPerformed(evt);
			}
		});

		manageReferenceButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		manageReferenceButton.setText("Manajemen Referensi");
		manageReferenceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		manageReferenceButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				managerReferenceButtonActionPerformed(evt);
			}
		});

		manageTransactionsButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		manageTransactionsButton.setText("Manajemen Transaksi");
		manageTransactionsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		manageTransactionsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				manageTransactionsButtonActionPerformed(evt);
			}
		});

		logoutButton.setFont(new java.awt.Font("Inter", 0, 16)); // NOI18N
		logoutButton.setText("Logout");
		logoutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		logoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addContainerGap(73, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(windowTitle)
						.addGap(203, 203, 203))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(manageTransactionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(manageMenusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
							.addComponent(manageUsersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(manageReferenceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(71, 71, 71))
					.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(logoutButton)
						.addGap(228, 228, 228))))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(layout.createSequentialGroup()
				.addGap(15, 15, 15)
				.addComponent(windowTitle)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(manageMenusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(manageUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
					.addComponent(manageReferenceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
					.addComponent(manageTransactionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(151, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void manageUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUsersButtonActionPerformed
		Popup.<UserManager>open(new UserManager(this), "Manajemen User");
		this.dispose();
	}//GEN-LAST:event_manageUsersButtonActionPerformed

	private void manageMenusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageItemsButtonActionPerformed
		Popup.<MenuManager>open(new MenuManager(this), "Manajemen Menu");
		this.dispose();
	}//GEN-LAST:event_manageMenusButtonActionPerformed

	private void managerReferenceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageReferenceButtonActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_manageReferenceButtonActionPerformed

	private void manageTransactionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageTransactionsButtonActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_manageTransactionsButtonActionPerformed

	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordToggleActionPerformed
		Popup.<Login>open(new Login(), "Login Aplikasi Kasir");
		this.dispose();
	}//GEN-LAST:event_passwordToggleActionPerformed

	/**
	 * @param args the command line arguments
	*/
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		*/
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Admin().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton logoutButton;
	private javax.swing.JLabel windowTitle;
	private javax.swing.JButton manageMenusButton;
	private javax.swing.JButton manageTransactionsButton;
	private javax.swing.JButton manageReferenceButton;
	private javax.swing.JButton manageUsersButton;
	// End of variables declaration//GEN-END:variables
}
