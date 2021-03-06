package kasir;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;

import kasir.helpers.Popup;
import kasir.ui.Login;

public class App {
    public static void main(String[] args) {
		FlatLightLaf.install();
		FlatArcOrangeIJTheme.install();

		// open the login screen
		Popup.<Login>open(new Login(), "Login Aplikasi Kasir");
    }
}
