package hitung_gaji;

import java.awt.Color;

import main.java.hitung_gaji.MainWindow;

public class App {
    // This is *kinda* working, idk, couldn't be bothered to fix it
    public static void main(String[] args) {
        MainWindow appWindow = new MainWindow();
        appWindow.setVisible(true);
        appWindow.setTitle("Perhitungan Gaji");
        appWindow.setSize(400, 600);
        appWindow.setBackground(Color.WHITE);
    }
}
