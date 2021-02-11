package main.java.guitest;

import java.awt.*;
import java.awt.event.*;

public class TFrame extends Frame {
    Label titleLabel, lengthLabel, widthLabel, areaLabel;
    TextField lengthText, widthText, resultText;
    Checkbox areaCheckbox, perimeterCheckbox;
    CheckboxGroup checkboxGroup;

    public TFrame() {
        setLayout(null);

        titleLabel = new Label("Hitung Luas Segi Empat");
        titleLabel.setFont(new Font("Arial", 1, 14));
        add(titleLabel);

        lengthLabel = new Label("Panjang : ");
        add(lengthLabel);
        lengthLabel.setBounds(100, 60, 62, 20);

        widthLabel = new Label("Lebar : ");
        add(widthLabel);
        widthLabel.setBounds(100, 90, 70, 20);

        areaLabel = new Label("Hasil : ");
        add(areaLabel);
        areaLabel.setBounds(100, 120, 70, 20);

        lengthText = new TextField("0");
        add(lengthText);
        lengthText.setBounds(200, 60, 60, 20);

        widthText = new TextField("0");
        add(widthText);
        widthText.setBounds(200, 90, 60, 20);

        resultText = new TextField("0");
        add(resultText);
        resultText.setBounds(200, 120, 60, 20);
        resultText.setEditable(false);

        checkboxGroup = new CheckboxGroup();

        areaCheckbox = new Checkbox("Luas", checkboxGroup, false);
        add(areaCheckbox).setBounds(90, 190, 100, 20);

        perimeterCheckbox = new Checkbox("Keliling", checkboxGroup, false);
        add(perimeterCheckbox).setBounds(200, 190, 100, 20);

        areaCheckbox.addItemListener(new MainAction());
        perimeterCheckbox.addItemListener(new MainAction());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public class MainAction implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            int length = Integer.parseInt(lengthText.getText().trim());
            int width = Integer.parseInt(widthText.getText().trim());
            Object source = e.getItemSelectable();

            if (source == areaCheckbox) {
                resultText.setText(String.valueOf(length * width));
            } else if (source == perimeterCheckbox) {
                resultText.setText(String.valueOf(2 * (length + width)));
            }
        }
    }
}
