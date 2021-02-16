package main.java.jswing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class TableData extends JFrame {
	private static final long serialVersionUID = 1L;

	public TableData() {
        super("Contoh Penggunaan JTable");

        MyTableModel myModel = new MyTableModel();
        JTable table = new JTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 80));

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class MyTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	final String[] columnNames = {"ID", "Name", "Address", "Sex"};
    final Object[][] data = {
        {"S001", "Mariana", "Malang", false},
        {"S002", "Tatiana", "Surabaya", false},
        {"S003", "Harry", "Jakarta", true},
        {"M001", "Zakhy", "Bandung", true},
        {"D001", "Angela", "Bogor", false},
    };

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return col < 3;
    }
}
