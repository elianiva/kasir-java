package kasir.helpers;

import javax.swing.JFrame;

/**
 * Helper class for managing popups
 */
public class Popup {

	/**
	 * Open the popup and do some stuff with it so we don't repeat ourself
	 * @param popup - The instance of the window
	 * @param title - The window title
	 * @return popup
	 */
	public static<T extends JFrame> T open(T popup, String title) {
		popup.setLocationRelativeTo(null);
		popup.setVisible(true);
		popup.setResizable(false);
		popup.setTitle(title);

		return popup;
	}
}
