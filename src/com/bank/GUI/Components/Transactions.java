package com.bank.GUI.Components;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.Transaction;
import com.bank.GUI.Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Transactions extends JFrame {
    JTable transactions;
    DefaultTableModel model;
    private static Transactions instance = null;

    Transactions() {
        transactions = new JTable();
        transactions.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) table.getModel().getValueAt(row, 3);
                if ("WITHDRAW".equals(status)) {
                    setBackground(Color.red.darker());
                } else {
                    setBackground(Color.GREEN.darker());
                }
                return this;
            }
        });
        model = new DefaultTableModel(new String[]{"ערוץ", "תאריך", "סכום", "פעולה"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        this.setLayout(new FlowLayout());
        transactions.setModel(model);
        JScrollPane sp = new JScrollPane(transactions);
        sp.setPreferredSize(new Dimension(700, 300));
        this.add(sp);
        this.pack();
        this.setPreferredSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setTitle("כספומט");
        this.pack();
        this.setVisible(true);
    }

    public void setTransData(ArrayList<Transaction> data) throws IOException, InterruptedException {
        System.out.println("SETDATA Transactions");
        model.setRowCount(0);
        for (int i = 0; i < data.size(); i++) {

            String action = data.get(i).getAction();
            float amount = data.get(i).getAmount();
            Date date = data.get(i).getDate();
            String location = data.get(i).getLocation();


            Object[] dt = {location, date, amount, action,};
            model.addRow(dt);
//            State.getInstance().notifyTables();
        }

        this.repaint();
        System.out.println("##Row counts" + model.getRowCount());
        model.fireTableDataChanged();
        Dashboard.getInstance().repaint();


    }

    public static Transactions getInstance() throws IOException {
        if (instance == null) {
            instance = new Transactions();
        }
        return instance;
    }


}
