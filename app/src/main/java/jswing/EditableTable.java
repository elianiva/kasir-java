package main.java.jswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class EditableTable extends JFrame implements ActionListener, TableModelListener {
    private static final long serialVersionUID = 1L;
    JFrame frame;
    JTable table;
    Vector<Vector<Object>> rows;
    Vector<Object> columns;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JButton cmdAdd, cmdDelete, cmdSetValue, cmdGetValue;
    JPanel mainPanel, buttonPanel;

    public EditableTable() {
        rows = new Vector<Vector<Object>>();
        columns = new Vector<Object>();
        String[] columnNames = {"ID", "Name", "Address", "Sex"};
        addColumns(columnNames);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tableModel = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return Boolean.class;
                default:
                    return String.class;
                }
            }
        };

        tableModel.setDataVector(rows, columns);

        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        table.setRowSelectionAllowed(false);
        table.getModel().addTableModelListener(this);

        buttonPanel = new JPanel();

        cmdAdd = new JButton("Add Row");
        cmdDelete = new JButton("Delete Row");

        buttonPanel.add(cmdAdd);
        buttonPanel.add(cmdDelete);

        cmdAdd.addActionListener(this);
        cmdDelete.addActionListener(this);

        frame = new JFrame("Table dengan Button Add dan Del Row");
        frame.setSize(600, 400);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add("Center", scrollPane);
        mainPanel.add("South", buttonPanel);
        mainPanel.setBackground(Color.WHITE);

        buttonPanel.setBackground(Color.white);

        table.getParent().setBackground(Color.WHITE);

        insertInitialData();

        frame.getContentPane().add(mainPanel);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }

    private void insertInitialData() {
        final Object[][] dummy = {
            {"S001", "Mariana", "Malang", false},
            {"S002", "Tatiana", "Surabaya", false},
            {"S003", "Harry", "Jakarta", true},
            {"M001", "Zakhy", "Bandung", true},
            {"D001", "Angela", "Bogor", false},
        };

        for (int i = 0; i < dummy.length; i++) {
            Vector<Object> r = new Vector<Object>();
            r.addElement(dummy[i][0]);
            r.addElement(dummy[i][1]);
            r.addElement(dummy[i][2]);
            r.addElement(dummy[i][3]);
            rows.addElement(r);
        }
    }

    public void addColumns(String[] colNames) {
        for (int i = 0; i < colNames.length; i++) {
            columns.addElement(colNames[i]);
        }
    }

    public void addRow() {
        Vector<Object> r = new Vector<Object>();
        r = createBlankElement();
        rows.addElement(r);
        table.addNotify();
    }

    public Vector<Object> createBlankElement() {
        Vector<Object> t = new Vector<Object>();
        t.addElement(" ");
        t.addElement(" ");
        t.addElement(" ");
        t.addElement(false);
        return t;
    }

    void deleteRow(int index) {
        if (index != -1 && index < rows.size()) {
            rows.removeElementAt(index);
            table.addNotify();
        }
    }

    // this is just a dummy method
    // not actually used
    public void tableChanged(TableModelEvent source) {}

    public void selectCell(int row, int col) {
        if (row != -1 && col != -1) {
            table.setRowSelectionInterval(row, row);
            table.setColumnSelectionInterval(col, col);
        }
    }

    public void actionPerformed(ActionEvent source) {
        if (source.getSource() == (JButton) cmdAdd) {
            addRow();
        }
        if (source.getSource() == (JButton) cmdDelete) {
            deleteRow(table.getSelectedRow());
        }
    }

    public Object getValueAt(int row, int col) {
        return rows.get(row).get(col);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }
}
