package com.bank.GUI.Components;

import Classes.Client;
import Classes.Enums.Enums;
import Classes.User;
import Common.ClientThread;
import com.bank.GUI.Dashboard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ClientsTable extends JPanel {
    private static ClientsTable instance = null;
    ClientThread clientThread;
    JTable clientsTable;
    DefaultTableModel model;
    String[] columnNames = {"ת.ז.", "סטטוס", "יתרה", "מספר לקוח", "מספר חשבון", "שם"};
    String data[][] = {{"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
            {"026480996", "פעיל", "670000", "123456", "99999", "סאמר אלעטאונה"},
    };

     ClientsTable() throws IOException {

        this.clientThread = clientThread;

        clientsTable = new JTable();
        clientsTable.setPreferredSize(new Dimension(600, 300));
        model = new DefaultTableModel(new String[]{"קוד אשראי", "מזהה", "ת.ז.", "סטטוס", "יתרה", "מספר חשבון", "שם"}, 0);
        clientsTable.setModel(model);
//        Object[] dt =  {1234,1234,Enums.Status.ACTIVE,200,333,111,"Samer"};
//        model.addRow(dt);

        JScrollPane sp = new JScrollPane(clientsTable);
        sp.setPreferredSize(new Dimension(700, 300));

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(sp);


    }

    public void setData(ArrayList<Client> data) throws IOException {
        System.out.println("SETDATA ClientsTable");

        for (int i = 0; i < data.size(); i++) {

            String userName = data.get(i).getFullName();
            Integer accountID = data.get(i).getAccountNumber();
            Integer clientAccountID = data.get(i).getClientAccountID();
            Float balance = data.get(i).getBalance();
            Enums.Status status = data.get(i).getStatus();
            Integer clientID = data.get(i).getClientID();
            Integer cardCode = data.get(i).getCardCode();

            Object[] dt = {cardCode,clientAccountID, clientID, status, balance, accountID, userName};
            model.addRow(dt);
        }

        this.repaint();
        System.out.println("##Row counts" + model.getRowCount());
        model.fireTableDataChanged();


    }

    public static ClientsTable getInstance() throws IOException {
        if (instance == null) {
            instance = new ClientsTable();
        }
        return instance;
    }
}
