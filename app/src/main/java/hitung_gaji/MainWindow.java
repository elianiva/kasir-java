package main.java.hitung_gaji;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame {
    private static final long serialVersionUID = 1L;
    Label lbTahunMasuk, lbJabatan, lbGajiPokok, lbBonus, lbTunjangan, lbTotalGaji;
    TextField inputTahunMasuk, inputGajiPokok, inputBonus, inputTunjangan, inputTotalGaji;
    CheckboxGroup jabatan;
    Checkbox staff, manager, direktur;
    Button submitButton;
    String selected = "staff";

    public MainWindow() {
        setLayout(null);

        // using 4 digits here because the assignment wasn't clear enough
        // 03 could be 2003 or 1903 which is ambiguous
        createLabel(lbTahunMasuk, "Tahun Masuk (4 digit) :", 20, 30, 180, 30);
        inputTahunMasuk = createInput(inputTahunMasuk, 220, 30, 120, 28, true);

        createLabel(lbJabatan, "Jabatan :", 20, 70, 120, 30);

        jabatan = new CheckboxGroup();
        staff = new Checkbox("Staff", jabatan, true);
        staff.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                selected = "staff";
            }
        });
        this.add(staff).setBounds(40, 100, 120, 28);

        manager = new Checkbox("Manager", jabatan, false);
        manager.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                selected = "manager";
            }
        });
        this.add(manager).setBounds(40, 130, 120, 28);

        direktur = new Checkbox("Direktur", jabatan, false);
        direktur.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                selected = "direktur";
            }
        });
        this.add(direktur).setBounds(40, 160, 120, 28);

        createLabel(lbGajiPokok, "Gaji Pokok :", 20, 200, 140, 30);
        inputGajiPokok = createInput(inputGajiPokok, 220, 200, 120, 28, false);

        createLabel(lbBonus, "Bonus :", 20, 240, 120, 30);
        inputBonus = createInput(inputBonus, 220, 240, 120, 28, false);

        createLabel(lbTunjangan, "Tunjangan :", 20, 240, 120, 30);
        inputTunjangan = createInput(inputTunjangan, 220, 240, 120, 28, false);

        createLabel(lbTotalGaji, "Total Gaji :", 20, 280, 120, 30);
        inputTotalGaji = createInput(inputTotalGaji, 220, 280, 120, 28, false);

        submitButton = new Button("Hitung");
        add(submitButton);
        submitButton.setBounds(100, 340, 140, 30);
        submitButton.addActionListener(new ButtonAction());

        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Helper function to create a label
     */
    private void createLabel(Label label, String content, int x, int y, int width, int height) {
        label = new Label(content);
        label.setFont(new Font("Open Sans", 1, 14));
        add(label);
        label.setBounds(x, y, width, height);
    }

    /**
     * Helper function to create an input field
     */
    private TextField createInput(TextField field, int x, int y, int width, int height, boolean isEditable) {
        field = new TextField("");
        field.setFont(new Font("Open Sans", 0, 13));
        add(field);
        field.setBounds(x, y, width, height);
        field.setEditable(isEditable);
        return field;
    }

    class ButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String inputTahunMasukVal = inputTahunMasuk.getText().trim();
            int tahunMasuk = Integer.parseInt(inputTahunMasukVal != "" ? inputTahunMasukVal : "0");
            int lamaBekerja = 2020 - tahunMasuk;

            int gaji = hitungGaji(selected);
            double bonus = hitungBonus(lamaBekerja, gaji, selected);
            double tunjangan = hitungTunjangan(selected, gaji);

            inputGajiPokok.setText(String.valueOf(gaji));
            inputBonus.setText(String.valueOf(bonus));
            inputTunjangan.setText(String.valueOf(tunjangan));
            inputTotalGaji.setText(String.valueOf(gaji + bonus + tunjangan));
        }

        private int hitungGaji(String jabatan) {
            if (selected == "staff") return 1000000;
            if (selected == "manager") return 5000000;
            if (selected == "direktur") return 10000000;
            return 0;
        }

        private double hitungBonus(int lama, int gajiPokok, String jabatan) {
            if (jabatan == "staff") {
                if (lama < 1) return 0;
                if (lama <= 1 && lama <= 10) return gajiPokok * 0.05;
                if (lama > 10) return gajiPokok * (lama/100);
                return 0;
            }
            if (jabatan == "manager") return (0.05 + (lama / 100)) * gajiPokok;
            if (jabatan == "direktur") return (0.1 + (lama / 100)) * gajiPokok;
            return 0;
        }

        private double hitungTunjangan(String jabatan, int gajiPokok) {
            if (jabatan == "staff") return 0;
            if (jabatan == "manager") return 0.05 * gajiPokok;
            if (jabatan == "direktur") return 0.1 * gajiPokok;
            return 0;
        }
    }
}
