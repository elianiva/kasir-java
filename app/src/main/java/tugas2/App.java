package tugas2;

import tugas2.ui.DataManagerForm;

public class App {
	public static void main(String[] args) {
		// this is the main window
		DataManagerForm window = new DataManagerForm();

		// we need to set its visibility so we can actually see it and use it
		window.setVisible(true);
	}
}
