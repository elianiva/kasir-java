package tugas2;

import tugas2.ui.DataManager;
import com.formdev.flatlaf.FlatLightLaf;

public class App {
	public static void main(String[] args) {
		FlatLightLaf.install();
		DataManager window = new DataManager();

		// we need to set its visibility so we can actually see it and use it
		window.setVisible(true);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
	}
}
